package smartsnake.ui;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SnakeComponentTest {
    //mock garden, snake, and regular food, mock graphics
    //You have a given, a do return, and a verify
    private final Garden garden = mock(Garden.class);
    private final Graphics graphics = mock(Graphics.class);
    private final SnakeComponent snakeComponent = new SnakeComponent(garden);
    private final Food food = new Food();


    /*@Test
    void paintComponent() {
    }*/


    @Test
    void drawFood() {
        //given
        //int SQUARE_SIZE = 10;
        doReturn(food.x).when(garden.getFood().getX());
        doReturn(food.y).when(garden.getFood().getY());

        //when
        snakeComponent.drawFood(graphics, food);

        //then
        Mockito.verify(graphics.getColor().equals(Color.RED));

    }
}