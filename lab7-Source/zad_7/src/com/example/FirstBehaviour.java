package com.example;


public class FirstBehaviour extends jade.core.behaviours.SimpleBehaviour
{
	private static final long serialVersionUID = 1707131088770713776L;
	
	@Override
	public void action()
	{
		System.out.println("pierwsze");
		this.getAgent().addBehaviour( new SecondBehaviour() );
	}
	
	@Override
	public boolean done()
	{
		return true;
	}
}
