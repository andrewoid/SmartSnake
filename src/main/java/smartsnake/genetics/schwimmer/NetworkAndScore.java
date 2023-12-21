package smartsnake.genetics.schwimmer;

import basicneuralnetwork.NeuralNetwork;
import org.jetbrains.annotations.NotNull;
import smartsnake.Garden;

public record NetworkAndScore(Garden garden, NeuralNetwork network, Score score)
        implements Comparable<NetworkAndScore> {

    @Override
    public int compareTo(@NotNull NetworkAndScore o) {
        return o.score.compareTo(score);
    }
}
