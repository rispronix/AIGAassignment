package average;

import geneticAlgorithm.GA;
import java.util.ArrayList;
import stats.RunStatistics;

/**
 *
 * @author rich
 */
public class AverageNRuns {

    public AverageNRuns(int numberOfRunsToAverage, GA function) {
        System.out.println("Averageing GA " + numberOfRunsToAverage + " times.\n");
//        System.out.print(function.toString()+'\n');
        ArrayList<ArrayList<RunStatistics>> runs = new ArrayList();
        double sum = 0;
        for (int i = 1; i <= numberOfRunsToAverage; i++) {
            function.run();
            runs.add(function.getStats());
            sum += function.getBest().fitness();
            System.out.printf("\nbest of run %d:\t", i);
            System.out.print(function.getBest());
            function.resetBest();
        }

//        for (int i = 0; i < numberOfRunsToAverage; i++) {
//            System.out.printf("\nbest fitness of run %d:\t %.2f",i+1, runs.get(i).get(runs.get(i).size() - 1).getBest().fitness());
//        }
        double average = sum / numberOfRunsToAverage;
        System.out.printf("\nAverage over %d runs: %.2f\n\n", numberOfRunsToAverage, average);
    }
}
