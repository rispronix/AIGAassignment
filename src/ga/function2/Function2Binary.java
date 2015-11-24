package ga.function2;

import chromosomes.BinaryChromosome;
import chromosomes.ChromosomeFactory;
import chromosomes.Chromosome;
import conversions.DecimalFromBinary;
import java.util.Random;
import mutation.BinaryMutation;
import recombination.SinglePointCrossover;

/**
 *
 * @author rich
 */
public class Function2Binary extends BaseFunction2 {

    /*
     hardcoded as two variables each with range of 31 is 12 bits
     */
    private final int chromosomeSize = 12;

    public Function2Binary(Random seed) {
        super(seed);
    }

    public Function2Binary(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, recombinationProbability,
                mutationProbability);
    }

    public Function2Binary(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability);
    }

    @Override
    public void setupFitnessFunction() {
        DecimalFromBinary dfb = new DecimalFromBinary();
        ff = (Chromosome c) -> {
            double x = dfb.decimalFromBinary(c.getGenes(0, 5)) - 15;
            double y = dfb.decimalFromBinary(c.getGenes(5, c.size())) - 15;
            return  0.26 * (x * x + y * y)
                    - 0.48 * x * y;
        };
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new ChromosomeFactory() {

            @Override
            public Chromosome createNew() {
                return new BinaryChromosome(seed, chromosomeSize) {

                    @Override
                    public double evaluate() {
                        return fitness = ff.calculate(this);
                    }
                }.initialise();
            }
        };
    }

    @Override
    public void setupRecombination() {
        recombination = new SinglePointCrossover(seed,
                populationFactory,
                recombinationProbability);
    }

    @Override
    public void setupMutation() {
        mutation = new BinaryMutation(seed,
                mutationProbability);
    }
}
