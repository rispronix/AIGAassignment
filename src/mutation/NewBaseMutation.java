package mutation;

import chromosomes.NewBaseChromosome;
import java.util.Random;
import population.NewPopulation;

/**
 *
 * @author rich
 */
public abstract class NewBaseMutation {
    
    protected Random seed;
    protected double probability;
    
    public NewBaseMutation(Random seed,double probability){
        this.seed=seed;
        this.probability=probability;
    }
    
    public NewPopulation mutate(NewPopulation population){
        for (int i = 0; i < population.size(); i++) {
            if (seed.nextDouble() < probability) {
                population.set(i, mutateGene(population.get(i)));
            }
        }
        return population;
    }
    
    public abstract NewBaseChromosome mutateGene(NewBaseChromosome c);
}
