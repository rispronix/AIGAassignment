package GA.function1;

import chromosomes.NewBaseChromosome;
import conversions.DecimalFromBinary;
import fitness.NewFitnessFunction;

/**
 *
 * @author rich
 */
public class Function1FitnessFunction implements NewFitnessFunction {

    DecimalFromBinary dfb = new DecimalFromBinary();

    @Override
    public float calculate(NewBaseChromosome c) {
        return (float) Math.pow(dfb.decimalFromBinary(
                c.getGenes(0, c.size())), 2);
    }
}
