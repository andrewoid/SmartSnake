package smartsnake.neuralnetwork.schlesinger;


import basicneuralnetwork.NeuralNetwork;
import smartsnake.*;

import java.io.IOException;

public class NeuralNetworkBrain implements Brain {
    public NeuralNetworkBrain() throws IOException {
        NeuralNetwork nn = NeuralNetwork.readFromFile();
    }

    @Override
    public Direction move(Snake snake, Food food, Garden garden) {
        return null;
    }


}
