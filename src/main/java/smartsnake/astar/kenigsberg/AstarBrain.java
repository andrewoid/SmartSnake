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
            Node current = openNodes.get(0);
            current = getLowestCost(openNodes);

            openNodes.remove(current);
            closedNodes.add(current);

            Point currentLocation = current.getLocation();
            if (currentLocation.equals(food)) {
                return current.getDirectionFromStart();
            }

            ArrayList<Node> neighborsOfCurrent = new ArrayList<>();

            int indexRight = openNodes.indexOf(new Node(new Point(
                    currentLocation.x + 1, currentLocation.y), food));
            if (indexRight == -1) {
                Node rightNode = new Node(new Point(currentLocation.x + 1, currentLocation.y), food);
                rightNode.setParent(current, Direction.Right);
                neighborsOfCurrent.add(rightNode);
            } else {
                neighborsOfCurrent.add(openNodes.get(indexRight));
            }

            int indexAbove = openNodes.indexOf(new Node(new Point(
                    currentLocation.x, currentLocation.y - 1), food));
            if (indexAbove == -1) {
                Node aboveNode = new Node(new Point(currentLocation.x, currentLocation.y - 1), food);
                aboveNode.setParent(current, Direction.Up);
                neighborsOfCurrent.add(aboveNode);
            } else {
                neighborsOfCurrent.add(openNodes.get(indexAbove));
            }

            int indexLeft = openNodes.indexOf(new Node(new Point(
                    current.getLocation().x - 1, current.getLocation().y), food));
            if (indexLeft == -1) {
                Node leftNode = new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food);
                leftNode.setParent(current, Direction.Left);
                neighborsOfCurrent.add(leftNode);
            } else {
                neighborsOfCurrent.add(openNodes.get(indexLeft));
            }

            int indexBelow = openNodes.indexOf(new Node(new Point(
                    currentLocation.x, currentLocation.y + 1), food));
            if (indexBelow == -1) {
                Node belowNode = new Node(new Point(currentLocation.x, currentLocation.y + 1), food);
                belowNode.setParent(current, Direction.Down);
                neighborsOfCurrent.add(belowNode);
            } else {
                neighborsOfCurrent.add(openNodes.get(indexBelow));
            }

            for (Node neighbor : neighborsOfCurrent) {
                if (!closedNodes.contains(neighbor)) {
                    if (neighbor.setParent(current, neighbor.getParentDirection()) || !openNodes.contains(neighbor)) {
                        neighbor.setParent(current, neighbor.getParentDirection());
                        openNodes.add(neighbor);
                    }
                }
            }
        }
        return null;
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