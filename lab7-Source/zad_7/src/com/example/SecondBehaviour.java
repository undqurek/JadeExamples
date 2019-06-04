package com.example;

import jade.core.behaviours.Behaviour;

public class SecondBehaviour extends Behaviour
{
	private static final long serialVersionUID = 2053032307338336571L;
	
	@Override
	public void action()
	{
		System.out.println("drugie");
	}
	
	@Override
	public boolean done()
	{
		return true;
	}
	
}
