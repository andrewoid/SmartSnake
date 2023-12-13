package smartsnake.astar.cohen;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AstarBrainTest
{
    private final Point point = new Point(10, 10);
    Snake snake = new Snake();
    Food food = new Food(point);
    Garden garden = new Garden(40, 30);
    AstarBrain brain = new AstarBrain();

    @Test
    public void move()
    {
        //Given above objects

        //When
        Direction direction = brain.move(snake, food, garden);

        //Then
        assertEquals(Direction.Down, direction);
    }

    @Test
    public void updateNodeParent()
    {
        Point headLocation = snake.getHeadLocation();
        Node current = new Node(headLocation, food);
        Node neighbor = new Node(new Point(headLocation.x, headLocation.y - 1), food);
        Direction direction = Direction.Up;
        List<Node> open = new ArrayList<>();
        List<Node> closed = new ArrayList<>();

        brain.closeSnakeNodes(snake, food, closed);
        AstarBrain.updateNodeParent(current, neighbor, direction, open, closed, garden);

        assertNotNull(neighbor.getParent());
        assertEquals(1, open.size());
    }

    @Test
    public void closeSnake()
    {
        //Given above objects and
        List<Node> closed = new ArrayList<>();

        //When
        brain.closeSnakeNodes(snake, food, closed);

        //Then
        for (int i = 1; i < snake.getSegments().size(); i++)
        {
            assertEquals(snake.getSegments().get(i).getLocation(), closed.get(i - 1).getLocation());
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