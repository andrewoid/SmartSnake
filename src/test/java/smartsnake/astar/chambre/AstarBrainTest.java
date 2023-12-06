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

class AstarBrainTest {
    private final Point point = new Point(20, 30);

    @Test
    void move() {
        //given
        Snake snake = new Snake();
        Garden garden = new Garden(30, 50);
        Food food = new Food();
        AstarBrain brain = new AstarBrain();

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
        AstarBrain brain = new AstarBrain();

        // when
        Node lowestCost = brain.getLowestCost(openNodes);

        // then
        assertEquals(openNodes.get(1), lowestCost);
    }

}