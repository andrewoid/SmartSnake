package smartsnake;

import java.awt.*;

/**
 * Chambre, Runge
 */
public class Garden {

    private final int width;
    private final int height;
    private Snake snake;
    private Food food;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        createSnake();
        createFood();
    }

    private void createSnake() {
        snake = new Snake();
        snake.setDirection(Direction.Right);
    }

    private void createFood() {
        int x = getSnake().getHeadLocation().x;
        int y = getSnake().getHeadLocation().y;
        food = new Food(x+5, y);
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        //create a Food object to bring the Food into this class
        return food;
    }

    /**
     * This method will move the Snake one square in its direction,
     * consume the food and spawn more food
     * or end the game if the Snake hits itself or the wall or starves.
     *
     * @return true if the simulation should continue, otherwise false
     */
    public boolean tick() {
        //Question: unsure if the tick method is supposed to have calculations of snake moving or turning?
        //every tick will be 1. a movement of the snake
                            //2. consume any food in its path and then spawn more food
                            //3. end the game if the snake hits itself or the wall
        return false;
    }

    /**
     * @return the number of times tick() was called successfully.
     */
    public int getTurns() {
        //This goes back to our previous question of where does the turning happen? in the tick method?
        return 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
