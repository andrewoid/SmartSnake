package smartsnake.neuralnetwork.halberstam;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.halberstam.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class LearnAndSave {
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork;
        try {
            neuralNetwork = NeuralNetwork.readFromFile();
        } catch(IOException e) {
            e.printStackTrace();
            neuralNetwork = new NeuralNetwork(5, 256, 4);
        }
        NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

        for (int i = 0; i < 1000; i++)
        {
            Garden garden = new Garden(20, 15);
            Snake snake = garden.getSnake();
            AstarBrain brain = new AstarBrain();
            snake.setBrain(brain);

            boolean tick = true;
            while (tick) {
                double[] gardenInput = dataFactory.toInput(garden);
                tick = garden.tick();
                double[] output = dataFactory.toOutput(snake.getDirection());
                neuralNetwork.train(gardenInput, output);
                if (!tick) //because when game ends tick is false
                {
                    break;
                }
            }
        }
        neuralNetwork.writeToFile();
    }
}
