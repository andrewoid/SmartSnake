package smartsnake.astar.runge;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AstarBrainTest
{
    @Test
    public void move() {
        //given
        Garden garden = new Garden(20, 15);
        Snake snake = new Snake();
        Food food = new Food(new Point(10, 10));
        AstarBrain brain = new AstarBrain();

        //when
        Direction direction = brain.move(snake, food, garden);

        //then
        assertEquals(Direction.Down, direction);
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
        Node current = new Node(new Point(14, 5), food);
        List<Node> openNodes = new ArrayList<>();
        List<Node> closedNodes = new ArrayList<>();
        Node node1 = new Node(new Point(10, 5), food);
        Node node2 = new Node(new Point(11, 5), food);
        openNodes.add(node1);
        openNodes.add(node2);

        //when
        brain.updateNeighborhood(current, new Node(new Point(15, 5), food), Direction.Up, openNodes, closedNodes);

        //then
        assertEquals(current, openNodes.get(openNodes.size() - 1).getParent());
    }
}