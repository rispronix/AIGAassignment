package comparators;

import chromosomes.BaseChromosome;

/**
 *
 * @author rich
 */
public interface FitnessComparator {

    public int compare(BaseChromosome c1, BaseChromosome c2);
}
