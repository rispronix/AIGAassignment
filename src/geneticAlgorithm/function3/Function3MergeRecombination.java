
package geneticAlgorithm.function3;

import java.util.Random;
import recombination.FloatMergeRecombination;

/**
 *
 * @author rich
 */
public class Function3MergeRecombination extends Function3{

    public Function3MergeRecombination(Random seed, 
            int chromosomeSize) {
        super(seed, chromosomeSize);
    }

    public Function3MergeRecombination(Random seed, 
            double recombinationProbability, 
            double mutationProbability, 
            int chromosomeSize) {
        super(seed, recombinationProbability,
                mutationProbability, 
                chromosomeSize);
    }

    public Function3MergeRecombination(Random seed,
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
    public void setupRecombination(){
        recombination = new FloatMergeRecombination(seed, 
                populationFactory, 
                recombinationProbability,
                chromosomeFactory);
    }
}
