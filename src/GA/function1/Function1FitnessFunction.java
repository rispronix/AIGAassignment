package GA.function1;

import chromosomes.BaseChromosome;
import conversions.DecimalFromBinary;
import fitness.FitnessFunction;

/**
 *
 * @author rich
 */
public class Function1FitnessFunction implements FitnessFunction {

    DecimalFromBinary dfb = new DecimalFromBinary();

    @Override
    public float calculate(BaseChromosome c) {
            return (float) Math.pow(dfb.decimalFromBinary(
                    c.getGenes(0, c.size())), 2);
    }

}
