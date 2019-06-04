package example;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.JADEAgentManagement.ShutdownPlatform;

public class RequestEx2AMSAgent extends Agent
{
	private static final long serialVersionUID = 802866730321796530L;
	
	@Override
	protected void setup()
	{
		final ShutdownPlatform action = new ShutdownPlatform();
		
		this.addBehaviour( new WakerBehaviour( this, 3000 )
		{
			private static final long serialVersionUID = -3548800085126990237L;
			
			@Override
			protected void onWake()
			{
				Utils.sendMessage( this.getAgent(), action, "Platform shouted down", "Error shutting platform" );
			}
		} );
	}
}
