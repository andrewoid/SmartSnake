package smartsnake.neuralnetwork.schlesinger;


import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain {
    NeuralNetworkDataFactory dataFactory = new NeuralNetworkDataFactory();

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        NeuralNetwork nn = null;
        try {
            nn = NeuralNetwork.readFromFile("nn_data.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        double[] gardenArray = nn.guess(dataFactory.toInput(garden));
        return dataFactory.toDirection(gardenArray);
    }


}
