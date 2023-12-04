package smartsnake.astar.kenigsberg;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AstarBrain implements Brain {

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();

        Node startNode = new Node(snake.getHeadLocation(), food.getLocation());
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

            updateNeighbors(current, new Point(currentLocation.x + 1, currentLocation.y), Direction.Right, openNodes, closedNodes, food);
            updateNeighbors(current, new Point(currentLocation.x, currentLocation.y - 1), Direction.Up, openNodes, closedNodes, food);
            updateNeighbors(current, new Point(current.getLocation().x - 1, current.getLocation().y),
                    Direction.Left, openNodes, closedNodes, food);
            updateNeighbors(current, new Point(currentLocation.x, currentLocation.y + 1), Direction.Down, openNodes, closedNodes, food);
        }
        return null;
    }

    public void updateNeighbors(Node current, Point point, Direction direction,
                                ArrayList<Node> openNodes, ArrayList<Node> closedNodes, Food food) {

        Node directionNode = new Node(point, food);
        if (!closedNodes.contains(directionNode)) {
            int index = openNodes.indexOf(directionNode);
            if (index != -1) {
                openNodes.get(index).setParent(current, direction);
            } else {
                directionNode.setParent(current, direction);
                openNodes.add(directionNode);
            }
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

    public void addSnakeToClosed(Snake snake, Food food, ArrayList<Node> closedNodes) {
        List<Point> snakeSegments = snake.getSegments();
        for (int i = 1; i < snakeSegments.size(); i++) {
            closedNodes.add(new Node(snakeSegments.get(i), food));
        }
    }
}