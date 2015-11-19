/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import stats.RunStatistics;

/**
 *
 * @author r2-rowley
 */
public class WriteToCSV {

    public void WriteToCSV(List<RunStatistics> stats) {
        try {
            FileWriter writer = new FileWriter("GAResultFile");

            writer.append("generation,average,best\n");

            for (int i = 0; i < 10; i++) {
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
