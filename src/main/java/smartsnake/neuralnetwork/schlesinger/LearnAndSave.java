package smartsnake.neuralnetwork.schlesinger;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.schlesinger.AstarBrain;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;


public class LearnAndSave {

    public static void main(String[] args) throws IOException {

        NeuralNetwork nn = new NeuralNetwork(5, 256, 4);
        NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();
        AstarBrain brain = new AstarBrain();

        for (int i = 0; i < 50000; i++) {
            Garden garden = new Garden(20, 15);
            while (garden.tick()) {
                Direction direction = brain.move(garden.getSnake(), garden.getFood(), garden);
                double[] gardenArray = dataFactory.toInput(garden);
                double[] directionArray = dataFactory.toOutput(direction);
                nn.train(gardenArray, directionArray);
            }
        }
        nn.writeToFile();
    }

}

