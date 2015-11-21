package GA.function2;

import chromosomes.BinaryChromosome;
import chromosomes.NewBaseChromosome;
import chromosomes.NewBaseChromosomeFactory;
import conversions.DecimalFromBinary;
import java.util.Random;
import mutation.NewBinaryMutation;
import recombination.NewSinglePointCrossover;

/**
 *
 * @author rich
 */
public class NewFunction2Binary extends BaseFunction2 {

    /*
     hardcoded as two variables each with range of 31 is 12 bits
     */
    private final int chromosomeSize = 12;

    public NewFunction2Binary(Random seed) {
        super(seed);
    }

    public NewFunction2Binary(Random seed,
            double recombinationProbability,
            double mutationProbability) {
        super(seed, recombinationProbability,
                mutationProbability);
    }

    public NewFunction2Binary(Random seed,
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
        ff = (NewBaseChromosome c) -> {
            float x = dfb.decimalFromBinary(
                    c.getGenes(0, 5)) - 15;
            float y = dfb.decimalFromBinary(
                    c.getGenes(5, c.size())) - 15;
            return (float) (0.26 * (x * x * y * y)
                    - 0.48 * x * y);
        };
    }

    @Override
    public void setupChromosomeFactory() {
        chromosomeFactory = new NewBaseChromosomeFactory() {

            @Override
            public NewBaseChromosome createNew() {
                return new BinaryChromosome(seed, chromosomeSize) {

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
        recombination = new NewSinglePointCrossover(seed,
                populationFactory,
                recombinationProbability);
    }

    @Override
    public void setupMutation() {
        mutation = new NewBinaryMutation(seed,
                mutationProbability);
    }

}
