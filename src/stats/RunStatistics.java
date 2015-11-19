/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stats;

import chromosomes.BaseChromosome;

/**
 *
 * @author r2-rowley
 */
public class RunStatistics {

    private final int generation;
    private final double average;
    private final BaseChromosome best;

    public RunStatistics(int generation, double average, BaseChromosome best) {
        this.generation = generation;
        this.average = average;
        this.best = best;
    }

    public BaseChromosome getBest() {
        return best;
    }

    public int getGeneration() {
        return generation;
    }

    public double getAverage() {
        return average;
    }
}
