package smartsnake.astar.chambre;

import org.junit.jupiter.api.Test;
import smartsnake.Garden;

import static org.mockito.Mockito.mock;

class AstarBrainTest {
    @Test
    void move() {
        //given
        Garden garden = mock();
        garden.getSnake().setBrain(new AstarBrain());
        garden.tick();

        //when


        //then


    }

}