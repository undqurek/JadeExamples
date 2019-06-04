package example;

import java.util.Random;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.JADEAgentManagement.QueryPlatformLocationsAction;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.util.leap.List;

public class RequestEx5AMSAgent extends Agent
{
	private static final long serialVersionUID = 802866730321796530L;
	
	@Override
	protected void setup()
	{
		final QueryPlatformLocationsAction action = new QueryPlatformLocationsAction();
		final ACLMessage message = Utils.createMessage( this, action );
		
		final Random randomizer = new Random();
		
		this.addBehaviour( new TickerBehaviour( this, 2000 )
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
						Agent agent = this.getAgent();
						List containers = Utils.castResult( agent, message );
						
						System.out.println( "Containers:" );
						
						for ( int i = 0; i < containers.size(); ++i )
						{
							ContainerID container = (ContainerID) containers.get( i );
							System.out.println( ( i + 1 ) + ". " + container.getName() );
						}
						
						int index = randomizer.nextInt( containers.size() );
						ContainerID container = (ContainerID) containers.get( index );
						
						agent.doSuspend();
						agent.doMove( container );
						agent.doActivate();
						
						System.out.println( "Moved to: " + container.getName() );
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
