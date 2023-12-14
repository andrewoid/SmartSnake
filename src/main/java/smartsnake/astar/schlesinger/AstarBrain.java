package smartsnake.astar.schlesinger;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AstarBrain implements Brain {


    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        //Instantiating all variables being used
        Point startNodeLoc = snake.getHeadLocation();

        //Start node
        Node startNode = new Node(startNodeLoc, food);


        //setting the open and close array lists, and the ones used to hold the neighbors
        ArrayList<Node> open = new ArrayList<>();
        Set<Point> closed = new HashSet<>();
        ArrayList<Node> neighbors = new ArrayList<>();

        //adding the start node to open
        open.add(startNode);
        //adding everything besides for the snakes head to the closed list -  they are barriers
        closed.addAll(snake.getSegments());
        closed.remove(snake.getHeadLocation());

        //Starting the loop while something is contained in open
        while (!open.isEmpty()) {
            //Setting it to a random point just for the beginning. it will be
            //replaced by the lowest cost either way.
            Node lowestCost = open.get(0);
            //iterating through open to find the lowest f cost
            for (Node node : open) {
                if (node.getCost() <= lowestCost.getCost()) {
                    lowestCost = node;
                }
            }
            //setting current to be to the node with the lowest f cost
            Node current = lowestCost;
            //since it is the lowest, remove from open and:
            open.remove(current);
            //add it to closed
            closed.add(current.getLocation());

            //if the snake head has reached the food
            if (current.getLocation().equals(food)) {
                return current.getDirectionFromStart();
            }

            //this is the current location of the snakes head, which keeps moving based on lowest cost
            Point currentLocation = current.getLocation();
            //setting the top, bottom, right, left points
            Point top = new Point(currentLocation.x, currentLocation.y - 1);
            Point bottom = new Point(currentLocation.x, currentLocation.y + 1);
            Point right = new Point(currentLocation.x + 1, currentLocation.y);
            Point left = new Point(currentLocation.x - 1, currentLocation.y);

            //turning them into nodes
            Node topNode = new Node(top, food);
            Node bottomNode = new Node(bottom, food);
            Node rightNode = new Node(right, food);
            Node leftNode = new Node(left, food);

            //Adding the neighbors to the neighbors array list to be transversed.
            neighbors.add(topNode);
            neighbors.add(bottomNode);
            neighbors.add(rightNode);
            neighbors.add(leftNode);

            //what is being traversed
            for (Node neighbor : neighbors) {
                if (closed.contains(neighbor.getLocation()) || !(garden.contains(neighbor.getLocation()))) {
                    continue;
                }

                //Setting new parents to neighbors that are already in open
                if (open.contains(neighbor)) {

                    if (neighbor.equals(topNode)) {
                        open.get(open.indexOf(neighbor)).setParent(current, Direction.Up);
                    }
                    if (neighbor.equals(bottomNode)) {
                        open.get(open.indexOf(neighbor)).setParent(current, Direction.Down);
                    }
                    if (neighbor.equals(rightNode)) {
                        open.get(open.indexOf(neighbor)).setParent(current, Direction.Right);
                    }
                    if (neighbor.equals(leftNode)) {
                        open.get(open.indexOf(neighbor)).setParent(current, Direction.Left);
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
