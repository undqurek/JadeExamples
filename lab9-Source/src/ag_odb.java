import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ag_odb extends Agent
{
	private static final long serialVersionUID = -3906632833568114763L;
	
	@Override
	protected void setup()
	{
		this.addBehaviour( new CyclicBehaviour()
		{
			private static final long serialVersionUID = 6140600130291451092L;
			
			@Override
			public void action()
			{
				ACLMessage requestMessage = ag_odb.this.receive();
				
				if ( requestMessage != null )
				{	
					ACLMessage responseMessage = requestMessage.createReply();
					
					switch(requestMessage.getPerformative())
					{
						case ACLMessage.REQUEST:
							System.out.println(this.getAgent().getName() + ": " + requestMessage.getContent());
							
							responseMessage.setPerformative( ACLMessage.INFORM );
							responseMessage.setContent( "wykona³em" );

							break;
							
						case ACLMessage.CFP:
							System.out.println(this.getAgent().getName() + ": " + requestMessage.getContent());
							
							responseMessage.setPerformative( ACLMessage.REQUEST );
							responseMessage.setContent( "raz jeszcze" );

							break;
							
						default:
							responseMessage.setPerformative( ACLMessage.NOT_UNDERSTOOD );
							responseMessage.setContent( "???" );
							
							break;
					}
					
					ag_odb.this.send( responseMessage );
				}
			}
		} );
	}
}
