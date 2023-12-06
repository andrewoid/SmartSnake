package smartsnake.astar;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceFormulaTest {

    @Test
    void distance() {
        // given
        Point a = new Point(4, 5);
        Point b = new Point(1, 1);

        // when
        double distance = DistanceFormula.distance(a, b);

        // then
        assertEquals(5, distance, 0.0001);
    }
}