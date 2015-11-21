package chromosomes;

/**
 *
 * @author rich
 */
public abstract class BaseChromosomeFactory {
    
    public abstract BaseChromosome createNew();

    public BaseChromosome createCopy(BaseChromosome c){
        
        BaseChromosome copy = createNew();
        copy.setGenes(c);
        copy.setFitness(c.fitness());
        return copy;
    }
}
