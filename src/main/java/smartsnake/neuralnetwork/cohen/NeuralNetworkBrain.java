package smartsnake.neuralnetwork.cohen;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain
{
    private final NeuralNetwork neuralNetwork;
    private NeuralNetworkDataFactory dataFactory;

    public NeuralNetworkBrain() throws IOException
    {
        neuralNetwork = NeuralNetwork.readFromFile();
    }

    @Override
    public Direction move(Snake snake, Food food, Garden garden)
    {
        double [] directionsToMove = neuralNetwork.guess(dataFactory.toInput(garden));
        return dataFactory.toDirection(directionsToMove);
    }
}