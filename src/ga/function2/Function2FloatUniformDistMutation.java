package ga.function2;

import java.util.Random;
import mutation.FloatGaussianMutation;

/**
 *
 * @author r2-rowley
 */
public class Function2FloatUniformDistMutation extends Function2FloatGaussianDistMutation{

    public Function2FloatUniformDistMutation(Random seed) {
        super(seed);
        title = "Function2FloatUniformMutation";
    }

    public Function2FloatUniformDistMutation(Random seed, double recombinationProbability, double mutationProbability) {
        super(seed, recombinationProbability, mutationProbability);
        title = "Function2FloatUniformMutation";
    }

    public Function2FloatUniformDistMutation(Random seed, int generationCount, int populationSize, double recombinationProbability, double mutationProbability) {
        super(seed, generationCount, populationSize, recombinationProbability, mutationProbability);
        title = "Function2FloatUniformMutation";
    }
    
    @Override
    public void setupMutation() {
        mutation = new FloatGaussianMutation(seed,
                mutationProbability);
    }
}
