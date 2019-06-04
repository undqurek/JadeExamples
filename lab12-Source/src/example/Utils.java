package example;

import jade.content.AgentAction;
import jade.content.ContentManager;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import jade.util.leap.List;

public final class Utils
{
	private static Ontology ontology = JADEManagementOntology.getInstance();
	
	public static ACLMessage createMessage( Agent sender, AgentAction agentAction )
	{
		ContentManager manager = sender.getContentManager();
		
		manager.registerLanguage( new SLCodec( 0 ) );
		manager.registerOntology( ontology );
		
		AID identifier = sender.getAMS();
		ACLMessage message = new ACLMessage( ACLMessage.REQUEST );
		
		message.addReceiver( identifier );
		message.setOntology( ontology.getName() );
		message.setLanguage( FIPANames.ContentLanguage.FIPA_SL0 );
		message.setProtocol( FIPANames.InteractionProtocol.FIPA_REQUEST );
		
		Action action = new Action( identifier, agentAction );
		
		try
		{
			manager.fillContent( message, action );
		}
		catch ( CodecException | OntologyException e )
		{
			return null;
		}
		
		return message;
	}
	
	public static void sendMessage( Agent sender, AgentAction agentAction, final String sucessMessage, final String failMessage )
	{
		ACLMessage message = createMessage( sender, agentAction );
		
		if ( message == null )
			return;
		
		sender.addBehaviour( new AchieveREInitiator( sender, message )
		{
			private static final long serialVersionUID = 4185300293148398762L;
			
			protected void handleInform( ACLMessage inform )
			{
				System.out.println( sucessMessage );
			}
			
			protected void handleFailure( ACLMessage failure )
			{
				System.out.println( failMessage + "\n" + failure );
			}
		} );
	}
	
	public static List castResult(Agent agent, ACLMessage message )
	{
		ContentManager manager = agent.getContentManager();
		
		Result result;
		
		try
		{
			result = (Result) manager.extractContent( message );
		}
		catch ( CodecException | OntologyException e )
		{
			return null;
		}
		
		return (List) result.getValue();
	}
}
