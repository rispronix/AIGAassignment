package population;

/**
 *
 * @author rich
 */
public interface PopulationFactory {

    Population createNew();

    Population createCopy(Population population);
}
