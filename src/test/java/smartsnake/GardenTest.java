package smartsnake;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        Point point = new Point(15, 15);
        Brain brain = mock();
        doReturn(brain).when(snake).getBrain();

        doReturn(Direction.Up).when(brain).move(any(), any(), any());

        doReturn(point).when(snake).getHeadLocation();
        Garden garden = new Garden(40, 30, snake);

        //when
        assertTrue(garden.tick());

        //then
        verify(snake).tick(anyBoolean());
        verify(snake).setDirection(Direction.Up);
        assertEquals(1, garden.getTurns());
    }
}