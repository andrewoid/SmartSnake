package smartsnake.garden;

import org.junit.jupiter.api.Test;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import static org.mockito.Mockito.mock;

public class GardenTest
{
    @Test
    void tickTest()
    {
        //given
        Garden garden = new Garden(40, 30);
        Snake snake = mock();
        Food food = mock();

        //when


        //then
    }
}

//test needs to confirm that tick, createSnake, and createFood work
    //call getTurns to see how many times tick ran successfully
    //probably done in same test
