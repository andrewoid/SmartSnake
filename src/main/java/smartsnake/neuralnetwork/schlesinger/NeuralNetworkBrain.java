package smartsnake.neuralnetwork.schlesinger;


import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain {
    NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();
    NeuralNetwork nn = NeuralNetwork.readFromFile("nn_data.json");

    public NeuralNetworkBrain() throws IOException {
    }

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {


        double[] gardenArray = nn.guess(dataFactory.toInput(garden));
        return dataFactory.toDirection(gardenArray);
    }


}
