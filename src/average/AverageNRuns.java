package average;

import fileWriter.WriteToCSV;
import ga.GA;
import java.util.ArrayList;
import stats.RunStatistics;

/**
 *
 * @author rich
 */
public class AverageNRuns {

    public AverageNRuns(int numberOfRunsToAverage, GA function) {
        System.out.print("\nAveraging GA " + numberOfRunsToAverage + " times.");
        System.out.print('\n'+function.toString());
        ArrayList<ArrayList<RunStatistics>> runs = new ArrayList();
        double sum = 0;
        for (int i = 1; i <= numberOfRunsToAverage; i++) {
            function.run();
            runs.add(function.getStats());
            sum += function.getBest().fitness();
            System.out.printf("\nBest of run %d:\t"+function.getBest().toString(), i);
            function.resetBest();
            new WriteToCSV().WriteToCSV(function.getStats());
        }

        double average = sum / numberOfRunsToAverage;
        System.out.printf("\nAverage over %d runs: %.5f\n\n", numberOfRunsToAverage, average);
    }
}
