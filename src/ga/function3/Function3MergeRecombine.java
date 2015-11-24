package ga.function3;

import java.util.Random;
import recombination.FloatMergeRecombination;

/**
 *
 * @author rich
 */
public class Function3MergeRecombine extends Function3 {

    public Function3MergeRecombine(Random seed,
            int chromosomeSize) {
        super(seed, chromosomeSize);
        title = "Function3MergeVar" + chromosomeSize;
    }

    public Function3MergeRecombine(Random seed,
            double recombinationProbability,
            double mutationProbability,
            int chromosomeSize) {
        super(seed, recombinationProbability,
                mutationProbability,
                chromosomeSize);
        title = "Function3MergeVar" + chromosomeSize;
    }

    public Function3MergeRecombine(Random seed,
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
        title = "Function3MergeVar" + chromosomeSize;
    }

    @Override
    public void setupRecombination() {
        recombination = new FloatMergeRecombination(seed,
                populationFactory,
                recombinationProbability,
                chromosomeFactory);
    }
}
