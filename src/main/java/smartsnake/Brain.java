package smartsnake;


/**
 * An interface that decides which Direction the Snake should move in based on the Snake, Garden and Food.
 */
public interface Brain {

    Direction move(Snake snake, Food food, Garden garden);

}
