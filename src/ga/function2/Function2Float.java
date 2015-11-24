package ga.function2;

import java.util.Random;

import chromosomes.ChromosomeFactory;
import chromosomes.Chromosome;
import mutation.FloatGaussianMutation;
import recombination.SinglePointCrossover;

/**
 *
 * @author rich
 */
public class Function2Float extends BaseFunction2 {

    /*
     hardcoded as only two variables needed
     */
    protected final int chromosomeSize = 2;

    public Function2Float(Random seed) {
        super(seed);
        title = "Function2Float";
    }

    public Function2Float(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, recombinationProbability,
                mutationProbability);
        title = "Function2Float";
    }

    public Function2Float(Random seed,
            int generationCount,
            int populationSize,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, generationCount,
                populationSize,
                recombinationProbability,
                mutationProbability);
        title = "Function2Float";
    }

    @Override
    public void setupFitnessFunction() {
        ff = (Chromosome c) -> {
            float x = (float) c.getGene(0);
            float y = (float) c.getGene(1);
            return (float) (0.26 * (x * x + y * y)
                    - 0.48 * x * y);
        };
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new ChromosomeFactory() {

            @Override
            public Chromosome createNew() {
                return new Chromosome(chromosomeSize) {

                    @Override
                    public Chromosome initialise() {
                        genes = new Float[chromosomeSize];
                        for (int i = 0; i < chromosomeSize; i++) {
                            genes[i] = seed.nextFloat() * 31 - 15;
                        }
                        return this;
                    }

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
        mutation = new FloatGaussianMutation(seed,
                mutationProbability);
    }

}
