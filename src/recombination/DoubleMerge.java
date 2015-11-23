package recombination;

import java.util.Random;
import chromosomes.ChromosomeFactory;
import chromosomes.Chromosome;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class DoubleMerge extends Recombination {

    ChromosomeFactory chromosomeFactory;

    public DoubleMerge(Random seed,
            PopulationFactory populationFactory,
            double probability, 
            ChromosomeFactory chromosomeFactory) {
        super(seed, populationFactory, probability);
        this.chromosomeFactory = chromosomeFactory;
    }

    @Override
    public Chromosome recombine(Chromosome c1, Chromosome c2) {
        Chromosome child = chromosomeFactory.createNew();
        double c1Gene, c2Gene, mutationValue;
        for (int i = 0; i < c1.size(); i++) {
            c1Gene = (double) c1.getGene(i);
            c2Gene = (double) c2.getGene(i);
            mutationValue = (double) seed.nextDouble() * 
                    Math.abs(c1Gene - c2Gene);
            child.setGene(i, Math.min(c1Gene, c2Gene) + mutationValue);
        }
        return child;
    }
}
