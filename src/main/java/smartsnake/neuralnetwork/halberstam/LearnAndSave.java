package smartsnake.neuralnetwork.halberstam;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.halberstam.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

public class LearnAndSave {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
        NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

        for (int i = 0; i < 50; i++)
        {
            Garden garden = new Garden(20, 15);
            Snake snake = garden.getSnake();
            AstarBrain brain = new AstarBrain();
            snake.setBrain(brain);

            while (garden.tick()) {
                double[] gardenInput = dataFactory.toInput(garden);
                double[] output = dataFactory.toOutput(snake.getDirection());
                neuralNetwork.train(gardenInput, output);
            }
        }
        neuralNetwork.writeToFile();
    }
}
