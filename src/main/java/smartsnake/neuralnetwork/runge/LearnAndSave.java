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
            Snake snake = new Snake();
            AstarBrain brain = new AstarBrain();
            snake.setBrain(brain);

            boolean ticked = true;
            while(ticked)
            {
                double[] input = neuralNetworkDataFactory.toInput(garden);
                ticked = garden.tick();
                neuralNetwork.train(input, neuralNetworkDataFactory.toOutput(snake.getDirection()));
                if(!ticked)
                {
                    break;
                }
            }
        }
        neuralNetwork.writeToFile();
    }
}
//create a neural network
//5 input nodes, 256 hidden nodes, 4 output nodes
//play 50_000 games
//for every move, train the NN with the state of the garden,
//and the move that your AstarBrain decides to make