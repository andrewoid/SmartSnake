package smartsnake.ui;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Snake;

import java.awt.event.KeyEvent;
import static org.mockito.Mockito.*;

class SnakeKeyAdapterTest
{

    public Snake snake = mock();
    public SnakeKeyAdapter keyAdapter = new SnakeKeyAdapter(snake);

    @Test
    void keyPressed_left()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_LEFT).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Left);
    }

    @Test
    void keyPressed_left_kp()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_KP_LEFT).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Left);
    }

    @Test
    void keyPressed_right()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_RIGHT).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Right);
    }

    @Test
    void keyPressed_right_kp()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_KP_RIGHT).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Right);
    }

    @Test
    void keyPressed_up()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_UP).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Up);
    }

    @Test
    void keyPressed_up_kp()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_KP_UP).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Up);
    }

    @Test
    void keyPressed_down()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_DOWN).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Down);
    }

    @Test
    void keyPressed_down_kp()
    {
        KeyEvent event = mock();
        doReturn(KeyEvent.VK_KP_DOWN).when(event).getKeyCode();
        keyAdapter.keyPressed(event);
        verify(snake).setDirection(Direction.Down);
    }
}