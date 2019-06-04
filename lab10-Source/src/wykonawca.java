import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class wykonawca extends Agent
{
	private static final long serialVersionUID = 3085833977976892012L;
	
	@Override
	protected void setup()
	{
		if ( this.register() )
		{
			final MessageTemplate messageTemplate = MessageTemplate.MatchPerformative( ACLMessage.REQUEST );
			
			this.addBehaviour( new CyclicBehaviour()
			{
				private static final long serialVersionUID = 39265731619681008L;
				
				private Integer parseNumber( String text )
				{
					try
					{
						return Integer.parseInt( text );
					}
					catch ( NumberFormatException ex )
					{
						return null;
					}
				}
				
				@Override
				public void action()
				{
					//this.block();
					
					ACLMessage requestMessage = wykonawca.this.blockingReceive( messageTemplate );
					
					if ( requestMessage == null )
						return;
					
					String content = requestMessage.getContent();
					
					if ( content == null )
						return;
					
					Integer number = this.parseNumber( content );
					
					if ( number == null )
						return;
					
					int result = number * number;
					
					ACLMessage responseMessage = requestMessage.createReply();
					responseMessage.setPerformative( ACLMessage.INFORM );
					responseMessage.setContent( Integer.toString( result ) );
					wykonawca.this.send( responseMessage );
				}
			} );
		}
		else
			this.doDelete();
	}
	
	@Override
	protected void takeDown()
	{
		this.unregister();
	}
	
	private boolean register()
	{
		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName( this.getAID() );
		agentDescription.addLanguages( "pl_PL" );
		
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setName( "wykonawca" );
		serviceDescription.setType( "POTEGA" );
		
		agentDescription.addServices( serviceDescription );
		
		try
		{
			DFService.register( this, agentDescription );
			
			return true;
		}
		catch ( FIPAException e )
		{
			return false;
		}
	}
	
	private boolean unregister()
	{
		try
		{
			DFService.deregister( this );
			
			return true;
		}
		catch ( FIPAException e )
		{	
			return false;
		}
	}
}
