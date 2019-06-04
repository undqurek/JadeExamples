import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ag_pl extends Agent
{
	private static final long serialVersionUID = 7545094641162150542L;
	
	@Override
	protected void setup()
	{
		final MessageTemplate template = MessageTemplate.MatchLanguage( "pl_PL" );
		
		this.addBehaviour( new CyclicBehaviour()
		{
			private static final long serialVersionUID = 9087632966272207251L;
			
			@Override
			public void action()
			{
				ACLMessage message = this.myAgent.receive( template );
				
				if ( message != null )
					System.out.println( this.getAgent().getName() + ": " + message.getContent() );
			}
		} );
	}
}
