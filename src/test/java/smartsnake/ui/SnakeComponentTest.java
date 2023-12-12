package smartsnake.ui;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SnakeComponentTest {
    private static final int SQUARE_SIZE = 10;
    private final Garden garden = mock();
    private final Graphics graphics = mock();
    private final SnakeComponent snakeComponent = new SnakeComponent(garden);
    private final Food food = new Food(2, 3);
    private final Snake snake = mock();
    List<Point> segments = new ArrayList<>(List.of(
            new Point(1, 1),
            new Point(2, 1),
            new Point(3, 1)
    ));

    @Test
    void drawFood() {
        //given
        doReturn(food).when(garden).getFood();
        snakeComponent.setSquareHeight(SQUARE_SIZE);
        snakeComponent.setSquareWidth(SQUARE_SIZE);

        //when
        snakeComponent.drawFood(graphics, food);

        //then
        Mockito.verify(graphics).fillRect(food.x * SQUARE_SIZE, food.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).setColor(Color.RED);
    }

    @Test
    void drawSnake() {
        //given
        doReturn(snake).when(garden).getSnake();
        doReturn(segments).when(snake).getSegments();
        final Point point = segments.get(0);
        final Point point2 = segments.get(1);
        final Point point3 = segments.get(2);
        snakeComponent.setSquareHeight(SQUARE_SIZE);
        snakeComponent.setSquareWidth(SQUARE_SIZE);

        //when
        snakeComponent.drawSnake(graphics, snake);

        //then
        Mockito.verify(graphics).fillRect(point.x * SQUARE_SIZE, point.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).fillRect(point2.x * SQUARE_SIZE, point2.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).fillRect(point3.x * SQUARE_SIZE, point3.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).setColor(Color.BLUE);
    }

    @Test
    void drawGarden() {
        //given
        doReturn(40).when(garden).getWidth();
        doReturn(30).when(garden).getHeight();
        snakeComponent.setSquareHeight(SQUARE_SIZE);
        snakeComponent.setSquareWidth(SQUARE_SIZE);

        //when
        snakeComponent.drawGarden(graphics, garden);

        //then
        Mockito.verify(graphics).fillRect(SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
}