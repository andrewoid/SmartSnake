package smartsnake.neuralnetwork.runge;

import basicneuralnetwork.NeuralNetwork;
import com.sun.source.tree.NewArrayTree;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain
{
    private final NeuralNetwork neuralNetwork;
    private final NeuralNetworkDataFactory neuralNetworkDataFactory = new NeuralNetworkDataFactory();


    public NeuralNetworkBrain() throws IOException {
        neuralNetwork = NeuralNetwork.readFromFile();
    }

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        double[] directions = neuralNetwork.guess(neuralNetworkDataFactory.toInput(garden));
        return neuralNetworkDataFactory.toDirection(directions);
    }
}
