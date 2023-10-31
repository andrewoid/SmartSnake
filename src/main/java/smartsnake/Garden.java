package smartsnake;

public class Garden {

    private final int width;
    private final int height;

    public Garden(int width, int height) {
        this.width = width;
        this.height = height;
        createSnake();
        createFood();
    }

    private void createSnake() {

    }

    private void createFood() {

    }

    public Snake getSnake() {
        return null;
    }

    public Food getFood() {
        return null;
    }

    /**
     * This method will move the Snake one square in its direction,
     * consume the food and spawn more food
     * or end the game if the Snake hits itself or the wall or starves.
     *
     * @return true if the simulation should continue, otherwise false
     */
    public boolean tick() {
        return false;
    }

    /**
     * @return the number of times tick() was called successfully.
     */
    public int getTurns() {
        return 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
