import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DoubleGene;
import jade.core.Agent;

public class ExampleAgent extends Agent
{
	private static final long serialVersionUID = 4474336920801651148L;
	
	@Override
	protected void setup()
	{
		{
			FirstGoalFunction function = new FirstGoalFunction(); 
			Range[] ranges = new Range[]
			{
				new Range( -2.0, 12.0 )
			};

			IChromosome bestSolution = this.optimize( function, ranges );
			
			this.printSolution( bestSolution );
			
			double result = function.evaluate( bestSolution );
			System.out.println( "function(arguments[]): " + result );
		}
		
		{
			SecondGoalFunction function = new SecondGoalFunction(); 
			Range[] ranges = new Range[]
			{
				new Range( -2.0, 2.0 ),
				new Range( -2.0, 2.0 )
			};

			IChromosome bestSolution = this.optimize( function, ranges );
			
			this.printSolution( bestSolution );
			
			double result = function.evaluate( bestSolution );
			System.out.println( "function(arguments[]): " + result );
		}
	}
	
	private IChromosome optimize( FitnessFunction function, Range[] ranges )
	{
		Configuration.reset();
		
		Configuration conf = new DefaultConfiguration();
		Gene[] genes = new Gene[ranges.length];
		
		try
		{
			conf.setFitnessFunction( function );
			
			for ( int i = 0; i < ranges.length; ++i )
				genes[ i ] = new DoubleGene( conf, ranges[ i ].getMin(), ranges[ i ].getMax() );
			
			Chromosome chromosome = new Chromosome( conf, genes );
			
			conf.setSampleChromosome( chromosome );
			conf.setPopulationSize( 500 );
			
			Genotype population = Genotype.randomInitialGenotype( conf );
			
			for ( int i = 0; i < 1000; ++i )
				population.evolve();

			return population.getFittestChromosome();
		}
		catch ( InvalidConfigurationException e )
		{
			return null;
		}
	}
	
	private void printSolution(IChromosome solution)
	{
		System.out.println( "The best solution contained the following: " );
		
		Gene[] genes = solution.getGenes();
		
		for ( int i = 0; i < genes.length; ++i )
		{
			Gene gene = solution.getGene( i );
			double value = (double) gene.getAllele();
			
			System.out.println( "argument " + ( i + 1 ) + ": " + value );
		}
	}
	
	private class Range
	{
		private double min;
		private double max;
		
		public Range( double min, double max )
		{
			this.min = min;
			this.max = max;
		}
		
		public double getMin()
		{
			return this.min;
		}
		
		public double getMax()
		{
			return this.max;
		}
	}
}
