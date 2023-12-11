package smartsnake.astar.chambre;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AstarBrain implements Brain {
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        Node pointA = new Node(snake.getHeadLocation(), food);
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();
        open.add(pointA);

        while (true) {
            Node current = getLowestCost(open);
            open.remove(current);
            closed.add(current);

            Point location = current.getLocation();

            if (location.equals(food)) {
                return current.getDirectionFromStart();
            }

            Point upPoint = new Point(location.x, location.y - 1);
            updateDirection(upPoint, Direction.Up, food, open, closed, snake, current, garden);
            Point downPoint = new Point(location.x, location.y + 1);
            updateDirection(downPoint, Direction.Down, food, open, closed, snake, current, garden);
            Point leftPoint = new Point(location.x - 1, location.y);
            updateDirection(leftPoint, Direction.Left, food, open, closed, snake, current, garden);
            Point rightPoint = new Point(location.x + 1, location.y);
            updateDirection(rightPoint, Direction.Right, food, open, closed, snake, current, garden);
        }
    }

    public void updateDirection(Point point,
                                Direction direction,
                                Food food,
                                List<Node> open,
                                List<Node> closed,
                                Snake snake,
                                Node current,
                                Garden garden) {

        Node directionNode = new Node(point, food);
        if (garden.contains(directionNode.getLocation())) {
            if (open.contains(directionNode)) {
                directionNode = open.get(open.indexOf(directionNode));
            }
            if (snake.getSegments().contains(directionNode.getLocation()) || closed.contains(directionNode)) {
                return;
            }
            directionNode.setParent(current, direction);
            if (!open.contains(directionNode)) {
                open.add(directionNode);
            }
        }
    }

    public Node getLowestCost(List<Node> open) {
        Node lowestCost = open.get(0);

        for (Node point : open) {
            if (point.getCost() < lowestCost.getCost()) {
                lowestCost = point;
            }
        }
        return lowestCost;
    }
}
