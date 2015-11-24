package ga.function3;

import java.util.Random;
import selection.BestOfFour;

/**
 *
 * @author rich
 */
public class Function3BestOfFourSelection extends Function3MergeRecombination {

    public Function3BestOfFourSelection(Random seed, int chromosomeSize) {
        super(seed, chromosomeSize);
    }

    public Function3BestOfFourSelection(Random seed,
            double recombinationProbability,
            double mutationProbability,
            int chromosomeSize) {
        super(seed, recombinationProbability,
                mutationProbability,
                chromosomeSize);
    }

    public Function3BestOfFourSelection(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability,
            int chromosomeSize) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability,
                chromosomeSize);
    }

    @Override
    public void setupSelection() {
        selection = new BestOfFour(seed, comparator,
                populationFactory);
    }
}
