package com.example;

import jade.core.Agent;

public class Klasa8 extends Agent
{
	private static final long serialVersionUID = -4255872458385613148L;
	
	@Override
	protected void setup()
	{
		// przed uruchomieniem programu uruchomic run-ap.bat
		
		System.out.println( "startuje" );
		
		this.addBehaviour(new SimpleBehaviour(this, 2000, 100000, "ma³y tick")
		{
			private static final long serialVersionUID = 3840609325420720783L;

			@Override
			public int onEnd()
			{
				Klasa8.this.doDelete();
				
				return super.onEnd();
			}
		});
		this.addBehaviour(new SimpleBehaviour(this, 5000, 50000, "du¿y tick"));	
		
		// Rozwiazanie nr 2: dodac trzecie zachowanie ktore usunie pozostale zachowania wraz z agentem
	}
	
	@Override
	protected void takeDown()
	{
		System.out.println( "zaraz sie usune" );
	}
}
