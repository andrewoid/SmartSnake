package smartsnake.neuralnetwork.cohen;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Direction;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.cohen.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class LearnAndSave
{
    public static void main(String[] args) throws IOException
    {
        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
        NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

        for (int i = 0; i < 10000; i++)
        {
            Garden garden = new Garden(20, 15);
            Snake snake = garden.getSnake();
            snake.setBrain(new AstarBrain());

            boolean stillPlaying = true;

            while(stillPlaying)
            {
                double[] currentGardenState = dataFactory.toInput(garden);
                stillPlaying = garden.tick();
                neuralNetwork.train(currentGardenState, dataFactory.toOutput(snake.getDirection()));
            }
        }

        neuralNetwork.writeToFile();
    }
}
