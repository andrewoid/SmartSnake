package smartsnake.astar.chambre;

import org.junit.jupiter.api.Test;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.ui.SnakeComponent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AstarBrainTest {
    @Test
    void move(){
        //given
        Garden garden = mock();
        garden.getSnake().setBrain(new AstarBrain());

        //when
        garden.tick();

        //then


    }

}