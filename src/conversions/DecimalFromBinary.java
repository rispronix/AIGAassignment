package conversions;

import chromosomes.BaseChromosome;


/**
 * Created by rich on 20/10/15.
 */
public class DecimalFromBinary {
    public static float decimalFromBinary(BaseChromosome c) {
        float decimalValue = 0;
        int size = c.size();
        for (int i = 0; i < size; i++) {
            if ((Integer) c.getGene(i) == 1) {
                decimalValue += Math.pow(2, size - i - 1);
            }
        }
        return decimalValue;
    }
}
