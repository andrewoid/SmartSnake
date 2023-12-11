package smartsnake.neuralnetwork.kenigsberg;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain {

    private NeuralNetwork neuralNetwork;
    private NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

    /**
     * 1. Load your NN
     * 2. Has a constructor to load the NN
     * 3. When move() is called, call guess() on the NN & then return the result as a Direction
     *  *** 3 lines of code!! ***
     */

    public NeuralNetworkBrain() throws IOException {
        neuralNetwork = NeuralNetwork.readFromFile();
    }

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {

        double[] directions = neuralNetwork.guess(new double[]{neuralNetwork.getInputNodes()});
        return dataFactory.toDirection(directions);
    }
}
