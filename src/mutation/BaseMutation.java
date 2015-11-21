package mutation;

import chromosomes.BaseChromosome;
import java.util.Random;
import population.Population;

/**
 *
 * @author r2-rowley
 */
public abstract class BaseMutation {

    private final Random seed;
    private final double probability;

    public BaseMutation(Random seed, double probability) {
        this.seed = seed;
        this.probability = probability;
    }

    public Population mutate(Population population) {
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                population.set(i, mutateGene(population.get(i)));
            }
        }

        return population;
    }

    public abstract BaseChromosome mutateGene(BaseChromosome c);
}
