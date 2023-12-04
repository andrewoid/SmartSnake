package smartsnake.astar.kenigsberg;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
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

            Node rightNode = new Node(new Point(currentLocation.x + 1, currentLocation.y), food);
            if (!closedNodes.contains(rightNode)) {
                setParentInBrain(rightNode, current, Direction.Right);
                if (!openNodes.contains(rightNode)) {
                    openNodes.add(rightNode);
                }
            }


            Node aboveNode = new Node(new Point(currentLocation.x, currentLocation.y - 1), food);
            if (!closedNodes.contains(aboveNode)) {
                setParentInBrain(aboveNode, current, Direction.Up);
                if (!openNodes.contains(aboveNode)) {
                    openNodes.add(aboveNode);
                }
            }


            Node leftNode = new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food);
            if (!closedNodes.contains(leftNode)) {
                setParentInBrain(leftNode, current, Direction.Left);
                if (!openNodes.contains(leftNode)) {
                    openNodes.add(leftNode);
                }

            }

            Node belowNode = new Node(new Point(currentLocation.x, currentLocation.y + 1), food);
            if (!closedNodes.contains(belowNode)) {
                setParentInBrain(belowNode, current, Direction.Down);
                if (!openNodes.contains(belowNode)) {
                    openNodes.add(belowNode);
                }
            }
        }
        return null;
    }

    public void setParentInBrain(Node currentNode, Node parent, Direction direction) {
        currentNode.setParent(parent, direction);
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