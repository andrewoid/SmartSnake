package smartsnake.astar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import smartsnake.Direction;

import java.awt.*;
import java.util.Objects;

/**
 * <a href="https://www.youtube.com/watch?v=-L-WgKMFuhE">A* Pathfinding</a>
 */
public class Node implements Comparable<Node> {

    /**
     * The cost added to "fromStart" when moving from one neighbor Node to another.
     */
    private static final int NEIGHBOR_COST = 10;

    private final Point location;

    private final Point endLocation;

    /**
     * Distance from the start Node, also called the G-COST
     */
    private double fromStart;

    /**
     * Distance from the end Node, also called the H-COST
     */
    private final double fromEnd;

    /**
     * The total cost of this node, also called the F-Cost.
     */
    private double cost;

    /**
     * The Node that leads to this Node with the smallest cost.
     */
    private Node parent;

    /**
     * The Direction to get from the parent Node to this Node.
     */
    private Direction parentDirection;

    public Node(Point nodeLocation, Point endLocation) {
        this.location = nodeLocation;
        this.endLocation = endLocation;
        this.fromEnd = DistanceFormula.distance(this.location, endLocation);
        cost = fromEnd;
    }

    public Point getLocation() {
        return location;
    }

    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent of this node if it would lower it's "fromStart" value.
     */
    public void setParent(Node parent, Direction parentDirection) {
        if (fromStart == 0 || parent.fromStart + NEIGHBOR_COST < fromStart) {
            this.parent = parent;
            this.parentDirection = parentDirection;
            this.fromStart = parent.fromStart + NEIGHBOR_COST;
            cost = fromStart + fromEnd;
        }
    }

    public double getCost() {
        return cost;
    }

    public double getFromStart() {
        return fromStart;
    }

    public double getFromEnd() {
        return fromEnd;
    }

    @Override
    public String toString() {
        return "Node{"
                + "location=" + location
                + ", fromStart=" + fromStart
                + ", fromEnd=" + fromEnd
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return location.equals(node.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    public Direction getParentDirection() {
        return parentDirection;
    }

    /**
     * @return the Direction that was taken to get to this Node from the starting Node.
     */
    public @Nullable Direction getDirectionFromStart() {
        if (parent == null) {
            return null;
        }
        Node node = this;
        while (node.parent.parent != null) {
            node = node.parent;
        }
        return node.parentDirection;
    }

    @Override
    public int compareTo(@NotNull Node node) {
        return (int) (this.getCost() - node.getCost());
    }
}
