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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class AstarBrainTest {

    @Test
    void move() {
        // Given
        Garden garden = new Garden(30, 40);
        Snake snake = mock();
        Food food = new Food(15, 5);
        AstarBrain brain = new AstarBrain();
        Point point = new Point(10, 10);
        doReturn(point).when(snake).getHeadLocation();


        //When
        Direction direction = brain.move(snake, food, garden);

        // Then
        assertEquals(direction, Direction.Up);
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
        Point point = new Point(4, 5);
        Snake snake = mock();
        Garden garden = mock();
        doReturn(point).when(snake).getHeadLocation();
        Food food = new Food(5, 15);

        ArrayList<Node> openNodes = new ArrayList<>();
        ArrayList<Node> closedNodes = new ArrayList<>();
        Node current = new Node(new Point(4, 5), food);
        openNodes.add(current);

        AstarBrain brain = new AstarBrain();

        //when
        brain.updateDirection(point, Direction.Up, food, openNodes, closedNodes, snake, current, garden);

        //then
        assertEquals(1, openNodes.size());
        assertEquals(openNodes.get(0).getLocation(), point);
    }
}