package smartsnake;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Food extends Point {
    public Food() {
    }

    public Food(@NotNull Point p) {
        super(p);
    }

    public Food(int x, int y) {
        super(x, y);
    }
}
