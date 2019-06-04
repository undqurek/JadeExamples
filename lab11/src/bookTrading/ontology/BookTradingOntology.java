/**
 * Section 5.1.3.1 Page 82 Ontology of the Book Trading Example.
 **/
package bookTrading.ontology;

import jade.content.onto.*;
import jade.content.schema.*;

public class BookTradingOntology extends Ontology implements BookTradingVocabulary
{
	// The name identifying this ontology
	public static final String ONTOLOGY_NAME = "Book-trading-ontology";
	
	/**
	 * As explained in section 5.1.3.6 pag. 90, it is suggested to move all the following constants,
	 * which represent the Vocabulary of the ontology, into the BookTradingVocabulary interface //
	 * VOCABULARY public static final String BOOK = "Book"; public static final String BOOK_TITLE =
	 * "title"; public static final String BOOK_AUTHORS = "authors"; public static final String
	 * BOOK_EDITOR = "editor"; public static final String COSTS = "Costs"; public static final
	 * String COSTS_ITEM = "item"; public static final String COSTS_PRICE = "price"; public static
	 * final String SELL = "Sell"; public static final String SELL_ITEM = "item";
	 **/
	
	// The singleton instance of this ontology
	private static Ontology theInstance = new BookTradingOntology();
	
	// Retrieve the singleton Book-trading ontology instance
	public static Ontology getInstance()
	{
		return theInstance;
	}
	
	// Private constructor
	private BookTradingOntology()
	{
		// The Book-trading ontology extends the basic ontology
		super( ONTOLOGY_NAME, BasicOntology.getInstance() );
		try
		{
			add( new ConceptSchema( BOOK ), Book.class );
			add( new PredicateSchema( COSTS ), Costs.class );
			add( new AgentActionSchema( SELL ), Sell.class );
			
			// Structure of the schema for the Book concept
			ConceptSchema cs = (ConceptSchema) getSchema( BOOK );
			cs.add( BOOK_TITLE, (PrimitiveSchema) getSchema( BasicOntology.STRING ) );
			cs.add( BOOK_AUTHORS, (PrimitiveSchema) getSchema( BasicOntology.STRING ), 0, ObjectSchema.UNLIMITED );
			cs.add( BOOK_EDITOR, (PrimitiveSchema) getSchema( BasicOntology.STRING ), ObjectSchema.OPTIONAL );
			
			// Structure of the schema for the Costs predicate
			PredicateSchema ps = (PredicateSchema) getSchema( COSTS );
			ps.add( COSTS_ITEM, (ConceptSchema) cs );
			ps.add( COSTS_PRICE, (PrimitiveSchema) getSchema( BasicOntology.INTEGER ) );
			
			// Structure of the schema for the Sell agent action
			AgentActionSchema as = (AgentActionSchema) getSchema( SELL );
			as.add( SELL_ITEM, (ConceptSchema) getSchema( BOOK ) );
		}
		catch ( OntologyException oe )
		{
			oe.printStackTrace();
		}
	}
}
