package com.example;

import jade.core.Agent;

public class Klasa1 extends Agent
{
	private static final long serialVersionUID = -4255872458385613148L;
	
	@Override
	protected void setup()
	{
		// przed uruchomieniem programu uruchomic run-ap.bat
		
		System.out.println( "startuje" );
	}
	
	@Override
	protected void takeDown()
	{
		System.out.println( "zaraz sie usune" );
	}
}
