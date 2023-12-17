package smartsnake.astar.kenigsberg;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AstarBrain implements Brain {

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        ArrayList<Node> openNodes = new ArrayList<>();
        Set<Node> closedNodes = new HashSet<>();

        Node startNode = new Node(snake.getHeadLocation(), food);
        openNodes.add(startNode);

        addSnakeToClosed(snake, food, closedNodes);

        while (!openNodes.isEmpty()) {
            Node current = getLowestCost(openNodes);

            openNodes.remove(current);
            closedNodes.add(current);

            Point currentLocation = current.getLocation();
            if (currentLocation.equals(food)) {
                return current.getDirectionFromStart();
            }

            updateNeighbors(current, new Point(currentLocation.x + 1, currentLocation.y),
                    Direction.Right, openNodes, closedNodes, food, garden);
            updateNeighbors(current, new Point(currentLocation.x, currentLocation.y - 1),
                    Direction.Up, openNodes, closedNodes, food, garden);
            updateNeighbors(current, new Point(current.getLocation().x - 1, current.getLocation().y),
                    Direction.Left, openNodes, closedNodes, food, garden);
            updateNeighbors(current, new Point(currentLocation.x, currentLocation.y + 1),
                    Direction.Down, openNodes, closedNodes, food, garden);
        }
        return null;
    }

    public void updateNeighbors(Node current, Point point, Direction direction,
                                ArrayList<Node> openNodes, Set<Node> closedNodes, Food food, Garden garden) {

        Node directionNode = new Node(point, food);
        if (!closedNodes.contains(directionNode) && garden.contains(point)) {
            int index = openNodes.indexOf(directionNode);
            if (index == -1) {
                openNodes.add(directionNode);
            } else {
                directionNode = openNodes.get(index);
            }
            directionNode.setParent(current, direction);
        }
    }

    public Node getLowestCost(ArrayList<Node> openNodes) {
        Node lowestfcostNode = openNodes.get(0);
        for (Node open : openNodes) {
            if (open.getCost() < lowestfcostNode.getCost()) {
                lowestfcostNode = open;
            }
        }
        return lowestfcostNode;
    }

    public void addSnakeToClosed(Snake snake, Food food, Set<Node> closedNodes) {
        List<Point> snakeSegments = snake.getSegments();
        for (int i = 1; i < snakeSegments.size(); i++) {
            closedNodes.add(new Node(snakeSegments.get(i), food));
        }
    }
}