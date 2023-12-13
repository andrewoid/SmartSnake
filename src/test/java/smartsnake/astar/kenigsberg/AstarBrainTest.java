package smartsnake.astar.kenigsberg;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AstarBrainTest {

    private final Point point = new Point(15, 5);

    @Test
    public void move() {
        // given
        Snake snake = new Snake();
        Food food = new Food(point);
        Garden garden = new Garden(40, 30);
        AstarBrain brain = new AstarBrain();

        // when
        Direction direction = brain.move(snake, food, garden);

        // then
        assertEquals(Direction.Right, direction);
    }

    @Test
    public void updateNeighbors() {
        // given
        Node current = new Node(new Point(14, 5), point);
        ArrayList<Node> openNodes = new ArrayList<>();
        openNodes.add(new Node(point, point));
        Set<Node> closedNodes = new HashSet<>();
        Food food = new Food(new Point(18, 5));
        AstarBrain brain = new AstarBrain();
        Garden garden = new Garden(20, 15);

        // when
        brain.updateNeighbors(current, point, Direction.Right, openNodes, closedNodes, food, garden);

        // then
        assertEquals(current, openNodes.get(openNodes.size() - 1).getParent());
    }

    @Test
    public void getLowestCost() {
        // given
        ArrayList<Node> openNodes = new ArrayList<>();
        openNodes.add(new Node(new Point(10, 5), new Point(15, 5)));
        openNodes.add(new Node(new Point(14, 5), new Point(15, 5)));
        AstarBrain brain = new AstarBrain();
        // when
        Node lowestCost = brain.getLowestCost(openNodes);

        // then
        assertEquals(openNodes.get(1), lowestCost);
    }

    @Test
    public void addSnakeToClosed() {
        // given
        Snake snake = new Snake();
        Food food = new Food(point);
        Set<Node> closedNodes = new HashSet<>();
        AstarBrain brain = new AstarBrain();

        // when
        brain.addSnakeToClosed(snake, food, closedNodes);

        // then
        assertEquals(snake.getSegments().get(1).getLocation(), ((Node)closedNodes.toArray()[0]).getLocation());
    }

}