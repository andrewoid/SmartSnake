package smartsnake.ui;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static smartsnake.ui.SnakeComponent.SQUARE_SIZE;

class SnakeComponentTest {
    //mock garden, snake, and regular food, mock graphics
    //You have a given, a do return, and a verify
    private final Garden garden = mock();
    private final Graphics graphics = mock();
    private final SnakeComponent snakeComponent = new SnakeComponent(garden);
    private final Food food = new Food(2, 3);
    private final Snake snake = mock();
    List<Point> segments = new ArrayList<>(List.of(
            new Point(1,1),
            new Point(2,1),
            new Point(3,1)
    ));



    /*@Test
    void paintComponent() {
    }*/


    @Test
    void drawFood() {
        //given

        doReturn(food).when(garden).getFood();

        //when
        snakeComponent.drawFood(graphics, food);

        //then
        Mockito.verify(graphics).fillRect(food.x, food.y, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).setColor(Color.RED);


    }

    @Test
    void drawSnake() {
        //given

        doReturn(snake).when(garden).getSnake();
        doReturn(segments).when(snake).getSegments();
        Point point1 = segments.get(0);
        Point point2 = segments.get(1);
        Point point3 = segments.get(2);


        //when
        snakeComponent.drawSnake(graphics, snake);

        //then
        Mockito.verify(graphics).fillRect(point1.x, point1.y, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).fillRect(point2.x, point2.y, SQUARE_SIZE, SQUARE_SIZE);
        Mockito.verify(graphics).fillRect(point3.x, point3.y, SQUARE_SIZE, SQUARE_SIZE);

    }
}