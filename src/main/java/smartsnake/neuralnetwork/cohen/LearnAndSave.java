package smartsnake.neuralnetwork.cohen;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;

public class LearnAndSave
{
    public static void main(String[] args)
    {
        NeuralNetwork neuralNetwork = new NeuralNetwork(5, 256, 4);
        for (int i = 0; i < 50_000; i++)
        {
            Garden garden = new Garden(20, 15);

        }
        /*Create a NN
    5 input nodes
    256 hidden nodes
    4 output nodes

    Play 50,000 games of Snake.
    For (I=0; I<50_000; I++)
    For every move, train the NN with the state of the Garden and the move that your AStarBrain decides to make.

    Save the NN
        * */
    }
}
