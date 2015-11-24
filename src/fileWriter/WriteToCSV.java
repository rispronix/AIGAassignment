package fileWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
public class WriteToCSV {

    public void WriteToCSV(List<RunStatistics> stats) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
            File file= new File("result_data/GAResultFile_"+format.format(new Date(System.currentTimeMillis()))+".csv");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            FileOutputStream writer = new FileOutputStream(new File("result_data/GAResultFile_"+format.format(new Date(System.currentTimeMillis()))));
            writer.append("#mutationProbability" + stats.get(0).getMutationProbability());
            writer.append("\n#recobminationProbability" + stats.get(0).getRecombinationProbability());
            writer.append("\n#populationSize" + stats.get(0).getPopulationSize());
            writer.append("\ngeneration,average,best\n");

            for (int i = 0; i < stats.size(); i++) {
                writer.append(String.valueOf(stats.get(i).getGeneration()));
                writer.append(',');
                writer.append(String.valueOf(stats.get(i).getAverage()));
                writer.append(',');
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
