package smartsnake;

import java.awt.*;
import java.util.List;

/**
 * Kenigsberg, Halberstam
 */
public class Snake {

    public static final int STARVE_LIMIT = 50;

    /**
     * @return the number of segments in the Snake.
     */
    public List<Point> getSegments() {
        return null;
    }

    /**
     * @return true if the Snake hasn't eaten for STARVE_LIMIT, otherwise false
     */
    boolean starved() {
        return false;
    }

    /**
     * Moves the Snake one square in the direction it's facing.
     *
     * @param grow true if he Snake should grow as it moves, otherwise false
     */
    public void tick(boolean grow) {

    }

    public void setDirection(Direction direction) {

    }

    public Point getHeadLocation() {
        return null;
    }

    /**
     * @param food
     * @return true if the head intersects any part of the food, otherwise false
     */
    public boolean intersectsHead(Food food) {
        return false;
    }

    /**
     * @return true if the head intersects any part of the tail, otherwise false.
     */
    public boolean intersectsItself() {
        return false;
    }

    /**
     * @return true if the Food intersects any part of the Snake, otherwise false.
     */
    public boolean intersects(Food food) {
        return false;
    }

}
