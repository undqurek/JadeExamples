import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class zlecajacy extends Agent
{
	private static final long serialVersionUID = 1534264922895104691L;
	
	private static DFAgentDescription agentDescription;
	private static Random randomizer = new Random();
	
	static
	{
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setType( "POTEGA" );
		
		agentDescription = new DFAgentDescription();
		agentDescription.addServices( serviceDescription );
	}
	
	@Override
	protected void setup()
	{
		this.addBehaviour( new TickerBehaviour( this, 20000 )
		{
			private static final long serialVersionUID = 3352854323650991854L;
			
			@Override
			protected void onTick()
			{
				AID agentId = zlecajacy.this.searchAgentId();
				
				if ( agentId == null )
					return;
				
				int number = zlecajacy.this.randomNumber();
				
				ACLMessage requestMessage = new ACLMessage( ACLMessage.REQUEST );
				requestMessage.addReceiver( agentId );
				requestMessage.setContent( Integer.toString( number ) );
				zlecajacy.this.send( requestMessage );
				
				ACLMessage responseMessage = zlecajacy.this.blockingReceive();
				
				if ( responseMessage == null )
					return;
				
				System.out.println( number + "*" + number + "=" + responseMessage.getContent() );
				
				zlecajacy.this.doDelete();
			}
		} );
	}
	
	private AID searchAgentId()
	{
		try
		{
			DFAgentDescription[] agentsDescriptions = DFService.search( this, agentDescription );
			
			if ( agentsDescriptions.length == 0 )
				return null;
			
			return agentsDescriptions[ 0 ].getName();
		}
		catch ( FIPAException e )
		{
			return null;
		}
	}
	
	private int randomNumber()
	{
		return randomizer.nextInt( 101 );
	}
}
