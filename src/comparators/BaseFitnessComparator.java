package comparators;

import chromosomes.BaseChromosome;

/**
 *
 * @author rich
 */
public interface BaseFitnessComparator {
    
    public int compare(BaseChromosome c1, BaseChromosome c2);
}
