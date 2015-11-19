package function1;

import java.util.Random;
import chromosome.BaseChromosome;
import chromosome.BaseChromosomeFactory;
import comparators.CompareMax;
import comparators.FitnessComparator;
import fitness.FitnessFunction;
import population.Population;
import population.PopulationFactory;
import recombination.Recombination;
import selection.TournamentSelection;
import util.DecimalFromBinary;

/**
 *
 * @author rich
 */
public class Function1 {

//    private final Random seed = new Random(System.currentTimeMillis());
    private final Random seed = new Random(1);//debug
    private final FitnessFunction ff;
    private final int geneQty = 8;// binary string to represent values 0 to 255
    private final BaseChromosomeFactory chromosomeFactory;
    private final Population population;
    private final int populationSize = 10;// arbitrary and modifiable 
    private final FitnessComparator comparator;// higher = better
    private final TournamentSelection s;
    private final Recombination r;
    private final double mutationProbability=0.5;

    public Function1() {
        
        /*
            maximise x^2
        */
        ff = (BaseChromosome c) -> (float) Math.pow(DecimalFromBinary.decimalFromBinary(c), 2);

        /*
            define factory to produce binary string chromosomes 
            
        */
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
        population = new Population(populationSize, chromosomeFactory);
        population.calculateAverageFitness();

        System.out.println(population.toString());
        comparator = new CompareMax();
//        System.out.println(comparator.compare(population.getElement(0), population.getElement(1)));

        PopulationFactory populationFactory = new PopulationFactory() {

            @Override
            public Population createNew() {
                return new Population(populationSize,chromosomeFactory);
            }

            @Override
            public Population createCopy(Population population) {
                Population newPopulation = new Population(populationSize);
                for (int i = 0; i < populationSize; i++) {
                    newPopulation.set(i, chromosomeFactory.createCopy(population.getElement(i)));
                    
                }
                return newPopulation;
            }
        };
        
        
        s = new TournamentSelection(seed, comparator,populationFactory);
//        population = s.select(population);  
        Population newPop = s.select(population);
        newPop.calculateAverageFitness();
        System.out.println(newPop.toString());
        
        r = new Recombination(seed, populationFactory, mutationProbability);
        Population anotherPop = r.singlepointCrossover(newPop);
        anotherPop.calculateAverageFitness();
        System.out.println(anotherPop.toString());
    }
}
