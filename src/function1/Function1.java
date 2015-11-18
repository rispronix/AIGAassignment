package function1;

import java.util.Random;
import chromosome.BaseChromosome;
import chromosome.BaseChromosomeFactory;
import comparators.CompareMax;
import comparators.FitnessComparator;
import fitness.FitnessFunction;
import population.Population;
import selection.TournamentSelection;
import util.DecimalFromBinary;

/**
 *
 * @author rich
 */
public class Function1 {

//    private final Random seed = new Random(System.currentTimeMillis());
    private final Random seed = new Random(1);
    private final FitnessFunction ff;
    private final int geneQty = 8;
    private final BaseChromosomeFactory chromosomeFactory;
    private final Population population;
    private final int populationQty = 10;
    private final FitnessComparator comparator;
    private final TournamentSelection s;

    public Function1() {
        ff = (BaseChromosome c) -> (float) Math.pow(DecimalFromBinary.decimalFromBinary(c), 2);

        chromosomeFactory = new BaseChromosomeFactory() {

            @Override
            public BaseChromosome createNew() {
                return new BaseChromosome() {

                    @Override
                    public BaseChromosome initialise() {
                        genes = new Integer[geneQty];
                        for (int i = 0; i < geneQty; i++) {
                            genes[i] = seed.nextInt(2);
                        }
                        return this;
                    }

                    @Override
                    public float calculateFitness() {
                        return fitness = ff.calculate(this);
                    }
                }.initialise();
            }
        };

//        BaseChromosome chromosome1 = chromosomeFactory.createNew();
//        chromosome1.calculateFitness();
//        System.out.println(chromosome1.toString());
        population = new Population(populationQty, chromosomeFactory);
        population.calculateAverageFitness();

        System.out.println(population.toString());
        comparator = new CompareMax();
//        System.out.println(comparator.compare(population.getElement(0), population.getElement(1)));

        s = new TournamentSelection(seed, comparator);
        Population newPopulation = s.select(population);
        population.calculateAverageFitness();
        System.out.println(population.toString());
    }
}
