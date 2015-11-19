package main;

import function1.Function1;
import function2.Function2Binary;
import function2.Function2Float;
import function3.Function3;
import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

//        Random seed = new Random(1);
        Random seed = new Random(System.currentTimeMillis());

        new Function1(seed);
        new Function2Binary(seed);
        new Function2Float(seed);
        new Function3(seed);
    }
}
