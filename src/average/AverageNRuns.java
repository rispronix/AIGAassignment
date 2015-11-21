package average;

import GA.BaseFunction;
import java.util.ArrayList;
import stats.RunStatistics;

/**
 *
 * @author rich
 */
public class AverageNRuns {
    public AverageNRuns(int numberOfRunsToAverage, BaseFunction function) {
        ArrayList<ArrayList<RunStatistics>> runs = new ArrayList();
//        double sum = 0;
        for (int i = 0; i < numberOfRunsToAverage; i++) {
            function.run();
            runs.add(function.getStats());
//            sum += function.getBest().fitness();
            function.resetBest();
        }

//        System.out.println("Running GA");
//        
//        for (int i = 0; i < numberOfRunsToAverage; i++) {
//            System.out.println("best fitness of run " + (i + 1) + ":\t" + runs.get(i).get(runs.get(i).size() - 1).getBest().fitness());
//        }
//        double average = sum/numberOfRunsToAverage;
//        System.out.println("Average over " + numberOfRunsToAverage + " runs: " + average+"\n\n");
    }
}