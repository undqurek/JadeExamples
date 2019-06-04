package com.example;

import jade.core.behaviours.Behaviour;

public class ExtendedBehaviour extends Behaviour
{
	private static final long serialVersionUID = 1707131088770713776L;
	
	private int state = 0;
	
	@Override
	public void action()
	{
		switch ( this.state )
		{
			case 0:
				System.out.println("pierwszy krok");
				break;
			
			case 1:
				System.out.println("drugi krok");
				break;
				
			case 2:
				System.out.println("rzeci krok");
				break;
		}
		
		this.state += 1;
		Utils.sleep( 5000 );
	}
	
	@Override
	public boolean done()
	{
		return this.state == 3;
	}
	
}
