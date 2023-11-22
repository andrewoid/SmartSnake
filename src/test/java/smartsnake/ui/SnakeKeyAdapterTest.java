package smartsnake.ui;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Snake;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SnakeKeyAdapterTest
{

    public Snake snake = mock();
    public SnakeKeyAdapter keyAdapter = new SnakeKeyAdapter(snake);

    @Test
    void keyPressed_left()
    {
        KeyEvent left1 = mock();
        left1.setKeyCode(KeyEvent.VK_LEFT);
        keyAdapter.keyPressed(left1);
        verify(snake).setDirection(Direction.Left);

        KeyEvent left2 = mock();
        left2.setKeyCode(KeyEvent.VK_KP_LEFT);
        keyAdapter.keyPressed(left2);
        verify(snake).setDirection(Direction.Left);
    }

    @Test
    void keyPressed_right()
    {
        KeyEvent right1 = mock();
        right1.setKeyCode(KeyEvent.VK_RIGHT);
        keyAdapter.keyPressed(right1);
        verify(snake).setDirection(Direction.Right);

        KeyEvent right2 = mock();
        right2.setKeyCode(KeyEvent.VK_KP_RIGHT);
        keyAdapter.keyPressed(right2);
        verify(snake).setDirection(Direction.Right);
    }

    @Test
    void keyPressed_up()
    {
        KeyEvent up1 = mock();
        up1.setKeyCode(KeyEvent.VK_UP);
        keyAdapter.keyPressed(up1);
        verify(snake).setDirection(Direction.Up);

        KeyEvent up2 = mock();
        up2.setKeyCode(KeyEvent.VK_KP_UP);
        keyAdapter.keyPressed(up2);
        verify(snake).setDirection(Direction.Up);
    }

    @Test
    void keyPressed_down()
    {
        KeyEvent down1 = mock();
        down1.setKeyCode(KeyEvent.VK_DOWN);
        keyAdapter.keyPressed(down1);
        verify(snake).setDirection(Direction.Down);

        KeyEvent down2 = mock();
        down2.setKeyCode(KeyEvent.VK_KP_DOWN);
        keyAdapter.keyPressed(down2);
        verify(snake).setDirection(Direction.Down);
    }
}