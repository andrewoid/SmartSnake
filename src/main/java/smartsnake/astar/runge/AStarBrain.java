package smartsnake.astar.runge;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AStarBrain implements Brain
{
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        List<Node> openNodes = new ArrayList<>();
        List<Node> closedNodes = new ArrayList<>();

        Node startNode = new Node(snake.getHeadLocation(), food);
        openNodes.add(startNode);

        List<Point> snakesBody = snake.getSegments();
        for (int i = 0; i < snake.getSegments().size(); i++)
        {
            closedNodes.add(new Node(snakesBody.get(i), food));
        }

        while (!openNodes.isEmpty())
        {
            checkOutOfBounds(garden, snake, garden.getWidth(), garden.getWidth());

            Node current = getLowestFCost(openNodes);
            openNodes.remove(current);
            closedNodes.add(current);

            Point currentLocation = current.getLocation();
            if (currentLocation.equals(food))
            {
                return current.getDirectionFromStart();
            }

            Node up = new Node(new Point(current.getLocation().x, current.getLocation().y + 1), food);
            Node down = new Node(new Point(current.getLocation().x, current.getLocation().y - 1), food);
            Node right = new Node(new Point(current.getLocation().x + 1, current.getLocation().y), food);
            Node left = new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food);
            Node[] neighborhood = {up, down, right, left};

            for (Node neighbor : neighborhood)
            {
                neighbor.setParent(current, current.getParentDirection());

                if (snake.intersectsItself())
                {
                    break;
                }

                Direction result;
                result = updateNeighborhood(current, up, Direction.Up, openNodes, closedNodes);
                if (result != null)
                {
                    return result;
                }

                result = updateNeighborhood(current, down, Direction.Down, openNodes, closedNodes);
                if (result != null)
                {
                    return result;
                }

                result = updateNeighborhood(current, right, Direction.Right, openNodes, closedNodes);
                if (result != null)
                {
                    return result;
                }

                result = updateNeighborhood(current, left, Direction.Left, openNodes, closedNodes);
                if (result != null)
                {
                    return result;
                }
            }
        }
        return null;
    }

    private Node getLowestFCost(List<Node> openNodes) {
        Node lowestCost = openNodes.get(0);
        for (Node node : openNodes)
        {
            if (node.getCost() < lowestCost.getCost())
            {
                lowestCost = node;
            }
        }
        return lowestCost;
    }

    private Direction updateNeighborhood(Node current, Node neighbor, Direction direction,
                                         List<Node> openNodes, List<Node> closedNodes) {
        if (!openNodes.contains(neighbor)
                || (neighbor.getCost() < current.getCost())
                && neighbor.getPath().size() < current.getPath().size())
        {
            neighbor.setParent(current, direction);
            openNodes.add(neighbor);
            if (current.getParentDirection() == direction)
            {
                return direction;
            }
        } else
        {
            closedNodes.add(neighbor);
        }
        return null;
    }

    private void checkOutOfBounds(Garden garden, Snake snake, int width, int height) {
        width = garden.getWidth();
        height = garden.getHeight();
        if (snake.getHeadLocation().x == width
                || snake.getHeadLocation().y == height
                || snake.getHeadLocation().x == -1
                || snake.getHeadLocation().y == -1)
        {
            snake.tick(false);
        }
    }
}

/*

 */
