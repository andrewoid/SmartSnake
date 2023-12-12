package smartsnake.neuralnetwork.chambre;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.chambre.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

public class LearnAndSave {
    public static void main(String[] args) {

        //Create a NN
        //        5 input nodes
        //        256 hidden nodes
        //        4 output nodes
        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
        NeuralNetworkDataFactory neuralNetworkDataFactory = new NeuralNetworkDataFactory();

        //Play 50,000 games of Snake.
        for (int i = 0; i < 50_000; i++) {
            //For every move, train the NN with the state of the Garden and the move that your AStarBrain decides to make.
            AstarBrain astarBrain = new AstarBrain();
            Garden garden = new Garden(20, 15);
            Snake snake = garden.getSnake();
            snake.setBrain(astarBrain);

            while (true) {
                //train method needs two arrays
                double[] gardenInput = neuralNetworkDataFactory.toInput(garden);
                //call tick before training to save the state of the garden
                boolean gardenTick = garden.tick();
                neuralNetwork.train(
                        gardenInput,
                        neuralNetworkDataFactory.toOutput(snake.getDirection()));
                if (!gardenTick) {
                    break;
                }
            }
        }
        //     Save the NN
        neuralNetwork.writeToFile();
    }
}
