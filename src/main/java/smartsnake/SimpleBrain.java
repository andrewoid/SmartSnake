package smartsnake;

/**
 * An implementation of Brain that just returns the current Direction of the Snake.
 */
public class SimpleBrain implements Brain {
    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        return snake.getDirection();
    }
}
