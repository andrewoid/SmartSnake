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
        Point currentNodeLoc = snake.getHeadLocation();

        //Current node
        Node startNode = new Node(currentNodeLoc, food.getLocation());

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
        Node topNode = new Node(top, food);
        Node bottomNode = new Node(bottom, food);
        Node rightNode = new Node(right, food);
        Node leftNode = new Node(left, food);

        //Adding the current node to open
        open.add(startNode);


        while (!open.isEmpty()) {

            Node current = new Node(snake.getHeadLocation(), food);
            Point currentLoc = current.getLocation();
            open.remove(current);
            closed.add(currentNodeLoc);
            //if the snake head has reached the food
            if (currentLoc.equals(food.getLocation())) {
                closed.add(currentLoc);
                return current.getDirectionFromStart();
            }
            //what is being traversed
            for (Node neighbor : open) {
                if (closed.contains(neighbor.getLocation())) {
                    continue;
                }
                if (neighbor.getCost() < current.getCost() || !(open.contains(neighbor))) {
                    current = neighbor;
                    open.add(neighbor);
                    if (neighbor.equals(topNode)) {
                        neighbor.setParent(current, Direction.Down);
                        closed.add(top);
                    }
                    if (neighbor.equals(bottomNode)) {
                        neighbor.setParent(current, Direction.Up);
                        closed.add(bottom);
                    }
                    if (neighbor.equals(rightNode)) {
                        neighbor.setParent(current, Direction.Left);
                        closed.add(right);
                    }
                    if (neighbor.equals(leftNode)) {
                        neighbor.setParent(current, Direction.Right);
                        closed.add(left);
                    }

                }
            }
        }
        return null;
    }
}
