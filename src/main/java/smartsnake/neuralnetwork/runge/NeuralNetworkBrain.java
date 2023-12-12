package smartsnake.neuralnetwork.runge;

import basicneuralnetwork.NeuralNetwork;
import com.sun.source.tree.NewArrayTree;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain
{
    //Load your NN
    //When move() is called,
    // call guess() on the NN and then
    // return the result as a Direction
    private final NeuralNetwork neuralNetwork;

    public NeuralNetworkBrain() throws IOException {
        neuralNetwork = NeuralNetwork.readFromFile();
    }

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        NeuralNetworkDataFactory neuralNetworkDataFactory = new NeuralNetworkDataFactory();
        double[] directions = neuralNetwork.guess(neuralNetworkDataFactory.toInput(garden));
        return neuralNetworkDataFactory.toDirection(directions);
    }
}
