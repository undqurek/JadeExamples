package com.example;

import jade.core.Agent;

public class Klasa3 extends Agent
{
	private static final long serialVersionUID = -4255872458385613148L;
	
	private boolean sleep(long milliseconds)
	{
		try
		{
			Thread.sleep( milliseconds );
			
			return true;
		}
		catch ( InterruptedException e )
		{
			return false;
		}
	}
	
	@Override
	protected void setup()
	{
		System.out.println( "startuje" );
		
		for(int i = 0; i < 100; ++i)
		{
			System.out.println( "wykonujê");
			this.sleep( 1000 );
		}
		
		super.doDelete();
	}
	
	@Override
	protected void takeDown()
	{
		System.out.println( "zaraz sie usune" );
	}
}
