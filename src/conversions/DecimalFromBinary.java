package conversions;

/**
 * Created by rich on 20/10/15.
 */
public class DecimalFromBinary<T> {

    public float decimalFromBinary(T[] c) {
        float decimalValue = 0;
        int size = c.length;
        for (int i = 0; i < size; i++) {
            if ((Integer) c[i] == 1) {
                decimalValue += Math.pow(2, size - i - 1);
            }
        }
        return decimalValue;
    }
}
