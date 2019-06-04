/**
 * Section 5.1.3.2 Page 85 Java class representing a Costs
 **/

// Class associated to the COSTS schema
package bookTrading.ontology;

import jade.content.Predicate;
import jade.core.AID;

public class Costs implements Predicate
{
	private Book item;
	private int price;
	
	public Book getItem()
	{
		return item;
	}
	
	public void setItem( Book item )
	{
		this.item = item;
	}
	
	public int getPrice()
	{
		return price;
	}
	
	public void setPrice( int price )
	{
		this.price = price;
	}
}
