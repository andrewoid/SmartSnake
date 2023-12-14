package smartsnake.astar.runge;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AstarBrainTest
{
    @Test
    public void move() {
        //given
        Garden garden = mock(); //make this a mock
        Snake snake = mock();
        Food food = new Food(15, 5);
        AstarBrain brain = new AstarBrain();
        Point snakePosition = new Point(10, 10);
        doReturn(snakePosition).when(snake).getHeadLocation();
        doReturn(true).when(garden).contains(any());

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(direction, Direction.Up);
        verify(snake).getHeadLocation();
    }

    @Test
    public void getLowestCost() {
        //given
        AstarBrain brain = new AstarBrain();
        List<Node> openNodes = new ArrayList<>();
        Food food = new Food(new Point(15, 5));
        openNodes.add(new Node(new Point(10, 5), food));
        openNodes.add(new Node(new Point(14, 5), food));

        //when
        Node lowest = brain.getLowestCost(openNodes);

        //then
        assertEquals(openNodes.get(1), lowest);
    }

    @Test
    public void updateNeighborhood() {
        //given
        AstarBrain brain = new AstarBrain();
        Food food = new Food(new Point(20, 5));
        Node current = new Node(new Point(10, 10), food);
        List<Node> openNodes = new ArrayList<>();
        Set<Node> closedNodes = new HashSet<>();
        Node node1 = mock();
        Node node2 = mock();
        openNodes.add(node1);
        openNodes.add(node2);
        Garden garden = new Garden(20, 20);

        //when
        brain.updateNeighborhood(current, new Node(new Point(15, 5), food),
                Direction.Up, openNodes, closedNodes, garden);

        //then
        assertEquals(current, openNodes.get(openNodes.size() - 1).getParent());
    }
}
