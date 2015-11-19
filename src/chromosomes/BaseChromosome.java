package chromosomes;

import java.util.Arrays;

/**
 *
 * @author rich
 * @param <T>
 */
public abstract class BaseChromosome<T> {

    protected T[] genes;
    protected float fitness;

    public abstract BaseChromosome initialise();

    public abstract float calculateFitness();

    public void setGenes(BaseChromosome c) {
        for (int i = 0; i < c.size(); i++) {
            genes[i] = (T) c.getGene(i);
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

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public float fitness() {
        return fitness;
    }

    public int size() {
        return genes.length;
    }

    @Override
    public String toString() {
        return "BaseChromosome{" + "fitness=" + fitness + ", genes=" + Arrays.toString(genes) + '}';
    }

}
