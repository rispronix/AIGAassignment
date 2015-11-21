package fitness;

import chromosomes.Chromosome;

/**
 *
 * @author rich
 */
public interface FitnessFunction {
    
    float calculate(Chromosome c);
}
