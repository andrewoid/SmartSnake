package smartsnake.astar.schlesinger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AstarBrainTest {

    @Test
    void move() {
        //given
        Snake snake = mock();
        Garden garden = mock();
        Food food = mock();
        AstarBrain brain = new AstarBrain();

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        Mockito.doReturn(Direction.values()).when(direction);

    }

}