package chromosomes;

/**
 *
 * @author rich
 */
public abstract class ChromosomeFactory {
    
    public abstract Chromosome createNew();

    public Chromosome createCopy(Chromosome c){
        
        Chromosome copy = createNew();
        copy.setGenes(c);
        copy.setFitness(c.fitness());
        return copy;
    }
}
