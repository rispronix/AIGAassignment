package recombination;

import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import java.util.Arrays;
import java.util.Random;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class PermutationRecombination extends Recombination {

    protected final ChromosomeFactory chromosomeFactory;
    protected int pivot, index;
    protected Chromosome child;

    public PermutationRecombination(Random seed,
            PopulationFactory populationFactory,
            double probability,
            ChromosomeFactory chromosomeFactory) {
        super(seed, populationFactory, probability);
        this.chromosomeFactory = chromosomeFactory;
    }

    @Override
    public Chromosome recombine(Chromosome c1, Chromosome c2) {

        pivot = seed.nextInt(c1.size());
        child = chromosomeFactory.createNew();
        for (int i = 0; i < pivot; i++) {
            child.setGene(i, c1.getGene(i));
        }

        boolean isAlreadyContained = false;
        int indexInQuestion = 0;

        for (int i = pivot; i < child.size(); i++) {
            for (int j = 0; j < child.size(); j++) {

                // parent 2 element in question
                int valueInQuestion = (Integer) c2.getGene(j);
                int addedSoFar = pivot;
                for (int k = 0; k < addedSoFar; k++) {
                    if ((Integer) child.getGene(k) == valueInQuestion) {
                        isAlreadyContained = true;
                    }
                }
                if (!isAlreadyContained) {
                    child.setGene(i, valueInQuestion);
                    addedSoFar++;
                }
            }
        }
        return child;
    }
}
//is value contained
//loop through
// if found mark true
