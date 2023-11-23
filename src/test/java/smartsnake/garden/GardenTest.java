package smartsnake.garden;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GardenTest {

    @Test
    void getSnake() {
        //given
        Snake snake = mock();
        Garden garden = new Garden(30,40,snake);
        //when
        garden.getSnake();

        //then
        assertNotNull(garden.getSnake());
    }

    @Test
    void getFood() {
        //given
        Snake snake = mock();
        Garden garden = new Garden(30,40, snake);

        //when
        garden.createFood();

        //then
        assertNotNull(garden.getFood());
    }

    @Test
    void tick() {
        //given
        Snake snake = mock();
        Garden garden = new Garden(40, 30, snake);

        //when
        garden.tick();

        //then
        assertFalse(garden.tick());
    }

    @Test
    void getTurns() {
        //given
        Snake snake = mock();
        Garden garden = new Garden(40, 30, snake);

        //when
        garden.tick();

        //then
        assertNotNull(garden.getTurns());
        assertEquals(1,garden.getTurns());
    }
}