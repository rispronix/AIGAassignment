package fitness;

import chromosome.BaseChromosome;


/**
 *
 * @author rich
 */
public interface FitnessFunction {
    float calculate(BaseChromosome c);
}
