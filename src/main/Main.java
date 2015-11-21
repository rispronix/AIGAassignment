package main;

import GA.function1.Function1;
import GA.function2.NewFunction2Binary;
import GA.function2.NewFunction2Float;

import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

//        Random seed = new Random(1);
        Random seed = new Random(System.currentTimeMillis());

        new Function1(seed).run();
        new NewFunction2Binary(seed).run();
        new NewFunction2Float(seed).run();
    }
}
