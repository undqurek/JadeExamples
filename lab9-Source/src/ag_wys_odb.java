import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class ag_wys_odb extends Agent
{
	private static final long serialVersionUID = 745338207849369357L;
	
	@Override
	protected void setup()
	{
		this.addBehaviour( new CyclicBehaviour()
		{
			private static final long serialVersionUID = 1624811450303316637L;
			
			@Override
			public void action()
			{
				SequentialBehaviour behaviour = new SequentialBehaviour();
				
				behaviour.addSubBehaviour( new FirstBehaviour() );
				behaviour.addSubBehaviour( new SecondBehaviour() );
				
				this.getAgent().addBehaviour( behaviour );
			}
		} );
	}
	
	private class FirstBehaviour extends Behaviour
	{
		private static final long serialVersionUID = 3785543993420415937L;
		
		private final Random random = new Random();
		
		private final AID polskiId = new AID( "Polski", false );
		private final AID alaId = new AID( "Ala", false );
		
		@Override
		public void action()
		{
			ACLMessage message;
			
			if ( this.nextValue() )
			{
				message = new ACLMessage( ACLMessage.CFP );
				message.setContent( "Numer: 0" );
				
			}
			else
			{
				message = new ACLMessage( ACLMessage.REQUEST );
				message.setContent( "Numer: 1" );
			}
			
			message.addReceiver( this.polskiId );
			message.addReceiver( this.alaId );
			
			ag_wys_odb.this.send( message );
			
			// na potrzeby testow
			Utils.sleep( 5000 );
		}
		
		@Override
		public boolean done()
		{
			return true;
		}
		
		private boolean nextValue()
		{
			return random.nextBoolean();
		}
	}
	
	private class SecondBehaviour extends Behaviour
	{
		private static final long serialVersionUID = -2100881661906965465L;
		private boolean finished = false;
		
		@Override
		public void action()
		{
			ACLMessage response = this.getAgent().receive();
			
			if ( response != null )
			{
				System.out.println( this.getAgent().getName() + ": " + response.getContent() );
				
				switch ( response.getPerformative() )
				{
					case ACLMessage.INFORM:
						this.getAgent().doDelete();
						break;
					
					default:
						finished = true;
						break;
				}
			}
		}
		
		@Override
		public boolean done()
		{
			return this.finished;
		}
	}
}
