package chromosomes;

/**
 *
 * @author rich
 */
public abstract class NewBaseChromosomeFactory {
    
    public abstract NewBaseChromosome createNew();

    public NewBaseChromosome createCopy(NewBaseChromosome c){
        
        NewBaseChromosome copy = createNew();
        copy.setGenes(c);
        copy.setFitness(c.fitness());
        return copy;
    }
}
