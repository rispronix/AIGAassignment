package recombination;

import java.util.Random;
import chromosomes.Chromosome;
import chromosomes.ChromosomeFactory;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class FloatMergeRecombination extends Recombination {

    ChromosomeFactory chromosomeFactory;

    public FloatMergeRecombination(Random seed,
            PopulationFactory populationFactory,
            double probability, 
            ChromosomeFactory chromosomeFactory) {
        super(seed, populationFactory, probability);
        this.chromosomeFactory = chromosomeFactory;
    }

    @Override
    public Chromosome recombine(Chromosome c1, Chromosome c2) {
        Chromosome child = chromosomeFactory.createNew();
        float c1Gene, c2Gene, mutationValue;
        for (int i = 0; i < c1.size(); i++) {
            c1Gene = (float) c1.getGene(i);
            c2Gene = (float) c2.getGene(i);
            mutationValue = (float) seed.nextDouble() * Math.abs(c1Gene - c2Gene);
            child.setGene(i, Math.min(c1Gene, c2Gene) + mutationValue);
        }
        return child;
    }
}
