package smartsnake.astar.halberstam;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstarBrainTest {
    @Test
    void move() {
        //given
        Snake snake = mock();
        Food food = mock();
        Garden garden = mock();
        AstarBrain brain = new AstarBrain();
        //doReturn(Direction.Right).when(brain).move(snake, food, garden);
        //doReturn(source).when(element).getOutputFile();
        when(brain.move(snake, food, garden)).thenReturn(Direction.Right);

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(Direction.Right, direction);
    }
}