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
        Assertions.assertEquals(resultPoint, snakeSegments.get(0));
    }

    @Test
    public void starved() {
        // given

        // when

        // then
    }

    @Test
    public void tick() {
        // given

        // when

        // then
    }

    @Test
    public void setDirection() {
        // given

        // when

        // then
    }

    @Test
    public void intersects() {
        // given

        // when

        // then
    }

    @Test
    public void intersectsItself() {
        // given

        // when

        // then
    }

    @Test
    public void intersectsTail() {
        // given

        // when

        // then
    }
}