package ga.function3;

import java.util.Random;
import selection.BestOfFour;

/**
 *
 * @author rich
 */
public class Function3MergeRecombineAndBoFSelection extends Function3MergeRecombine {

    public Function3MergeRecombineAndBoFSelection(Random seed, int chromosomeSize) {
        super(seed, chromosomeSize);
        title = "Function3MergeAndBoFVar"+chromosomeSize;
    }

    public Function3MergeRecombineAndBoFSelection(Random seed,
            double recombinationProbability,
            double mutationProbability,
            int chromosomeSize) {
        super(seed, recombinationProbability,
                mutationProbability,
                chromosomeSize);
        title = "Function3MergeAndBoFVar"+chromosomeSize;
    }

    public Function3MergeRecombineAndBoFSelection(Random seed,
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
        title = "Function3MergeAndBoFVar"+chromosomeSize;
    }

    @Override
    public void setupSelection() {
        selection = new BestOfFour(seed, comparator,
                populationFactory);
    }
}
