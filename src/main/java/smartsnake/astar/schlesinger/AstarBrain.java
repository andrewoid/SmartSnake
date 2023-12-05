package smartsnake.astar.schlesinger;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
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


        //setting the open and close-set
        ArrayList<Node> open = new ArrayList<>();
        ArrayList<Point> closed = new ArrayList<>();
        ArrayList<Node> neighbors = new ArrayList<>();

        open.add(startNode);
        closed.addAll(snake.getSegments());
        closed.remove(snake.getHeadLocation());

        //turning the neighbor points into nodes


        while (!open.isEmpty()) {
            Node lowestCost = open.get(0);
            //iterating through open to find the lowest f cost
            for (Node node : open) {
                if (node.getCost() <= lowestCost.getCost()) {
                    lowestCost = node;
                }
            }
            Node current = lowestCost;
            open.remove(current);
            closed.add(current.getLocation());

            //if the snake head has reached the food
            if (current.getLocation().equals(food)) {
                return current.getDirectionFromStart();
            }

            Point currentLocation = current.getLocation();
            //setting the top, bottom, right, left points
            Point top = new Point(currentLocation.x, currentLocation.y + 1);
            Point bottom = new Point(currentLocation.x, currentLocation.y - 1);
            Point right = new Point(currentLocation.x + 1, currentLocation.y);
            Point left = new Point(currentLocation.x - 1, currentLocation.y);

            Node topNode = new Node(top, food);
            Node bottomNode = new Node(bottom, food);
            Node rightNode = new Node(right, food);
            Node leftNode = new Node(left, food);

            neighbors.add(topNode);
            neighbors.add(bottomNode);
            neighbors.add(rightNode);
            neighbors.add(leftNode);


            //what is being traversed
            for (Node neighbor : neighbors) {
                if (closed.contains(neighbor.getLocation())) {
                    continue;
                }

                //Setting new parents to neighbors that are already in open
                if (open.contains(neighbor)) {

                    if (neighbor.equals(topNode)) {
                        neighbor.setParent(current, Direction.Up);
                    }
                    if (neighbor.equals(bottomNode)) {
                        neighbor.setParent(current, Direction.Down);
                    }
                    if (neighbor.equals(rightNode)) {
                        neighbor.setParent(current, Direction.Right);
                    }
                    if (neighbor.equals(leftNode)) {
                        neighbor.setParent(current, Direction.Left);
                    }

                } else {

                    if (neighbor.equals(topNode)) {
                        neighbor.setParent(current, Direction.Up);
                        open.add(neighbor);
                    }
                    if (neighbor.equals(bottomNode)) {
                        neighbor.setParent(current, Direction.Down);
                        open.add(neighbor);
                    }
                    if (neighbor.equals(rightNode)) {
                        neighbor.setParent(current, Direction.Right);
                        open.add(neighbor);
                    }
                    if (neighbor.equals(leftNode)) {
                        neighbor.setParent(current, Direction.Left);
                        open.add(neighbor);
                    }
                }
            }
            neighbors.clear();
        }
        return null;
    }
}
