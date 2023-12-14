package smartsnake.neuralnetwork.runge;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.runge.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

public class LearnAndSave
{
    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
        NeuralNetworkDataFactory neuralNetworkDataFactory = new NeuralNetworkDataFactory();

        for (int i = 0; i < 50_000; i++)
        {
            Garden garden = new Garden(20, 15);
            Snake snake = garden.getSnake();
            AstarBrain brain = new AstarBrain();
            snake.setBrain(brain);

            boolean ticked = true;
            while(ticked)
            {
                double[] input = neuralNetworkDataFactory.toInput(garden);
                ticked = garden.tick();
                neuralNetwork.train(input, neuralNetworkDataFactory.toOutput(snake.getDirection()));
            }
        }
        neuralNetwork.writeToFile();
    }
}