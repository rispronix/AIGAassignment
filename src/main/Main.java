package main;

import geneticAlgorithm.function1.Function1;
import geneticAlgorithm.function2.Function2Binary;
import geneticAlgorithm.function2.Function2Float;
import geneticAlgorithm.function3.Function3;
import average.AverageNRuns;
import geneticAlgorithm.function3.Function3MergeRecombination;
import geneticAlgorithm.parameterTuning.ProbabilitySearch;

import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

//        Random seed = new Random(1);
        Random seed = new Random(System.currentTimeMillis());

//        new Function1(seed).run();
//        new Function2Binary(seed).run();
//        new Function2Float(seed).run();
//        new Function3(seed,500,500,0.5,0.05,10).run();
//        new AverageNRuns(10, new Function3MergeRecombination(seed, 500, 500, 1, 0.1,  10));
        new AverageNRuns(10, new Function3MergeRecombination(seed, 200, 200, 1, 0.1, 10));
        new AverageNRuns(1, new ProbabilitySearch(seed, 200, 200, 1, 0.1));
    }
}
