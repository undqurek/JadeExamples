package com.example;

import jade.core.Agent;

public class Klasa5 extends Agent
{
	private static final long serialVersionUID = -4255872458385613148L;

	@Override
	protected void setup()
	{ 
		System.out.println( "startuje" );
		this.addBehaviour(new ExtendedBehaviour());
	}
	
	@Override
	protected void takeDown()
	{
		System.out.println( "zaraz sie usune" );
	}
}
