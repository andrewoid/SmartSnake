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
    private final Garden garden = mock();
    private final Graphics graphics = mock();
    private final SnakeComponent snakeComponent = new SnakeComponent(garden);
    private final Food food = new Food();


    /*@Test
    void paintComponent() {
    }*/


    @Test
    void drawFood() {
        //given
        int SQUARE_SIZE = 10;
        doReturn(food).when(garden).getFood();

        //when
        snakeComponent.drawFood(graphics, food);

        //then
        Mockito.verify(graphics).fillRect(food.x, food.y, SQUARE_SIZE, SQUARE_SIZE);


    }
}