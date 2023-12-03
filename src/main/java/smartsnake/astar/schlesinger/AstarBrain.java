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

        //setting the top, bottom, right, left nodes
        Point top = new Point(currentNodeLoc.x, currentNodeLoc.y + 1);
        Point bottom = new Point(currentNodeLoc.x, currentNodeLoc.y - 1);
        Point right = new Point(currentNodeLoc.x + 1 , currentNodeLoc.y);
        Point left = new Point(currentNodeLoc.x - 1, currentNodeLoc.y);

        //setting the open and close-set
        Set<Node> open = new HashSet<>();
        Set<Point> closed = new HashSet<>();

        //adding the currentNodeLoc to close, and neighbors to open
        closed.add(currentNodeLoc);
        closed.addAll(snake.getSegments());

        //turning the neighbor points into nodes
        Node topNode = new Node(top, food.getLocation());
        Node bottomNode = new Node(bottom, food.getLocation());
        Node rightNode = new Node(right, food.getLocation());
        Node leftNode = new Node(left, food.getLocation());

        //Adding the current node to open
        open.add(currentNode);

        //if the snake head has reached the food
        if (currentNodeLoc.equals(food.getLocation())){
            return null;
        }

        if (topNode.getCost() < currentNode.getCost()){
            if(closed.contains(topNode.getLocation())){
                top.move(top.x, top.y + 1);
            }
            closed.add(topNode.getLocation());
            topNode.setParent(currentNode, Direction.Down);
            return Direction.Up;
        }else if(bottomNode.getCost() < currentNode.getCost()){
            if(closed.contains(bottomNode.getLocation())){
                bottom.move(top.x, top.y - 1);
            }
            closed.add(bottomNode.getLocation());
            topNode.setParent(currentNode, Direction.Up);
            return Direction.Down;
        }else if (leftNode.getCost() < currentNode.getCost()){
            if(closed.contains(leftNode.getLocation())){
                top.move(top.x - 1, top.y);
            }
            closed.add(leftNode.getLocation());
            topNode.setParent(currentNode, Direction.Right);
            return Direction.Left;
        }else if (rightNode.getCost() < currentNode.getCost()){
            if(closed.contains(rightNode.getLocation())){
                top.move(top.x + 1, top.y);
            }
            closed.add(rightNode.getLocation());
            topNode.setParent(currentNode, Direction.Left);
            return Direction.Right;
        }

        return null;
    }
}
