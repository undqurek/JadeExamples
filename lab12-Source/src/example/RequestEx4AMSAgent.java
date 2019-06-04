package example;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.JADEAgentManagement.QueryPlatformLocationsAction;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.util.leap.List;

public class RequestEx4AMSAgent extends Agent
{
	private static final long serialVersionUID = 802866730321796530L;
	
	@Override
	protected void setup()
	{
		final QueryPlatformLocationsAction action = new QueryPlatformLocationsAction();
		final ACLMessage message = Utils.createMessage( this, action );
		
		this.addBehaviour( new TickerBehaviour( this, 20000 )
		{
			private static final long serialVersionUID = 9011480925217942509L;
			
			@Override
			protected void onTick()
			{
				Agent agent = this.getAgent();
				
				agent.addBehaviour( new AchieveREInitiator( agent, message )
				{
					private static final long serialVersionUID = -6710954613796466795L;
					
					protected void handleInform( ACLMessage message )
					{
						List containers = Utils.castResult( this.getAgent(), message );
						
						System.out.println( "Containers:" );
						
						for ( int i = 0; i < containers.size(); ++i )
						{
							ContainerID container = (ContainerID) containers.get( i );
							System.out.println( ( i + 1 ) + ". " + container.getName() );
						}
					}
					
					protected void handleFailure( ACLMessage failure )
					{
						System.out.println( "Operation error\n" + failure );
					}
				} );
			}
		} );
	}
}
