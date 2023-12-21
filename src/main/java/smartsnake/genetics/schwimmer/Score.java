package smartsnake.genetics.schwimmer;

import org.jetbrains.annotations.NotNull;

public record Score(int size, int tickCounter) implements Comparable<Score> {
    @Override
    public int compareTo(@NotNull Score o) {
        if (o.size < size) {
            return 1;
        } else if (o.size > size) {
            return -1;
        } else {
            return o.tickCounter - tickCounter;
        }
    }
}
