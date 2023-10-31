package smartsnake;

import java.awt.*;

public class Snake {

    public static final int STARVE_LIMIT = 50;

    /**
     * @return the number of segments in the Snake.
     */
    public int getSegments() {
        return 0;
    }

    /**
     * @return true if the Snake hasn't eaten for STARVE_LIMIT, otherwise false
     */
    boolean starved() {
        return false;
    }

    /**
     * Moves the Snake one square in the direction it's facing.
     */
    public void tick() {

    }

    public void setDirection(Direction direction) {

    }

    public Point getLocation() {
        return null;
    }

    /**
     *
     * @param food
     * @return true if the head intersects any part of the food, otherwise false
     */
    public boolean intersects(Food food) {
        return false;
    }

    /**
     * @return if the head intersects any part of the tail, otherwise false.
     */
    public boolean intersectsItself() {
        return false;
    }

    /**
     * @return if the Food intersects any part of the tail, otherwise false.
     */
    public boolean intersectsTail(Food food) {
        return false;
    }

}
