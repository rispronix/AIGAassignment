package comparators;

import chromosomes.BaseChromosome;

/**
 *
 * @author rich
 */
public class CompareMin implements BaseFitnessComparator {

    @Override
    public int compare(BaseChromosome c1, BaseChromosome c2) {
        return c1.fitness() > c2.fitness() ? -1 : c2.fitness() > c1.fitness() ? 1 : 0;
    }

}
