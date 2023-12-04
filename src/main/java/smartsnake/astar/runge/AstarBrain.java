package smartsnake.astar.runge;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AstarBrain implements Brain
{
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        List<Node> openNodes = new ArrayList<>();
        List<Node> closedNodes = new ArrayList<>();

        Node startNode = new Node(snake.getHeadLocation(), food);
        openNodes.add(startNode);

        List<Point> snakesBody = snake.getSegments();
        for (int i = 1; i < snake.getSegments().size(); i++)
        {
            closedNodes.add(new Node(snakesBody.get(i), food));
        }

        while (!openNodes.isEmpty())
        {
            Node current = getLowestCost(openNodes);
            openNodes.remove(current);
            closedNodes.add(current);

            /*
            Point currentLocation = current.getLocation();
            if (currentLocation.equals(food))
            {
                return current.getDirectionFromStart();
            }
            */

            Node up = new Node(new Point(current.getLocation().x, current.getLocation().y + 1), food);
            Node down = new Node(new Point(current.getLocation().x, current.getLocation().y - 1), food);
            Node right = new Node(new Point(current.getLocation().x + 1, current.getLocation().y), food);
            Node left = new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food);
            Node[] neighborhood = {up, down, right, left};


            for (Node neighbor : neighborhood)
            {
                Point currentLocation = current.getLocation();
                if (currentLocation.equals(food))
                {
                    return current.getDirectionFromStart();
                }

                //If a Node for this Point exists within openNodes, you need to get the Node from openNodes
                if (!openNodes.contains(up)
                        || !openNodes.contains(down)
                        || !openNodes.contains(right)
                        || !openNodes.contains(left))
                {
                    openNodes.add(neighbor);
                    neighbor.setParent(current, current.getParentDirection());
                } else
                {
                    //find which index in open nodes the node is and then set the parent for that node
                    neighbor.setParent(current, current.getParentDirection());

                    openNodes.get(openNodes.indexOf(neighbor)).setParent(current, neighbor.getParentDirection());
                }
                updateNeighborhood(current, up, Direction.Up, openNodes, closedNodes);

                updateNeighborhood(current, down, Direction.Down, openNodes, closedNodes);

                updateNeighborhood(current, right, Direction.Right, openNodes, closedNodes);

                updateNeighborhood(current, left, Direction.Left, openNodes, closedNodes);

            }
        }
        return null;
    }

    private Node getLowestCost(List<Node> openNodes) {
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

    private void updateNeighborhood(Node current, Node neighbor, Direction direction,
                                    List<Node> openNodes, List<Node> closedNodes) {
        if (!closedNodes.contains(neighbor))
        {
            if (!openNodes.contains(neighbor))
            {
                neighbor.setParent(current, direction);
                openNodes.add(neighbor);
            }
        } else
        {
            openNodes.add(neighbor);
            openNodes.get(openNodes.indexOf(neighbor)).setParent(current, direction);

        }
    }
}