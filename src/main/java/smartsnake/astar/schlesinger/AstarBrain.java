package smartsnake.astar.schlesinger;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

//import static java.util.stream.Nodes.node;

public class AstarBrain implements Brain {

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        //Instantiating all variables being used
        Point currentNodeLoc = snake.getHeadLocation();

        //Current node
        Node currentNode = new Node(currentNodeLoc, food.getLocation());

        //setting the top, bottom, right, left points
        Point top = new Point(currentNodeLoc.x, currentNodeLoc.y + 1);
        Point bottom = new Point(currentNodeLoc.x, currentNodeLoc.y - 1);
        Point right = new Point(currentNodeLoc.x + 1, currentNodeLoc.y);
        Point left = new Point(currentNodeLoc.x - 1, currentNodeLoc.y);

        //setting the open and close-set
        Set<Node> open = new HashSet<>();
        Set<Point> closed = new HashSet<>();

        //adding the currentNodeLoc to close
        closed.add(currentNodeLoc);
        closed.addAll(snake.getSegments());

        //turning the neighbor points into nodes
        Node topNode = new Node(top, food.getLocation());
        Node bottomNode = new Node(bottom, food.getLocation());
        Node rightNode = new Node(right, food.getLocation());
        Node leftNode = new Node(left, food.getLocation());

        //Adding the current node to open
        open.add(currentNode);

        while (!open.isEmpty()) {
            open.remove(currentNode);
            closed.add(currentNodeLoc);
            //if the snake head has reached the food
            if (currentNodeLoc.equals(food.getLocation())) {
                currentNode.getPath();
            }
            //what is being traversed
            for (Node neighbor : open) {
                if (closed.contains(neighbor.getLocation())) {
                    continue;
                }
                if (neighbor.getCost() < currentNode.getCost() || !(open.contains(neighbor))) {
                    open.add(neighbor);
                    if (neighbor.equals(topNode)) {
                        neighbor.setParent(currentNode, Direction.Down);
                    }
                    if (neighbor.equals(bottomNode)) {
                        neighbor.setParent(currentNode, Direction.Up);
                    }
                    if (neighbor.equals(rightNode)) {
                        neighbor.setParent(currentNode, Direction.Left);
                    }
                    if (neighbor.equals(leftNode)) {
                        neighbor.setParent(currentNode, Direction.Right);
                    }

                }
            }
            if (currentNode.getParentDirection() == Direction.Down) {
                return Direction.Up;
            }
            if (currentNode.getParentDirection() == Direction.Up) {
                return Direction.Down;
            }
            if (currentNode.getParentDirection() == Direction.Left) {
                return Direction.Right;
            }
            if (currentNode.getParentDirection() == Direction.Right) {
                return Direction.Left;
            }
        }
        return null;
    }
}
