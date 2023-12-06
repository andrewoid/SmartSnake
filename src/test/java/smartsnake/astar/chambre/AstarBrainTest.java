package smartsnake.astar.chambre;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smartsnake.Direction;
import smartsnake.Food;
import smartsnake.Garden;
import smartsnake.Snake;
import smartsnake.astar.Node;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AstarBrainTest {
    private AstarBrain brain;
    private Food food;
    private Garden garden;
    private Snake snake;

    @BeforeEach
    public void setUp() {
        snake = new Snake();
        garden = new Garden(30, 50);
        food = new Food();
        brain = new AstarBrain();
    }

    @Test
    void move() {
        //given

        //when
        Direction direction = brain.move(snake, food, garden);


        //then
        assertEquals(Direction.Up, direction);
    }

    @Test
    void getLowestCost() {
        // given
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
        Point point = new Point(5, 5);
        food = new Food(point);
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