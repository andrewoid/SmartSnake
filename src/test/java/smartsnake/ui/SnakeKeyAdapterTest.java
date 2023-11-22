package smartsnake.ui;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Snake;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SnakeKeyAdapterTest
{

    public Snake snake = mock();
    public SnakeKeyAdapter keyAdapter = new SnakeKeyAdapter(snake);

    @Test
    void keyPressed_left()
    {
        KeyEvent left1 = mock();
        doReturn(KeyEvent.VK_LEFT).when(left1).getKeyCode();
        keyAdapter.keyPressed(left1);
        verify(snake).setDirection(Direction.Left);
    }

    @Test
    void keyPressed_left_KP()
    {
        KeyEvent left2 = mock();
        doReturn(KeyEvent.VK_KP_LEFT).when(left2).getKeyCode();
        keyAdapter.keyPressed(left2);
        verify(snake).setDirection(Direction.Left);
    }
    @Test
    void keyPressed_right()
    {
        KeyEvent right1 = mock();
        doReturn(KeyEvent.VK_RIGHT).when(right1).getKeyCode();
        keyAdapter.keyPressed(right1);
        verify(snake).setDirection(Direction.Right);
    }

    @Test
    void keyPressed_right_KP()
    {
        KeyEvent right2 = mock();
        doReturn(KeyEvent.VK_KP_RIGHT).when(right2).getKeyCode();
        keyAdapter.keyPressed(right2);
        verify(snake).setDirection(Direction.Right);
    }
    @Test
    void keyPressed_up()
    {
        KeyEvent up1 = mock();
        doReturn(KeyEvent.VK_UP).when(up1).getKeyCode();
        keyAdapter.keyPressed(up1);
        verify(snake).setDirection(Direction.Up);
    }

    @Test
    void keyPressed_up_KP()
    {
        KeyEvent up2 = mock();
        doReturn(KeyEvent.VK_KP_UP).when(up2).getKeyCode();
        keyAdapter.keyPressed(up2);
        verify(snake).setDirection(Direction.Up);
    }
    @Test
    void keyPressed_down()
    {
        KeyEvent down1 = mock();
        doReturn(KeyEvent.VK_DOWN).when(down1).getKeyCode();
        keyAdapter.keyPressed(down1);
        verify(snake).setDirection(Direction.Down);
    }

    @Test
    void keyPressed_down_KP()
    {
        KeyEvent down2 = mock();
        doReturn(KeyEvent.VK_KP_DOWN).when(down2).getKeyCode();
        keyAdapter.keyPressed(down2);
        verify(snake).setDirection(Direction.Down);
    }
}