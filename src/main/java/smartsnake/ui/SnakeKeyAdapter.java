package smartsnake.ui;

import smartsnake.Direction;
import smartsnake.Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

/**
 * Cohen
 */
public class SnakeKeyAdapter extends KeyAdapter
{
    private final Snake snake;

    public SnakeKeyAdapter(Snake snake)
    {
        this.snake = snake;
    }

    /**
     * Sets the Direction of the Snake based on the event
     *
     * @param event the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent event)
    {
        super.keyPressed(event);

        // KeyCodes - https://docs.oracle.com/javase/8/docs/api/java/awt/event/KeyEvent.html
        switch (event.getKeyCode())
        {
            case VK_UP, VK_KP_UP -> snake.setDirection(Direction.Up);
            case VK_DOWN, VK_KP_DOWN -> snake.setDirection(Direction.Down);
            case VK_LEFT, VK_KP_LEFT -> snake.setDirection(Direction.Left);
            case VK_RIGHT, VK_KP_RIGHT -> snake.setDirection(Direction.Right);
        }
    }
}
