package smartsnake.astar.chambre;

import smartsnake.*;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static smartsnake.astar.Node.NEIGHBOR_COST;

public class AstarBrain implements Brain {
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        //determine what direction the snake should move via implementing A*
        //return direction snake should move

        //Point A is snake.getHeadLocation()
        Node pointA = new Node(snake.getHeadLocation(), food);
        //Point B is food

        //Open List- set of nodes to be evaluated
        List<Node> open = new ArrayList<>();
        //Closed List- set of nodes already evaluated
        List<Node> closed = new ArrayList<>();

        //add the start node to open
        open.add(pointA);

        //loop
        while (true) {

            //current node = node in open with lowest f-cost
            Node current = getLowestCost(open);
            //remove current from open
            open.remove(current);
            //add current to closed
            closed.add(current);

            Point location = current.getLocation();

            //IF current is the target node
            if (location.equals(food)) {
                return current.getDirectionFromStart();
            }

            //foreach neighbor of the current node
            // if one of the neighbors in in open then update the node to be that one

            Node up = new Node(new Point(location.x, location.y - 1), food);
            if(open.contains(up)){

            }

            Node down = new Node(new Point(location.x, location.y + 1), food);
            Node left = new Node(new Point(location.x - 1, location.y), food);
            Node right = new Node(new Point(location.x + 1, location.y), food);

            List<Node> neighbors = new ArrayList<>(List.of(right, left, up, down));

            for (Node neighbor : neighbors) {
                //if the neighbor is not traversable or neighbor is in closed
                if (snake.getSegments().contains(neighbor.getLocation()) || closed.contains(neighbor)) {
                    continue;
                }

                //set parent of neighbour to current
                neighbor.setParent(current, current.getParentDirection());
                //if neighbour is not in open
                if (!open.contains(neighbor)) {
                    //add neighbour to open
                    open.add(neighbor);
                }
            }
        }
    }

}

    private static Node getLowestCost(List<Node> open) {
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
