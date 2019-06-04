import org.jgap.FitnessFunction;
import org.jgap.IChromosome;


public class SecondGoalFunction extends FitnessFunction
{
	private static final long serialVersionUID = 8239314224512152847L;
	
	@Override
	protected double evaluate( IChromosome chromosome )
	{
		double x = (double) chromosome.getGene(0).getAllele();
		double y = (double) chromosome.getGene(1).getAllele();
		
		double a = x * x - y;
		double b = x - 1;
		
		return 10000.0 - (100.0 * a * a + b * b);
	}
}
