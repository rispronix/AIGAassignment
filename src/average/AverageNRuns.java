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
//            new WriteToFile().WriteRunToFile(function.getStats());
        }

        double average = sum / numberOfRunsToAverage;
        System.out.printf("\nAverage over %d runs: %.5f\n\n", numberOfRunsToAverage, average);
        calculateAverageOfNRuns();
    }

    double fitnessSum = 0, fitnessAverage = 0, fitnesssd = 0, averagesd = 0,fitnessVar=0,averageVar=0;
    ArrayList<RunStatistics> averageRuns;

    public void calculateAverageOfNRuns() {

        averageRuns = new ArrayList<>();
        for (int i = 0; i < runs.get(0).size(); i++) {
            fitnessSum = fitnesssd = fitnessAverage = averagesd = fitnessVar=averageVar=0;
            for (int j = 0; j < runs.size(); j++) {
                fitnessSum += runs.get(j).get(i).getBest().fitness();
                fitnessAverage += runs.get(j).get(i).getAverage();
            }
            fitnessSum = fitnessSum / numberOfRunsToAverage;
            fitnessAverage = fitnessAverage / numberOfRunsToAverage;

            for (int j = 0; j < runs.size(); j++) {
                averagesd = Math.pow(runs.get(j).get(i).getAverage() - fitnessAverage, 2);
                fitnesssd = Math.pow(runs.get(j).get(i).getBest().fitness() - fitnessSum, 2);
            }
            averagesd = Math.sqrt(averagesd / numberOfRunsToAverage);
            fitnesssd = Math.sqrt(fitnesssd / numberOfRunsToAverage);
            fitnessVar = fitnesssd*fitnesssd;
            averageVar=averagesd*averagesd;
            averageRuns.add(new RunStatistics(i, fitnessAverage, fitnessSum, averagesd,fitnesssd,fitnessVar,averageVar,runs.get(0).get(0).getMutationProbability(), runs.get(0).get(0).getRecombinationProbability(), runs.get(0).get(0).getPopulationSize(), runs.get(0).get(0).getTitle()));
        }
        new WriteToFile().WriteAverageToFile(averageRuns);
    }
}
