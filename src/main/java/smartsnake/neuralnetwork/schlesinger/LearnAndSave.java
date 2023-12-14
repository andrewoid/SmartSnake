package smartsnake.neuralnetwork.schlesinger;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Direction;
import smartsnake.Garden;
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
            garden.getSnake().setBrain(brain);
            boolean gardenTick = true;
            while (gardenTick) {
                //call to input
                //then call garden.tick
                //to output, and train
                double[] gardenArray = dataFactory.toInput(garden);
                gardenTick = garden.tick();
                double[] directionArray = dataFactory.toOutput(garden.getSnake().getDirection());
                nn.train(gardenArray, directionArray);

            }
        }
        nn.writeToFile();
    }

}

