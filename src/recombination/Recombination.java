package recombination;

import chromosomes.Chromosome;
import java.util.Random;
import population.Population;
import population.PopulationFactory;

/**
 *
 * @author rich
 */
public abstract class Recombination {
    
    protected final Random seed;
    protected final PopulationFactory populationFactory;
    protected final double probability;
    
    protected Recombination(Random seed,
            PopulationFactory populationFactory,
            double probability) {
        this.seed = seed;
        this.populationFactory = populationFactory;
        this.probability = probability;
    }

    public Population recombine(Population population){
        Population newp = populationFactory.createNew();
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                newp.set(i, recombine(
                        population.get(i),
                        population.get(i + 1 % population.size() - 1)));
            } else {
                newp.set(i, population.get(i));
            }
        }
        return newp;
    }
    
    public abstract Chromosome recombine(Chromosome c1,Chromosome c2);
}
