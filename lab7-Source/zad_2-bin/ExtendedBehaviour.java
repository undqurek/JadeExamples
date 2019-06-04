package com.example;

import jade.core.behaviours.Behaviour;

public class ExtendedBehaviour extends Behaviour
{
	private static final long serialVersionUID = 1707131088770713776L;

	@Override
	public void action()
	{
		System.out.println("wykonuje");
	}
	
	@Override
	public boolean done()
	{
		return true;
	}
	
}
