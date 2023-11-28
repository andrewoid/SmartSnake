package smartsnake.astar;

import org.junit.jupiter.api.Test;
import smartsnake.Direction;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    public void getFromEnd() {
        // given
        Point nodeLocation = new Point(4, 5);
        Point endLocation = new Point(1, 1);

        // when
        Node node = new Node(nodeLocation, endLocation);

        // then
        assertEquals(5, node.getFromEnd(), 0.0001);
    }

    @Test
    public void getFromStart() {
        // given
        Point nodeLocation = new Point(4, 5);
        Point parentLocation = new Point(4, 4);
        Point endLocation = new Point(1, 1);
        Node parentNode = new Node(parentLocation, endLocation);
        Node node = new Node(nodeLocation, endLocation);

        // when
        node.setParent(parentNode, Direction.Down);

        // then
        assertEquals(10, node.getFromStart());
        assertEquals(Direction.Down, node.getParentDirection());
    }

    @Test
    public void getDirectionFromStart() {
        // given
        Point aLocation = new Point(3, 4); // start
        Point bLocation = new Point(4, 4);
        Point cLocation = new Point(4, 5); // end
        Node a = new Node(aLocation, cLocation);
        Node b = new Node(bLocation, cLocation);
        Node c = new Node(cLocation, cLocation);

        // when
        b.setParent(a, Direction.Right);
        c.setParent(b, Direction.Down);

        // then
        assertEquals(Direction.Right, c.getDirectionFromStart());
    }

}