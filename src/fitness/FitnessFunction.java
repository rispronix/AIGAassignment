package fitness;

import chromosomes.Chromosome;

/**
 *
 * @author rich
 */
public interface FitnessFunction {
    
    double calculate(Chromosome c);
    
    
}
