package smartsnake.neuralnetwork.chambre;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class NeuralNetworkBrainTest {
    @Test
    void move() throws IOException {
        // Given
        Garden garden = new Garden(30, 40);
        Snake snake = mock();
        Food food = new Food(15, 5);
        NeuralNetworkBrain brain = new NeuralNetworkBrain();
        Point point = new Point(10, 10);
        doReturn(point).when(snake).getHeadLocation();


        //When
        Direction direction = brain.move(snake, food, garden);

        // Then
        assertEquals(direction, Direction.Up);
    }

}