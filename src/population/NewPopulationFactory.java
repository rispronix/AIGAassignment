package population;

/**
 *
 * @author rich
 */
public interface NewPopulationFactory {
    
    NewPopulation createNew();

    NewPopulation createCopy(NewPopulation population);
}
