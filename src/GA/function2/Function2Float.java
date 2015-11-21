package GA.function2;

import java.util.Random;

import chromosomes.BaseChromosome;
import chromosomes.BaseChromosomeFactory;
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
    private final int chromosomeSize = 2;

    public Function2Float(Random seed) {
        super(seed);
    }

    public Function2Float(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, recombinationProbability,
                mutationProbability);
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
    }

    @Override
    public void setupFitnessFunction() {
        ff = (BaseChromosome c) -> {
            float x = (float) c.getGene(0);
            float y = (float) c.getGene(1);
            return (float) (0.26 * (x * x * y * y)
                    - 0.48 * x * y);
        };
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new BaseChromosomeFactory() {

            @Override
            public BaseChromosome createNew() {
                return new BaseChromosome(chromosomeSize) {

                    @Override
                    public BaseChromosome initialise() {
                        genes = new Float[chromosomeSize];
                        for (int i = 0; i < chromosomeSize; i++) {
                            genes[i] = seed.nextFloat() * 31 - 15;
                        }
                        return this;
                    }

                    @Override
                    public float evaluate() {
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
