package chromosomes;

import java.util.Arrays;

/**
 *
 * @author rich
 * @param <T>
 */
public abstract class Chromosome <T>{

    protected T[] genes;
    protected int size;
    protected double fitness;

    public abstract Chromosome initialise();

    public abstract double evaluate();

    public Chromosome(int size) {
        this.size = size;
    }

    public void setGenes(Chromosome c) {
        T[] copy = (T[]) c.genes.clone();
        for (int i = 0; i < size; i++) {
            genes[i] = (T) copy[i];
        }
    }

    public void setGene(int index, T value) {
        genes[index] = value;
    }

    public T getGene(int i) {
        return genes[i];
    }

    public T[] getGenes(int start, int end) {
        return Arrays.copyOfRange(genes, start, end);
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double fitness() {
        return fitness;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "Chromosome{" + String.format("fitness=%.5f", fitness) + 
                ",\tgenes=" + Arrays.toString(genes) + '}';
    }
}
