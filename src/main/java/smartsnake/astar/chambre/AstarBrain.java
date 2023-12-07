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
        if (validate(directionNode, garden.getWidth(), garden.getHeight())) {
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

    private boolean validate(Node node, int width, int height) {
        return node.getLocation().x != width
                && node.getLocation().y != height
                && node.getLocation().x != -1
                && node.getLocation().y != -1;
    }

    public Node getLowestCost(List<Node> open) {
        int lowestCostIndex = 0;
        Node lowestCost = open.get(lowestCostIndex);

        for (int i = 0; i < open.size(); i++) {
            Node point = open.get(i);
            if (point.getCost() < lowestCost.getCost()) {
                lowestCostIndex = i;
                lowestCost = open.get(lowestCostIndex);
            }
        }
        return lowestCost;
    }
}
