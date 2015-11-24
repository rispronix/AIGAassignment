package average;

import fileWriter.WriteToFile;
import ga.GA;
import java.util.ArrayList;
import stats.RunStatistics;

/**
 *
 * @author rich
 */
public class AverageNRuns {

    ArrayList<ArrayList<RunStatistics>> runs;
    int numberOfRunsToAverage;

    public AverageNRuns(int numberOfRunsToAverage, GA function) {
        this.numberOfRunsToAverage = numberOfRunsToAverage;
        System.out.print("\nAveraging GA " + numberOfRunsToAverage + " times.");
        System.out.print('\n' + function.toString());
        runs = new ArrayList<>();
        double sum = 0;
        for (int i = 1; i <= numberOfRunsToAverage; i++) {
            function.run();
            runs.add(function.getStats());
            sum += function.getBest().fitness();
            System.out.printf("\nBest of run %d:\t" + function.getBest().toString(), i);
            function.resetBest();
//            new WriteToCSV().WriteToCSV(function.getStats());
        }

        double average = sum / numberOfRunsToAverage;
        System.out.printf("\nAverage over %d runs: %.5f\n\n", numberOfRunsToAverage, average);
        calculateAverageOfNRuns();
    }

    double fitnessSum = 0;
    double fitnessAverage = 0;
    ArrayList<RunStatistics> averageRuns;

    public void calculateAverageOfNRuns() {

        averageRuns = new ArrayList<>();
        for (int i = 0; i < runs.get(0).size(); i++) {
            fitnessSum = 0;
            fitnessAverage = 0;
            for (int j = 0; j < runs.size(); j++) {
                fitnessSum += runs.get(j).get(i).getBest().fitness();
                fitnessAverage += runs.get(j).get(i).getAverage();
            }
            averageRuns.add(new RunStatistics(i, fitnessAverage / numberOfRunsToAverage, fitnessSum / numberOfRunsToAverage, runs.get(0).get(0).getMutationProbability(), runs.get(0).get(0).getRecombinationProbability(), runs.get(0).get(0).getPopulationSize(), runs.get(0).get(0).getTitle()));
        }
        new WriteToFile().WriteAverageToFile(averageRuns);
    }
}
