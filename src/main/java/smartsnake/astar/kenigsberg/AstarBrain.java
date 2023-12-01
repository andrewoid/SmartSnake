package smartsnake.astar.kenigsberg;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AstarBrain implements Brain {

    /**
     * Goal: Determine what direction the snake should move to
     * Implement Astar
     * Code given in garden uses the brain to get the location it should move in
     */
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        // Notes:
        // look at whole path and then determine the direction
        // loop = while there are still open nodes
        // Open will start with the node at the head of the snake

        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();

        Node startNode = new Node(snake.getHeadLocation(), food.getLocation());
        openNodes.add(startNode);

        List<Point> snakeSegments = snake.getSegments();
        for (int i = 1; i < snakeSegments.size(); i++) {
            closedNodes.add(new Node(snakeSegments.get(i).getLocation(), food));
        }

        while (!openNodes.isEmpty()) {
            Node current = openNodes.get(0);
            double lowestfcost = openNodes.get(0).getCost();
            for (Node open : openNodes) {
                if (open.getCost() < lowestfcost) {
                    lowestfcost = open.getCost();
                    current = open;
                }
            }

            openNodes.remove(current);
            closedNodes.add(current);

            if (current.getLocation().equals(food)) {
                return current.getDirectionFromStart();
            }

            ArrayList<Node> neighborsOfCurrent = new ArrayList<>();

            if (!openNodes.contains(new Node(new Point(current.getLocation().x + 1, current.getLocation().y), food))) {
                Node rightNode = new Node(new Point(current.getLocation().x + 1, current.getLocation().y), food);
                rightNode.setParent(current, Direction.Right);
                neighborsOfCurrent.add(rightNode);
            } else {
                int index = openNodes.indexOf(new Node(new Point(
                        current.getLocation().x + 1, current.getLocation().y), food));
                neighborsOfCurrent.add(openNodes.get(index));
            }

            if (!openNodes.contains(new Node(new Point(current.getLocation().x, current.getLocation().y - 1), food))) {
                Node aboveNode = new Node(new Point(current.getLocation().x, current.getLocation().y - 1), food);
                aboveNode.setParent(current, Direction.Up);
                neighborsOfCurrent.add(aboveNode);
            } else {
                int index = openNodes.indexOf(new Node(new Point(
                        current.getLocation().x, current.getLocation().y - 1), food));
                neighborsOfCurrent.add(openNodes.get(index));
            }

            if (!openNodes.contains(new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food))) {
                Node leftNode = new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food);
                leftNode.setParent(current, Direction.Left);
                neighborsOfCurrent.add(leftNode);
            } else {
                int index = openNodes.indexOf(new Node(new Point(
                        current.getLocation().x - 1, current.getLocation().y), food));
                neighborsOfCurrent.add(openNodes.get(index));
            }

            if (!openNodes.contains(new Node(new Point(current.getLocation().x, current.getLocation().y + 1), food))) {
                Node belowNode = new Node(new Point(current.getLocation().x, current.getLocation().y + 1), food);
                belowNode.setParent(current, Direction.Down);
                neighborsOfCurrent.add(belowNode);
            } else {
                int index = openNodes.indexOf(new Node(new Point(
                        current.getLocation().x, current.getLocation().y + 1), food));
                neighborsOfCurrent.add(openNodes.get(index));
            }

            for (Node neighbor : neighborsOfCurrent) {
                if (closedNodes.contains(neighbor)) {
                    break;
                } else {
                    if (neighbor.setParent(current, neighbor.getParentDirection()) || !openNodes.contains(neighbor)) {
                        neighbor.setParent(current, neighbor.getParentDirection());
                        if (!openNodes.contains(neighbor)) {
                            openNodes.add(neighbor);
                        }
                    }
                }
            }
        }
        return null;
    }
}

        /*
        while (!snake.intersectsItself() && !snake.intersectsHead(food) && !snake.intersects(food)) {
            Node rightNode = new Node(new Point(startNode.getLocation().x + 1, startNode.getLocation().y), food);
            double rightCost = rightNode.getCost();

            Node aboveNode = new Node(new Point(startNode.getLocation().x, startNode.getLocation().y - 1), food);
            double aboveCost = aboveNode.getCost();

            Node leftNode = new Node(new Point(startNode.getLocation().x - 1, startNode.getLocation().y), food);
            double leftCost = leftNode.getCost();

            Node belowNode = new Node(new Point(startNode.getLocation().x, startNode.getLocation().y + 1), food);
            double belowCost = belowNode.getCost();

            if (rightCost < aboveCost && rightCost < leftCost && rightCost < belowCost) {
                return Direction.Right;
            } else if (aboveCost < rightCost && aboveCost < leftCost && aboveCost < belowCost) {
                return Direction.Up;
            } else if (leftCost < rightCost && leftCost < aboveCost && leftCost < belowCost) {
                return Direction.Left;
            } else if (belowCost < rightCost && belowCost < aboveCost && belowCost < leftCost) {
                return Direction.Down;
            }
        }
        return null;

    }
}
         */

