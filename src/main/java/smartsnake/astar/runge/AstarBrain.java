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

            Point point = current.getLocation();

            Node up = new Node(new Point(point.x, point.y - 1), food);
            updateNeighborhood(current, up, Direction.Up, openNodes, closedNodes, garden);

            Node down = new Node(new Point(point.x, point.y + 1), food);
            updateNeighborhood(current, down, Direction.Down, openNodes, closedNodes, garden);

            Node right = new Node(new Point(point.x + 1, point.y), food);
            updateNeighborhood(current, right, Direction.Right, openNodes, closedNodes, garden);

            Node left = new Node(new Point(point.x - 1, point.y), food);
            updateNeighborhood(current, left, Direction.Left, openNodes, closedNodes, garden);

            if (point.equals(food))
            {
                return current.getDirectionFromStart();
            }

        }
        return null;
    }

    public Node getLowestCost(List<Node> openNodes) {
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


    public void updateNeighborhood(Node current,
                                   Node neighbor,
                                   Direction direction,
                                   List<Node> openNodes,
                                   List<Node> closedNodes,
                                   Garden garden) {

        if (!closedNodes.contains(neighbor) && (garden.contains(current.getLocation())))
        {
            if (!openNodes.contains(neighbor))
            {
                neighbor.setParent(current, direction);
                openNodes.add(neighbor);
            } else
            {
                openNodes.get(openNodes.indexOf(neighbor)).setParent(current, direction);
            }
        }
    }
}
