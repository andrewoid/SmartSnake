package smartsnake.astar.halberstam;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstarBrainTest {
    @Test
    void move() {
        //given
        Snake snake = mock();
        doReturn(new Point(10,5)).when(snake).getHeadLocation();

        ArrayList<Point> segments = new ArrayList<>();
        segments.add(new Point(10, 5));
        segments.add(new Point(9, 5));
        segments.add(new Point(8, 5));
        doReturn(segments).when(snake).getSegments();

        Food food = new Food(12, 5);
        Garden garden = mock();
        doReturn(true).when(garden).contains(any());
        AstarBrain brain = new AstarBrain();

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(Direction.Right, direction);
    }
}