package main;

import geneticAlgorithm.function1.Function1;
import geneticAlgorithm.function2.Function2Binary;
import geneticAlgorithm.function2.Function2Float;
import geneticAlgorithm.function3.Function3;
import average.AverageNRuns;
import geneticAlgorithm.GA;
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

        new AverageNRuns(10,  new Function1(seed, 50, 50, 0.9, 0.3));
//        new AverageNRuns(1, new ProbabilitySearch(seed, 10, 10, 0.9, 0.2, new Function1(seed, 50, 50, 0.9, 0.3)));
//        new AverageNRuns(1, new ProbabilitySearch(seed, 10, 10, 0.9, 0.2, new ProbabilitySearch(seed, 100, 100, new Function1(seed, 50, 50, 0.9, 0.3))));
        
//        new AverageNRuns(10,  new Function2Binary(seed, 50, 50, 0.9, 0.3));
//        new AverageNRuns(1, new ProbabilitySearch(seed, 100, 100, 0.9, 0.5, new Function2Binary(seed, 50, 50, 0.9, 0.3)));
        
//        new AverageNRuns(10,  new Function2Float(seed, 50, 50, 0.9, 0.3));
//        new AverageNRuns(1, new ProbabilitySearch(seed, 100, 100, 0.9, 0.5, new Function2Float(seed, 50, 50, 0.9, 0.3)));
        
//        new AverageNRuns(10, new Function3(seed,100,100, 1, 0.1, 10));
//        new AverageNRuns(10, new Function3MergeRecombination(seed,100,100, 1, 0.1, 10));
//        new AverageNRuns(10, new Function3BestOfFourSelection(seed,100,100, 0.9, 0.8, 10));
//        new AverageNRuns(1, new ProbabilitySearch(seed, 100, 100, 0.9, 0.5, new Function3BestOfFourSelection(seed, 100, 100, 10)));
    }
}
