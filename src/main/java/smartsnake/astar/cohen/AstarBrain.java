package smartsnake.astar.cohen;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.VisibleForTesting;
import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AstarBrain implements Brain
{
    @Override
    public @Nullable Direction move(Snake snake, Food food, Garden garden)
    {
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        Node start = new Node(snake.getHeadLocation(), food.getLocation());
        open.add(start);

        closeSnakeNodes(snake, food, closed);

        while (!open.isEmpty())
        {
            Node current = getLowestCostNode(open);
            open.remove(current);
            closed.add(current);

            Point currentLocation = current.getLocation();
            if (currentLocation.equals(food))
            {
                return current.getDirectionFromStart();
            }

            updateGardenWithNeighboringPositions(food, garden, open, closed, current, currentLocation);
        }
        return null;
    }

    private static void updateGardenWithNeighboringPositions(Food food,
                                                             Garden garden,
                                                             List<Node> open,
                                                             List<Node> closed,
                                                             Node current,
                                                             Point currentLocation)
    {
        Node right = new Node(new Point(currentLocation.x + 1, currentLocation.y), food);
        updateNodeParent(current, right, Direction.Right, open, closed, garden);
        Node left = new Node(new Point(currentLocation.x - 1, currentLocation.y), food);
        updateNodeParent(current, left, Direction.Left, open, closed, garden);
        Node up = new Node(new Point(currentLocation.x, currentLocation.y - 1), food);
        updateNodeParent(current, up, Direction.Up, open, closed, garden);
        Node down = new Node(new Point(currentLocation.x, currentLocation.y + 1), food);
        updateNodeParent(current, down, Direction.Down, open, closed, garden);
    }

    @VisibleForTesting
    public static void updateNodeParent(Node current,
                                        Node neighbor,
                                        Direction direction,
                                        List<Node> open,
                                        List<Node> closed,
                                        Garden garden)
    {
        if (!closed.contains(neighbor) && garden.contains(neighbor.getLocation()))
        {
            if (!open.contains(neighbor))
            {
                open.add(neighbor);
            } else
            {
                neighbor = open.get(open.indexOf(neighbor));
            }
            neighbor.setParent(current, direction);
        }
    }

    @VisibleForTesting
    public void closeSnakeNodes(Snake snake, Food food, List<Node> closed)
    {
        List<Point> snakePoints = snake.getSegments();
        for (int i = 1; i < snakePoints.size(); i++)
        {
            closed.add(new Node(snakePoints.get(i).getLocation(), food.getLocation()));
        }
    }

    @VisibleForTesting
    public Node getLowestCostNode(List<Node> open)
    {
        int minCostIndex = 0;

        for (int i = 0; i < open.size(); i++)
        {
            Node point = open.get(i);
            if (point.getCost() < open.get(minCostIndex).getCost())
            {
                minCostIndex = i;
            }
        }
        return open.get(minCostIndex);
    }
}
