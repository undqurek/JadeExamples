import org.jgap.FitnessFunction;
import org.jgap.IChromosome;


public class FirstGoalFunction extends FitnessFunction
{
	private static final long serialVersionUID = 8239314224512152847L;
	
	@Override
	protected double evaluate( IChromosome chromosome )
	{
		double x = (double) chromosome.getGene(0).getAllele();

		return -(x - 5) * (x - 5) + 100;
	}
	
}
