package smartsnake.astar;

import java.awt.*;

public class DistanceFormula {

    public static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) +
                (a.y - b.y) * (a.y - b.y));
    }

}
