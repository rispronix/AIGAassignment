package comparators;

import chromosomes.NewBaseChromosome;

/**
 *
 * @author rich
 */
public interface BaseFitnessComparator {
    
    public int compare(NewBaseChromosome c1, NewBaseChromosome c2);
}
