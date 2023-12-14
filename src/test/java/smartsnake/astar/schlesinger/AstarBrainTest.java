package smartsnake.astar.schlesinger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstarBrainTest {
    final Garden garden = mock();
    final Food food = new Food(10, 6);
    final AstarBrain brain = new AstarBrain();
    final Snake snake = mock();

    @Test
    void move() {
        //given
        doReturn(new Point(10, 5)).when(snake).getHeadLocation();
        ArrayList<Point> snakeSegments = new ArrayList<>();
        snakeSegments.add(new Point(10, 5));
        snakeSegments.add(new Point(10, 4));
        snakeSegments.add(new Point(10, 3));
        doReturn(snakeSegments).when(snake).getSegments();
        doReturn(true).when(garden).contains(any());

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(Direction.Down, direction);

    }

}