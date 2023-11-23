package smartsnake;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class GardenTest {

    @Test
    void getSnake() {
        //given
        Snake snake = mock();

        //when
        Garden garden = new Garden(30, 40, snake);

        //then
        assertNotNull(garden.getSnake());
    }

    @Test
    void getFood() {
        //given
        Snake snake = mock();

        //when
        Garden garden = new Garden(30, 40, snake);

        //then
        assertNotNull(garden.getFood());
    }

    @Test
    void tick() {
        //given
        Snake snake = mock();
        Point point = new Point(0, 0);
        Garden garden = new Garden(40, 30, snake);
        doReturn(point).when(snake).getHeadLocation();

        //when
        garden.tick();

        //then
        assertFalse(garden.tick());
    }

    @Test
    void getTurns() {
        //given
        Snake snake = mock();
        Point point = mock();
        Garden garden = new Garden(40, 30, snake);
        doReturn(point).when(snake).getHeadLocation();

        //when
        garden.tick();

        //then
        assertEquals(1, garden.getTurns());
    }
}