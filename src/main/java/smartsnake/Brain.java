package smartsnake;

import org.jetbrains.annotations.Nullable;

interface Brain {

    @Nullable
    Direction move(Snake snake, Food food, Garden garden);

}
