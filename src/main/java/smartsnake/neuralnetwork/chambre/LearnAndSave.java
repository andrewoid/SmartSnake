package smartsnake.neuralnetwork.chambre;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.chambre.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

public class LearnAndSave {
    public static void main(String[] args) {

        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
        NeuralNetworkDataFactory neuralNetworkDataFactory = new NeuralNetworkDataFactory();

        for (int i = 0; i < 50_000; i++) {
            AstarBrain astarBrain = new AstarBrain();
            Garden garden = new Garden(20, 15);
            Snake snake = garden.getSnake();
            snake.setBrain(astarBrain);

            boolean gardenTick = true;
            while (gardenTick) {
                double[] gardenInput = neuralNetworkDataFactory.toInput(garden);
                gardenTick = garden.tick();
                double[] snakeOutput = neuralNetworkDataFactory.toOutput(snake.getDirection());
                neuralNetwork.train(gardenInput, snakeOutput);

            }
        }
        neuralNetwork.writeToFile();
    }
}
