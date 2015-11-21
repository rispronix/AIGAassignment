package main;

import GA.function1.Function1;
import GA.function2.Function2Binary;
import GA.function2.Function2Float;
import GA.function3.Function3;
import average.AverageNRuns;

import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

        Random seed = new Random(1);
//        Random seed = new Random(System.currentTimeMillis());

//        new Function1(seed).run();
//        new Function2Binary(seed).run();
//        new Function2Float(seed).run();
//        new Function3(seed,500,500,0.5,0.05,10).run();
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.09, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.08, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.07, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.06, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.05, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.04, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.03, 10));
        new AverageNRuns(10, new Function3(seed, 500, 500, 1,    0.02, 10));
    }
}
