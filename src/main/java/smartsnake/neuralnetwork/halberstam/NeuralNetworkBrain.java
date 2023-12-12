package smartsnake.neuralnetwork.halberstam;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain {
    NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
    NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

    @Override
    public Direction move(Snake snake, Food food, Garden garden) throws IOException {

        neuralNetwork = NeuralNetwork.readFromFile();
        double[] directions = neuralNetwork.guess(dataFactory.toInput(garden));
        return dataFactory.toDirection(directions);
    }
}