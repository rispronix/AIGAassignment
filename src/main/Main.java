package main;

import geneticAlgorithm.function1.Function1;
import geneticAlgorithm.function2.Function2Binary;
import geneticAlgorithm.function2.Function2Float;
import geneticAlgorithm.function3.Function3;
import average.AverageNRuns;
import geneticAlgorithm.function3.Function3BestOfFourSelection;
import geneticAlgorithm.function3.Function3MergeRecombination;
import geneticAlgorithm.parameterSearch.ProbabilitySearch;

import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

//        Random seed = new Random(1);
        Random seed = new Random(System.currentTimeMillis());

        System.out.print("\nRunning Function1");
        new AverageNRuns(10, new Function1(seed, 50, 50, 0.9, 0.3));

        System.out.print("\nRunning Function2Binary");
        new AverageNRuns(10, new Function2Binary(seed, 50, 50, 0.9, 0.3));

        System.out.print("\nRunning Function2Float");
        new AverageNRuns(10, new Function2Float(seed, 50, 50, 0.9, 0.3));

        System.out.print("\nRunning Function3");
        new AverageNRuns(10, new Function3(seed, 100, 100, 1, 0.1, 10));

        System.out.print("\nRunning Function3 with merge recombination");
        new AverageNRuns(10,
                new Function3MergeRecombination(seed, 100, 100, 1, 0.1, 10));

        System.out.print("\nRunning Function3 with best of four tournament "
                + "selection and merge recombination");
        new AverageNRuns(10,
                new Function3BestOfFourSelection(seed, 100, 100, 0.9, 0.8, 10));

        System.out.print("\nRunning probability parameter search on function"
                + " 3 with best of four selection and merge recombination");
        new ProbabilitySearch(seed, 10, 100, 0.9, 0.8,
                new Function3BestOfFourSelection(seed,
                        100, 100, 0.9, 0.8, 10)).run();
    }
}
