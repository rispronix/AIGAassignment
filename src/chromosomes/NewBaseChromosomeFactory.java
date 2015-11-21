package chromosomes;

/**
 *
 * @author rich
 */
public abstract class NewBaseChromosomeFactory {
    
    public abstract BaseChromosome createNew();

    public abstract BaseChromosome createCopy(BaseChromosome c);
}
