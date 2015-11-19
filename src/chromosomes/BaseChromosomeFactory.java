package chromosomes;

/**
 *
 * @author rich
 */
public abstract class BaseChromosomeFactory {

    public abstract BaseChromosome createNew();

    public BaseChromosome createCopy(BaseChromosome c) {
        BaseChromosome c1 = createNew();
        c1.setGenes(c);
        c1.setFitness(c.fitness());
        return c1;
    }
}
