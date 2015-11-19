package recombination;

import chromosomes.BaseChromosome;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public class Recombination {

    private final Random seed;
    private final PopulationFactory populationFactory;
    private final double probability;

    public Recombination(Random seed,
            PopulationFactory populationFactory,
            double probability) {
        this.seed = seed;
        this.populationFactory = populationFactory;
        this.probability = probability;
    }

    public Population singlepointCrossover(Population population) {
//        population = populationFactory.createCopy(population);
        Population newp = populationFactory.createNew();
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                newp.set(i, singlepointCrossover(
                        population.getElement(i),
                        population.getElement(i + 1 % population.size() - 1)));
            }
        }
        return newp;
    }

    public BaseChromosome singlepointCrossover(BaseChromosome c1,
            BaseChromosome c2) {
        int pivot = seed.nextInt(c1.size());
        for (int i = pivot; i < c1.size(); i++) {
            c1.setGene(i, c2.getGene(i));
        }
        return c1;
    }
}
