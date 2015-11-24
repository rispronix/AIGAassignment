package main;

import ga.function1.Function1;
import ga.function2.Function2Binary;
import ga.function2.Function2Float;
import ga.function3.Function3;
import average.AverageNRuns;
import ga.function3.Function3BestOfFourSelection;
import ga.function3.Function3MergeRecombination;
import ga.parameterSearch.ProbabilitySearch;
import ga.tsp.TSPGA;

import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

//        Random seed = new Random(1);
        Random seed = new Random(System.currentTimeMillis());

        System.out.print("\nExperiment 1: Function1");
        new AverageNRuns(10, new Function1(seed, 50, 50, 0.9, 0.5));

        System.out.print("\nExperiment 2: Function2Binary");
        new AverageNRuns(10, new Function2Binary(seed, 50, 50, 0.9, 0.5));

        System.out.print("\nExperiment 3: Function2Float");
        new AverageNRuns(10, new Function2Float(seed, 50, 50, 0.9, 0.5));

        System.out.print("\nExperiment 4: Function3 (10 variables)");
        new AverageNRuns(10, new Function3(seed, 100, 100, 0.9, 0.5, 10));

        System.out.print("\nExperiment 5: Function3 with merge recombination (10 variables)");
        new AverageNRuns(10, new Function3MergeRecombination(seed, 100, 100, 0.9, 0.5, 10));

        System.out.print("\nExperiment 6: Function3 with best of four tournament selection and merge recombination (10 variables)");
        new AverageNRuns(10, new Function3BestOfFourSelection(seed, 100, 100, 0.9, 0.5, 10));

        System.out.print("\nExperiment 7: Function3 with best of four tournament selection and merge recombination (20 variables)");
        new AverageNRuns(10, new Function3BestOfFourSelection(seed, 100, 100, 0.9, 0.5, 20));

        System.out.print("\nExperiment 8: probability parameter search on function 3 with best of four selection and merge recombination (20 variables)");
        new ProbabilitySearch(seed, 10, 100, 0.9, 0.5, new Function3BestOfFourSelection(seed, 100, 100, 0.9, 0.8, 20)).run();

        System.out.print("\nExperiment 9: TSPGA");
        new AverageNRuns(10, new TSPGA(seed, 10));

        System.out.print("\nExperiment 10: probability parameter search on TSPGA");
        new ProbabilitySearch(seed, 10, 100, 0.9, 0.5, new TSPGA(seed, 10)).run();
    }
}
