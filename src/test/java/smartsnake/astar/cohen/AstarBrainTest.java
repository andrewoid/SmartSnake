package smartsnake.astar.cohen;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AstarBrainTest
{
    private final Point point = new Point(10, 10);
    private  final Point headLocation = new Point(10, 5);
    Snake snake = mock();
    Food food = new Food(point);
    Garden garden = mock();
    AstarBrain brain = new AstarBrain();

    @Test
    public void move()
    {
        //Given above objects
        doReturn(snake).when(garden).getSnake();
        doReturn(food).when(garden).getFood();
        doReturn(headLocation).when(snake).getHeadLocation();
        doReturn(true).when(garden).contains(any());

        //When
        Direction direction = brain.move(snake, food, garden);
        //Then
        assertEquals(Direction.Down, direction);
        verify(snake).getHeadLocation();
    }

    @Test
    public void updateNodeParent()
    {
        Node current = new Node(headLocation, food);
        Node neighbor = new Node(new Point(headLocation.x, headLocation.y - 1), food);
        Direction direction = Direction.Up;
        ArrayList<Node> open = new ArrayList<>();
        Set<Node> closed = new HashSet<>();

        doReturn(snake).when(garden).getSnake();
        doReturn(food).when(garden).getFood();
        doReturn(headLocation).when(snake).getHeadLocation();
        doReturn(true).when(garden).contains(any());

        brain.closeSnakeNodes(snake, food, closed);
        AstarBrain.updateNodeParent(current, neighbor, direction, open, closed, garden);

        assertNotNull(neighbor.getParent());
        assertEquals(1, open.size());
    }

    @Test
    public void closeSnake()
    {
        //Given above objects and
        Set<Node> closed = new HashSet<>();

        //When
        brain.closeSnakeNodes(snake, food, closed);

        //Then
        for (int i = 1; i < snake.getSegments().size(); i++)
        {
            assertEquals(snake.getSegments().get(i).getLocation(), closed.toArray()[0]);
        }
    }

    @Test
    public void getLowestCostNode()
    {
        //Given
        List<Node> open = List.of(new Node(new Point(12, 5), food),
                new Node(new Point(15, 5), food));

        //When
        Node lowestCost = brain.getLowestCostNode(open);

        //Then
        assertEquals(open.get(0).getLocation(), lowestCost.getLocation());
    }
}