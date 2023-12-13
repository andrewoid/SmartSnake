package smartsnake.astar.halberstam;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;

public class AstarBrain implements Brain {
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closeNodes = new ArrayList<>();

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
            Node up = new Node(new Point(location.x, location.y - 1), food);
            Node down = new Node(new Point(location.x, location.y + 1), food);
            Node right = new Node(new Point(location.x + 1, location.y), food);
            Node left = new Node(new Point(location.x - 1, location.y), food);

            neighbors.add(up);
            neighbors.add(down);
            neighbors.add(right);
            neighbors.add(left);


            for (int i = 0; i < neighbors.size(); i++) {
                Node neighbor = neighbors.get(i);
                if (!garden.contains(neighbor.getLocation()) || closeNodes.contains(neighbor)) {
                    continue;
                } else {
                    Direction direction = null;
                    switch (i) {
                        case 0:
                            direction = Direction.Up;
                            break;
                        case 1:
                            direction = Direction.Down;
                            break;
                        case 2:
                            direction = Direction.Right;
                            break;
                        case 3:
                            direction = Direction.Left;
                            break;
                        default:
                            continue;
                    }
                    if (!openNodes.contains(neighbor))
                    {
                        openNodes.add(neighbor);
                        neighbor.setParent(current, direction);
                    } else {
                        openNodes.get(openNodes.indexOf(neighbor)).setParent(current, direction);
                    }
                }

            }
        }

        return snake.getDirection();
    }
}