package smartsnake.neuralnetwork;

import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

/**
 * Converts the state of Garden to a Neural Network input array,
 * converts Direction to an output array and converts an output array
 * back into a Direction.
 */
public class NeuralNetworkDataFactory {

    public static final int INPUT_SIZE = 5;
    public static final int OUTPUT_SIZE = 4;

    private final Direction[] directions = Direction.values();

    /**
     * @return the Garden as an input array for use with a Neural Network
     */
    public double[] toInput(Garden garden) {
        double[] input = new double[INPUT_SIZE];
        fillInput(garden, input);
        return input;
    }

    /**
     * Sets the values of input based on the current state of the Garden
     */
    public void fillInput(
            Garden garden,
            double[] input) {
        Snake snake = garden.getSnake();
        Point snakeLocation = snake.getHeadLocation();
        List<Point> segments = snake.getSegments();
        input[0] = isOpenSquare(garden, segments, snakeLocation.x + 1, snakeLocation.y);
        input[1] = isOpenSquare(garden, segments, snakeLocation.x - 1, snakeLocation.y);
        input[2] = isOpenSquare(garden, segments, snakeLocation.x, snakeLocation.y + 1);
        input[3] = isOpenSquare(garden, segments, snakeLocation.x, snakeLocation.y - 1);
        
        Food food = garden.getFood();
        // This is the angle from the Snake head to the Food.
        input[4] = Math.atan2(snakeLocation.getY() - food.getY(),
                snakeLocation.getX() - food.getX());
    }

    /**
     * @return 1 if the square is outside the Garden or blocked by the Snake, otherwise 0
     */
    private double isOpenSquare(Garden garden, List<Point> segments, int x, int y) {
        Point p = new Point(x, y);
        if (garden.contains(p) && !segments.contains(p)) {
            return 0;
        }
        return 1;
    }

    /**
     * @return the Direction with the highest probability from the output.
     */
    public Direction toDirection(double[] output) {
        int directionIndex = 0;
        for (int i = 1; i < output.length; i++) {
            if (output[i] > output[directionIndex]) {
                directionIndex = i;
            }
        }
        return directions[directionIndex];
    }

    /**
     * @return the Direction represented as an array of outputs.
     */
    public double[] toOutput(Direction direction) {
        double[] output = new double[OUTPUT_SIZE];
        output[direction.ordinal()] = 1;
        return output;
    }

    /**
     * Sets the values in output corresponding to this Direction.
     */
    public void fillOutput(Direction direction, double[] output) {
        Arrays.fill(output, 0);
        output[direction.ordinal()] = 1;
    }

}
