package smartsnake.genetics.schwimmer;

import basicneuralnetwork.NeuralNetwork;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.neuralnetwork.NeuralNetworkDataFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static smartsnake.neuralnetwork.NeuralNetworkDataFactory.INPUT_SIZE;
import static smartsnake.neuralnetwork.NeuralNetworkDataFactory.OUTPUT_SIZE;

public class GeneticLearnAndSave {

    private static final Random random = new Random();

    private static final int GENERATIONS = 40;

    /**
     * Number of Snakes every generation
     */
    private static final int SNAKES = 2000;

    /**
     * Best performing Snakes to breed into a new generation.
     */
    private static final int BREED = (int) (SNAKES * .01);
    public static final double MUTATION_PROBABILITY = 0.1;
    public static final int HIDDEN_NODES = 256;
    public static final int LENGTH_WEIGHT = 10;

    public static void main(String[] args) {
        // 1. create 2000 networks
        // 2. each network plays one game of Snake
        // 3. Figure out the 1% best performing networks
        //      longest time running
        //      most amount of food eaten
        // 4. discard the rest
        // 5. using the 1%, create 2000 networks
        // 6. repeat step 2 for a number of generations
        NeuralNetworkDataFactory factory = new NeuralNetworkDataFactory();
        List<NeuralNetwork> networks = new ArrayList<>();
        List<NetworkAndScore> scores = new ArrayList<>();
        NetworkAndScore highScore = null;
        for (int i = 0; i < SNAKES; i++) {
            boolean add = networks.add(new NeuralNetwork(
                    INPUT_SIZE, HIDDEN_NODES, OUTPUT_SIZE));
        }
        for (int i = 0; i < GENERATIONS; i++) {
            scores.clear();
            for (NeuralNetwork network : networks) {
                Garden garden = new Garden(20, 15);
                Snake snake = garden.getSnake();
                boolean running;
                do {
                    double[] input = factory.toInput(garden);
                    double[] output = network.guess(input);
                    snake.setDirection(factory.toDirection(output));
                    running = garden.tick();
                } while (running);
                int snakeSize = snake.getSegments().size();
                int tickCounter = garden.getTurns();
                int score = snakeSize * LENGTH_WEIGHT + tickCounter;
                scores.add(new NetworkAndScore(garden, network, score));
            }
            Collections.sort(scores);
            NetworkAndScore generationalHighScore = scores.get(0);
            if (highScore == null || highScore.score() < generationalHighScore.score()) {
                highScore = generationalHighScore;
            }
            System.out.printf("%d Snakes=%d High Score=%d\n", i, scores.size(), generationalHighScore.score());
            List<NetworkAndScore> oldGeneration = scores.subList(0, BREED);
            networks.clear();
            for (NetworkAndScore n : oldGeneration) {
                for (int j = 0; j < SNAKES / BREED; j++) {
                    NeuralNetwork other = oldGeneration.get(
                            random.nextInt(oldGeneration.size())).network();
                    NeuralNetwork child = n.network().merge(other);
                    child.mutate(MUTATION_PROBABILITY);
                    networks.add(child);
                }
            }
        }

        highScore.network().writeToFile();
    }

}
