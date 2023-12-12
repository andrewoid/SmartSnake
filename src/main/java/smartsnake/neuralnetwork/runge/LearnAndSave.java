package smartsnake.neuralnetwork.runge;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.runge.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

public class LearnAndSave
{
    public static void main(String[] args) {
        //create a neural network
        //5 input nodes
        //256 hidden nodes
        //4 output nodes
        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);

        NeuralNetworkDataFactory neuralNetworkDataFactory = new NeuralNetworkDataFactory();
        Garden garden = new Garden(20, 15);
        Snake snake = new Snake();
        //need to set the brain im using
        AstarBrain brain = new AstarBrain();
        snake.setBrain(brain);

        //play 50_000 games
        for (int i = 0; i < 50_000; i++)
        {
            //while snake can move
            while (garden.tick())
            {
                //for every move, train the NN with the state of the garden,
                //and the move that your AstarBrain decides to make
                neuralNetwork.train(neuralNetworkDataFactory.toInput(garden),
                        neuralNetworkDataFactory.toOutput(snake.getDirection()));
            }
            neuralNetwork.writeToFile();
        }
        //save the NN, found in NN class
        //neuralNetwork.writeToFile();
    }
}