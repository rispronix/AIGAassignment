package fitness;

import chromosomes.NewBaseChromosome;

/**
 *
 * @author rich
 */
public interface NewFitnessFunction {
    
    float calculate(NewBaseChromosome c);
}
