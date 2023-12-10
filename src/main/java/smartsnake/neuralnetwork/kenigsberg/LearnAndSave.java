package smartsnake.neuralnetwork.kenigsberg;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Brain;
import smartsnake.BrainFactory;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

public class LearnAndSave {
    /**
     *  1. Create a NN
     *      a. 5 input nodes
     *      b. 256 hidden nodes
     *      c. 4 output nodes
     *  2. Play 50,000 games
     *      a. for(I=0; I<50,000; I++)
     *          i. Have inner loop - while the game is still going, keep going (garden.tick() is true…)
     *          ii. For every move, train the NN with the state of the Garden & the move that your AstarBrain decides to make
     *  3. Save the NN
     */
    public static void main(String[] args) {

        // Neural network with 5 inputs, 256 hidden nodes and 4 output
        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);

        for (int i = 0; i < 50_000; i++)
        {
            Garden garden = new Garden(20,15);
            Snake snake = garden.getSnake();
            BrainFactory brainFactory = new BrainFactory();
            Brain brain = brainFactory.newInstance();
            snake.setBrain(brain);

            while (garden.tick())
            {
                NeuralNetworkDataFactory networkDataFactory = new NeuralNetworkDataFactory();

                // Need to get the Output - do to that need to get the Direction...
                //neuralNetwork.train(networkDataFactory.toInput(garden), networkDataFactory.toOutput());
            }
        }
        //neuralNetwork.Save?? **How to save a NN**
    }
}
