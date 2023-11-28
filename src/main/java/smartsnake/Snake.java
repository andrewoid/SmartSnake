package smartsnake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kenigsberg, Halberstam
 */
public class Snake {

    public static final int STARVE_LIMIT = 50;
    private List<Point> snake;
    private Direction direction;
    private int starveTicks;

    public Snake() {
        //this depends on how the initial snake setup will be
        snake = new ArrayList<>();
        snake.add(new Point(10, 5));
        snake.add(new Point(9, 5));
        snake.add(new Point(8, 5));

        direction = Direction.Right;

        starveTicks = 0;
    }

    /**
     * @return the number of segments in the Snake.
     */
    public List<Point> getSegments() {
        return snake;
    }

    /**
     * @return true if the Snake hasn't eaten for STARVE_LIMIT, otherwise false
     */
    public boolean starved() {
        return starveTicks == STARVE_LIMIT;
    }

    /**
     * Moves the Snake one square in the direction it's facing.
     *
     * @param grow true if the Snake should grow as it moves, otherwise false
     */
    public void tick(boolean grow) {
        Point facing = getHeadLocation();
        if (this.direction == Direction.Right) {
            snake.add(0, new Point((int) (facing.getX() + 1), (int) facing.getY()));
        } else if (this.direction == Direction.Left) {
            snake.add(0, new Point((int) (facing.getX() - 1), (int) (facing.getY())));
        } else if (this.direction == Direction.Up) {
            snake.add(0, new Point((int) (facing.getX()), (int) (facing.getY() + 1)));
        } else if (this.direction == Direction.Down) {
            snake.add(0, new Point((int) (facing.getX()), (int) (facing.getY() - 1)));
        }
        if (grow) {
            starveTicks = 0; // reset it if the snake eats
        } else {
            snake.remove(snake.size() - 1);
            starveTicks++;
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getHeadLocation() {
        return snake.get(0);
    }

    /**
     * @param food
     * @return true if the head intersects any part of the food, otherwise false
     */
    public boolean intersectsHead(Food food) {
        return getHeadLocation().equals(food);
    }

    /**
     * @return true if the head intersects any part of the tail, otherwise false.
     */
    public boolean intersectsItself() {
        Point headLocation = getHeadLocation();
        for (int i = 1; i < snake.size(); i++) {
            if (headLocation.equals(snake.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return true if the Food intersects any part of the Snake, otherwise false.
     */
    public boolean intersects(Food food) {
        return snake.contains(food);
    }
}