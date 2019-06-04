package com.example;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class SimpleBehaviour extends TickerBehaviour
{
	private static final long serialVersionUID = 2053032307338336571L;
	
	private String text;
	private long left;
	
	public SimpleBehaviour(Agent agent, long period, long duration, String text)
	{
		super(agent, period);

		this.text = text;
		this.left = duration;
	}
	
	@Override
	protected void onTick()
	{
		if(this.left > 0L)
		{
			System.out.println(this.text);
			this.left -= super.getPeriod();
		}
		else
			super.stop();
	}
}
