package chromosome;

import java.util.Arrays;

/**
 *
 * @author rich
 * @param <T>
 */
public abstract class BaseChromosome<T> {

    protected float fitness;
    protected T[] genes;

    public abstract BaseChromosome initialise();

    public abstract float calculateFitness();
    
    public void setGenes(BaseChromosome c) {
        for (int i = 0; i < c.size(); i++) {
            genes[i] = (T) c.getGene(i);
        }
    }

    public T getGene(int i) {
        return genes[i];
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
