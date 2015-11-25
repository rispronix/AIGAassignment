package fileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import stats.RunStatistics;

/**
 *
 * @author r2-rowley
 */
public class WriteToFile {

    public void WriteAverageToFile(List<RunStatistics> stats) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH-mm-ss.SSSS");
            File file = new File("result_data/average_" + stats.get(0).getTitle() + '_' + format.format(new Date(System.currentTimeMillis())) + ".xls");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append("#mutationProbability" + stats.get(0).getMutationProbability());
            writer.append("\n#recobminationProbability" + stats.get(0).getRecombinationProbability());
            writer.append("\n#populationSize" + stats.get(0).getPopulationSize());
            writer.append("\ngeneration\taverage\taverageStdDev\taverageVariance\tbest\tbestStdDev\tbestVariance\n");

            for (int i = 0; i < stats.size(); i++) {
                writer.append(String.valueOf(stats.get(i).getGeneration()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getAverage()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getAveragesd()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getAverageVar()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getAverageFitness()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getAverageVar()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getFitnesssd()));
                writer.append('\n');
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void WriteRunToFile(List<RunStatistics> stats) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH-mm-ss.SSSS");
            File file = new File("result_data/" + stats.get(0).getTitle() + '_' + format.format(new Date(System.currentTimeMillis())) + ".xls");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append("#mutationProbability" + stats.get(0).getMutationProbability());
            writer.append("\n#recobminationProbability" + stats.get(0).getRecombinationProbability());
            writer.append("\n#populationSize" + stats.get(0).getPopulationSize());
            writer.append("\ngeneration\taverage\tbest\n");

            for (int i = 0; i < stats.size(); i++) {
                writer.append(String.valueOf(stats.get(i).getGeneration()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getAverage()));
                writer.append('\t');
                writer.append(String.valueOf(stats.get(i).getBest().fitness()));
                writer.append('\n');
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
