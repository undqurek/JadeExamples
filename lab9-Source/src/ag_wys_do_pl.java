import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ag_wys_do_pl extends Agent
{
	private static final long serialVersionUID = 745338207849369357L;
	
	@Override
	protected void setup()
	{
		this.addBehaviour( new CyclicBehaviour()
		{
			private static final long serialVersionUID = 1624811450303316637L;
			
			private final AID polskiId = new AID( "Polski", false );
			
			@Override
			public void action()
			{
				ACLMessage message;
				
				message = new ACLMessage( ACLMessage.CFP );
				message.addReceiver( this.polskiId );
				message.setLanguage( "pl_PL" );
				message.setContent( "komunikat..." );
				
				ag_wys_do_pl.this.send( message );
				
				// na potrzeby testow
				Utils.sleep( 1000 );
			}
		} );
	}
}
