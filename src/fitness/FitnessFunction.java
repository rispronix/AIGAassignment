package fitness;

import chromosomes.BaseChromosome;


/**
 *
 * @author rich
 */
public interface FitnessFunction {
    float calculate(BaseChromosome c);
}
