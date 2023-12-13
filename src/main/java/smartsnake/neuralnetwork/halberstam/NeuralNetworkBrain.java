package smartsnake.neuralnetwork.halberstam;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain {
    NeuralNetwork neuralNetwork;
    NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

    public NeuralNetworkBrain() {
        try {
            neuralNetwork = NeuralNetwork.readFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public Direction move(Snake snake, Food food, Garden garden) {
        double[] directions = neuralNetwork.guess(dataFactory.toInput(garden));
        return dataFactory.toDirection(directions);
    }
}
