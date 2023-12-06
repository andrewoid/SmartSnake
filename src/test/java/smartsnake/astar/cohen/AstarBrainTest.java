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

    }

    @Test
    public void closeSnakeNodes()
    {
        //Given above objects and
        List<Node> closed = new ArrayList<>();

        //When
        brain.closeSnakeNodes(snake, food, closed);

        //Then
        for (int i = 1; i < snake.getSegments().size(); i++)
        {
            assertEquals(snake.getSegments().get(i).getLocation(), closed.get(i-1).getLocation());
        }
    }

    @Test
    public void getLowestCostNode()
    {
        //Given
        List<Node> open = new ArrayList<>(List.of(new Node(new Point(12, 5), food),
                                                  new Node(new Point(15, 5), food)));

        //When
        Node lowestCost = brain.getLowestCostNode(open);

        //Then
        assertEquals(open.get(0).getLocation(), lowestCost.getLocation());
    }
}