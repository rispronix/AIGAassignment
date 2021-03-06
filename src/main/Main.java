package main;

import ga.function1.Function1;
import ga.function2.Function2Binary;
import ga.function2.Function2FloatGaussianDistMutation;
import ga.function3.Function3;
import average.AverageNRuns;
import ga.function2.Function2FloatUniformDistMutation;
import ga.function3.Function3MergeRecombineAndBoFSelection;
import ga.function3.Function3MergeRecombine;
import ga.parameterSearch.ProbabilitySearch;
import ga.tsp.TSPGA;

import java.util.Random;

/**
 *
 * @author rich
 */
public class Main {

    public static void main(String[] args) {

        Random seed = new Random(1);
//        Random seed = new Random(System.currentTimeMillis());

//        System.out.print("\nExperiment 1a: Function1");
//        new AverageNRuns(10, new Function1(seed, 50, 5, 0.9, 0.3));
//        new ProbabilitySearch(seed, 10, 50, 0.9, 0.5, new Function1(seed, 10, 10, 0.9, 0.5)).run();

//        System.out.print("\nExperiment 1b: Function1");
//        new AverageNRuns(10, new Function1(seed, 50, 10, 0.9, 0.3));
//        new ProbabilitySearch(seed, 10, 50, 0.9, 0.5, new Function1(seed, 25, 25, 0.9, 0.5)).run();

//        System.out.print("\nExperiment 1c: Function1");
//        new AverageNRuns(10, new Function1(seed, 50, 20, 0.9, 0.3));
//        new ProbabilitySearch(seed, 10, 50, 0.9, 0.5, new Function1(seed, 50, 50, 0.9, 0.5)).run();

//        System.out.print("\nExperiment 2: Function2Binary");
//        new AverageNRuns(10, new Function2Binary(seed, 50, 50, 0.9, 0.5));

//        System.out.print("\nExperiment 3a: Function2FloatGaussianDistMutation");
//        new AverageNRuns(10, new Function2FloatGaussianDistMutation(seed, 50, 50, 0.9, 0.5));
        
//        System.out.print("\nExperiment 3b: Function2FloatUniformDistMutation");
//        new AverageNRuns(10, new Function2FloatUniformDistMutation(seed, 50, 50, 0.9, 0.5));

//        System.out.print("\nExperiment 4: Function3 (10 variables)");
//        new AverageNRuns(10, new Function3(seed, 200, 200, 0.9, 0.5, 10));

//        System.out.print("\nExperiment 5: Function3 with merge recombination (10 variables)");
//        new AverageNRuns(10, new Function3MergeRecombine(seed, 200, 200, 0.9, 0.5, 10));

//        System.out.print("\nExperiment 6: Function3 with best of four tournament selection and merge recombination (10 variables)");
//        new AverageNRuns(10, new Function3MergeRecombineAndBoFSelection(seed, 200, 200, 0.9, 0.5, 10));

//        System.out.print("\nExperiment 7: Function3 with best of four tournament selection and merge recombination (20 variables)");
//        new AverageNRuns(10, new Function3MergeRecombineAndBoFSelection(seed, 200, 200, 0.9, 0.5, 20));
        
        System.out.print("\nExperiment 7: Function3 with best of four tournament selection and merge recombination (50 variables)");
        new AverageNRuns(10, new Function3MergeRecombineAndBoFSelection(seed, 2000, 2000, 0.9, 0.5, 50));

//        System.out.print("\nExperiment 8: probability parameter search on function 3 with best of four selection and merge recombination (20 variables)");
//        new ProbabilitySearch(seed, 10, 100, 0.9, 0.5, new Function3MergeRecombineAndBoFSelection(seed, 200, 100, 0.9, 0.8, 20)).run();

//        System.out.print("\nExperiment 9: TSPGA");
//        new AverageNRuns(10, new TSPGA(seed, 10));

//        System.out.print("\nExperiment 10: probability parameter search on TSPGA");
//        new ProbabilitySearch(seed, 10, 100, 0.9, 0.5, new TSPGA(seed, 10)).run();
    }
}
