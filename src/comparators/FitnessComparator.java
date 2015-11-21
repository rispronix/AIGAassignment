package comparators;

import chromosomes.Chromosome;

/**
 *
 * @author rich
 */
public interface FitnessComparator {
    
    public int compare(Chromosome c1, Chromosome c2);
}
