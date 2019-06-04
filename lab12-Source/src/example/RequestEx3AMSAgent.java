package example;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.JADEAgentManagement.KillAgent;

public class RequestEx3AMSAgent extends Agent
{
	private static final long serialVersionUID = 802866730321796530L;
	
	@Override
	protected void setup()
	{
		final KillAgent action = new KillAgent();
		action.setAgent( new AID( "Ala", AID.ISLOCALNAME ) );
		
		this.addBehaviour( new WakerBehaviour( this, 3000 )
		{
			private static final long serialVersionUID = -3548800085126990237L;
			
			@Override
			protected void onWake()
			{
				Utils.sendMessage( this.getAgent(), action, "Agent Ala killed", "Error killing Ala agent" );
			}
		} );
	}
}
