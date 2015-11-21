package mutation;

import chromosomes.Chromosome;
import java.util.Random;
import population.Population;

/**
 *
 * @author rich
 */
public abstract class Mutation {
    
    protected Random seed;
    protected double probability;
    
    public Mutation(Random seed,double probability){
        this.seed=seed;
        this.probability=probability;
    }
    
    public Population mutate(Population population){
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                population.set(i, mutateGene(population.get(i)));
            }
        }
        return population;
    }
    
    public abstract Chromosome mutateGene(Chromosome c);
}
