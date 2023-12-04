package smartsnake.astar.schlesinger;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//import static java.util.stream.Nodes.node;

public class AstarBrain implements Brain {


    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        //Instantiating all variables being used
        Point startNodeLoc = snake.getHeadLocation();

        //Start node
        Node startNode = new Node(startNodeLoc, food);

        //Setting my current, which starts off as start node
        Node current = startNode;

        //setting the top, bottom, right, left points
        Point top = new Point(startNodeLoc.x, startNodeLoc.y + 1);
        Point bottom = new Point(startNodeLoc.x, startNodeLoc.y - 1);
        Point right = new Point(startNodeLoc.x + 1, startNodeLoc.y);
        Point left = new Point(startNodeLoc.x - 1, startNodeLoc.y);

        //setting the open and close-set
        Set<Node> open = new HashSet<>();
        Set<Point> closed = new HashSet<>();

        //adding the startNodeLoc to close
        open.add(startNode);
        closed.addAll(snake.getSegments());

        //turning the neighbor points into nodes
        Node topNode = new Node(top, food);
        Node bottomNode = new Node(bottom, food);
        Node rightNode = new Node(right, food);
        Node leftNode = new Node(left, food);
        //Adding the current node to open
        open.add(startNode);

        while (!open.isEmpty()) {
            //iterating through open to find the lowest f cost
            for (Node node : open) {
                if (node.getCost() < current.getCost() || node.getCost() == current.getCost()) {
                    current = node;
                }
            }
            closed.add(current.getLocation());
            //if the snake head has reached the food
            if (current.getLocation().equals(food.getLocation())) {
                return current.getDirectionFromStart();
            }
            //what is being traversed
            for (Node neighbor : open) {
                if (closed.contains(neighbor.getLocation())) {
                    continue;
                }
                if (neighbor.getFromStart() < current.getFromStart() || !(open.contains(neighbor))) {
                    open.add(neighbor);
                    if (neighbor.equals(topNode)) {
                        neighbor.setParent(current, Direction.Down);
                    }
                    if (neighbor.equals(bottomNode)) {
                        neighbor.setParent(current, Direction.Up);
                    }
                    if (neighbor.equals(rightNode)) {
                        neighbor.setParent(current, Direction.Left);
                    }
                    if (neighbor.equals(leftNode)) {
                        neighbor.setParent(current, Direction.Right);
                    }
                }
            }
        }
        return null;
    }
}
