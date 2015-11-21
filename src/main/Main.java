package main;

import GA.function1.Function1;
import GA.function1.newFunction1;
//import GA.function3.Function3WithGAParameterTuning;
import GA.function2.Function2Binary;
import GA.function2.Function2Float;
import GA.function3.Function3;
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
        new Function2Binary(seed);
        new Function2Float(seed);
        new Function3(seed).run();
        Function3 f = new Function3(seed,0.05,0.5);
//        Function3WithGAParameterTuning f = new Function3WithGAParameterTuning();
//        System.out.println("f best fitness: "+ f.getBestFitness());
//        System.out.println("f mutation probability: "+ f.getMutationProbability());
//        System.out.println("f recombination probability: "+ f.getRecombinationProbability());
//        new newFunction1(seed);
    }
}
