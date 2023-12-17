package smartsnake.astar.schlesinger;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class AstarBrainTest {

    @Test
    void move() {
        //given
        Snake snake = new Snake();
        AstarBrain brain = new AstarBrain();
        Garden garden = new Garden(15, 30);
        Food food = new Food(12, 5);

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(Direction.Right, direction);

    }

}