package smartsnake.astar.halberstam;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AstarBrain implements Brain {
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        ArrayList<Node> openNodes = new ArrayList<>();
        Set<Node> closeNodes = new HashSet<>();

        Node start = new Node(snake.getHeadLocation(), food);
        openNodes.add(start);

        //start at 1 so I skip the head
        for (int i = 1; i < snake.getSegments().size(); i++)
        {
            Node node = new Node(snake.getSegments().get(i), food);
            closeNodes.add(node);
        }

        while (!openNodes.isEmpty()) {
            Node current = openNodes.get(0);

            for (Node node : openNodes) {
                if (node.getCost() <= current.getCost()) {
                    current = node;
                }
            }

            openNodes.remove(current);
            closeNodes.add(current);

            if (current.getLocation().equals(food)) {
                return current.getDirectionFromStart();
            }

            ArrayList<Node> neighbors = new ArrayList<>();
            Point location = current.getLocation();

            checkingNeighbors(current, new Node(new Point(location.x, location.y - 1), food),
                    Direction.Up, closeNodes, openNodes, garden);
            checkingNeighbors(current, new Node(new Point(location.x, location.y + 1), food),
                    Direction.Down, closeNodes, openNodes, garden);
            checkingNeighbors(current, new Node(new Point(location.x + 1, location.y), food),
                    Direction.Right, closeNodes, openNodes, garden);
            checkingNeighbors(current, new Node(new Point(location.x - 1, location.y), food),
                    Direction.Left, closeNodes, openNodes, garden);
        }

        return snake.getDirection();
    }

    private void checkingNeighbors(Node current, Node neighbor, Direction direction,
                                   Set<Node> closeNodes, ArrayList<Node> openNodes, Garden garden)
    {     if (garden.contains(neighbor.getLocation()) && !closeNodes.contains(neighbor)) {
                int neighborIndex = openNodes.indexOf(neighbor);
                if (neighborIndex == -1)
                {
                    openNodes.add(neighbor);
                    neighbor.setParent(current, direction);
                } else {
                    openNodes.get(neighborIndex).setParent(current, direction);
                }
            }
    }
}