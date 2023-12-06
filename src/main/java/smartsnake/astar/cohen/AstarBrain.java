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

        while(!open.isEmpty())
        {
            Node current = getLowestCostNode(open);
            open.remove(current);
            closed.add(current);

            if(current.getLocation().equals(food))
            {
                return current.getDirectionFromStart();
            }

            Node right = new Node(new Point(current.getLocation().x + 1, current.getLocation().y), food);
            Node left = new Node(new Point(current.getLocation().x - 1, current.getLocation().y), food);
            Node up = new Node(new Point(current.getLocation().x, current.getLocation().y - 1), food);
            Node down = new Node(new Point(current.getLocation().x, current.getLocation().y + 1), food);

            List<Node> neighbors = new ArrayList<>(List.of(left, right, up, down));
            List<String> directions = new ArrayList<>(List.of("Left", "Right", "Up", "Down"));

            for(Node neighbor: neighbors)
            {
                if(!closed.contains(neighbor))
                {
                    if(!open.contains(neighbor)) //neighbor.getFromEnd() < current.getFromEnd() ||
                    {
                        if(!open.contains(neighbor))
                        {
                            open.add(neighbor);
                        }
                        else
                        {
                            neighbor = open.get(open.indexOf(neighbor));
                        }
                        updateNodeParent(current, neighbor, directions.get(neighbors.indexOf(neighbor)));
                    }
                }
            }

            System.out.println(current.getDirectionFromStart());
        }
        return null;
    }

    @VisibleForTesting
    public static void updateNodeParent(Node current, Node neighbor, String direction)
    {
        neighbor.setParent(current, Direction.valueOf(direction));
    }

    @VisibleForTesting
    public void closeSnakeNodes(Snake snake, Food food, List<Node>closed)
    {
        List<Point>snakePoints = snake.getSegments();
        for (int i = 1; i < snakePoints.size(); i++)
        {
            closed.add(new Node(snakePoints.get(i).getLocation(), food.getLocation()));
        }
    }

    @VisibleForTesting
    public Node getLowestCostNode(List<Node> open)
    {
        int minCostIndex = 0;
        Node lowest = open.get(minCostIndex);

        for (int i = 0; i < open.size(); i++)
        {
            Node point = open.get(i);
            if(point.getCost() < lowest.getCost())
            {
                minCostIndex = i;
                lowest = open.get(minCostIndex);
            }
        }
        return lowest;
    }
}
