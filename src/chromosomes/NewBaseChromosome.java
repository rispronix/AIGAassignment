package chromosomes;

import java.util.Arrays;

/**
 *
 * @author rich
 * @param <T>
 */
public abstract class NewBaseChromosome <T> {

    protected T[] genes;
    protected int size;
    protected float fitness;

    public abstract NewBaseChromosome initialise();

    public abstract float evaluate();

    public NewBaseChromosome(int size) {
        this.size = size;
    }

//    public NewBaseChromosome(NewBaseChromosome source) {
//        size = source.size();
//        T[] copy = (T[]) source.genes.clone();
//        for (int i = 0; i < size; i++) {
//            genes[i] = (T) copy[i];
//        }
//    }

    public void setGenes(NewBaseChromosome c) {
        T[] copy = (T[]) c.genes.clone();
        for (int i = 0; i < c.size(); i++) {
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

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public float fitness() {
        return fitness;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "NewBaseChromosome{" + "fitness=" + fitness + ", genes=" + Arrays.toString(genes) + '}';
    }
}
