package smartsnake.astar.halberstam;

import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;

class AstarBrainTest {
    //given
        Snake snake = new Snake();
        Food food = new Food(12, 5);
        Garden garden = new Garden(100, 200);
        AstarBrain brain = new AstarBrain();

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(Direction.Right, direction);
}