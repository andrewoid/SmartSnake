package smartsnake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

    @Test
    public void getSegments() {
        // given
        Snake snake = new Snake();
        Point resultPoint = new Point(10, 5);

        // when
        List<Point> snakeSegments = snake.getSegments();

        // then
        assertEquals(resultPoint, snakeSegments.get(0));
    }

    @Test
    public void starved() {
        // given
        Snake snake = new Snake();

        // when
        boolean isNotStarved = snake.starved();

        // then
        assertFalse(isNotStarved);
    }

    @Test
    public void tick() {
        // given
        Snake snake = new Snake();

        // when
        snake.tick(true); //direction is currently right

        // then
        assertEquals(new Point(11, 5), snake.getHeadLocation());
        assertEquals(new Point(8, 5), snake.getSegments().get(snake.getSegments().size() - 1));
    }

    @Test
    public void tickFalse() {
        // given
        Snake snake = new Snake();

        // when
        snake.tick(false); //direction is currently right

        // then
        assertEquals(new Point(11, 5), snake.getHeadLocation());
        assertEquals(new Point(10, 5), snake.getSegments().get(1));
        assertEquals(new Point(9, 5), snake.getSegments().get(snake.getSegments().size() - 1));
    }

    @Test
    public void intersectsHead() {
        // given
        Snake snake = new Snake();

        // when
        boolean intersects = snake.intersects(new Food(10, 5));

        // then
        assertTrue(intersects);
    }

    @Test
    public void intersectsItself() {
        // given
        Snake snake = new Snake();

        // when
        boolean intersectSelf = snake.intersectsItself();

        // then
        assertFalse(intersectSelf);
    }

    @Test
    public void intersects() {
        // given
        Snake snake = new Snake();

        // when
        boolean intersects = snake.intersects(new Food(9, 5));

        // then
        assertTrue(intersects);
    }
}