package smartsnake.astar.chambre;

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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class AstarBrainTest {
    private final Garden garden = mock();
    private final Food food = mock();
    private final Snake snake = mock();
    private final Point point = new Point(15, 15);
    List<Point> segments = new ArrayList<>(List.of(
            new Point(16, 15),
            new Point(17, 15),
            new Point(18, 15)
    ));

    @Test
    void move() {
        // Given
        //set up the mock snake with a head and segments
        doReturn(point).when(snake).getHeadLocation();
        doReturn(segments).when(snake).getSegments();

        //this line of code needs to be mocked
        //Garden garden = new Garden(30, 50);

        doReturn(food).when(garden).getFood();
        doReturn(snake).when(garden).getSnake();
        doReturn(30).when(garden).getWidth();
        doReturn(50).when(garden).getHeight();

        AstarBrain brain = new AstarBrain();


        //When
        Direction direction = brain.move(snake, food, garden);

        // Then
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
        doReturn(point).when(snake).getHeadLocation();
        Garden garden = new Garden(30, 50);
        AstarBrain brain = new AstarBrain();
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