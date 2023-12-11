package smartsnake.astar.chambre;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AstarBrainTest {

    @Test
    void move() {
        //given
        Snake snake = new Snake();
        Garden garden = new Garden(30, 50);
        Food food = mock();
        AstarBrain brain = new AstarBrain();

        //when
        Direction direction = brain.move(snake, food, garden);


        //then
        assertEquals(Direction.Up, direction);
    }

    @Test
    void getLowestCost() {
        // given
        AstarBrain brain = new AstarBrain();
        ArrayList<Node> openNodes = new ArrayList<>();
        openNodes.add(new Node(new Point(9, 4), new Point(20, 10)));
        openNodes.add(new Node(new Point(13, 4), new Point(20, 10)));

        // when
        Node lowestCost = brain.getLowestCost(openNodes);

        // then
        assertEquals(openNodes.get(1), lowestCost);
    }

    @Test
    void updateDirection() {
        //given
        Snake snake = new Snake();
        Garden garden = new Garden(30, 50);
        AstarBrain brain = new AstarBrain();
        Point point = new Point(5, 5);
        Food food = mock();
        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();
        Node current = new Node(new Point(4, 5), food);

        //when
        brain.updateDirection(point, Direction.Up, food, openNodes, closedNodes, snake, current, garden);

        //then
        assertEquals(1, openNodes.size());
        assertEquals(openNodes.get(0).getLocation(), point);
    }
}