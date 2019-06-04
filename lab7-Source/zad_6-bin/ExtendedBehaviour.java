package com.example;

import java.util.Scanner;

import jade.core.behaviours.Behaviour;

public class ExtendedBehaviour extends Behaviour
{
	private static final long serialVersionUID = 1707131088770713776L;
	
	private Scanner scanner = new Scanner( System.in );
	private boolean finished = false;
	
	@Override
	public void action()
	{
		System.out.print( "Podaj liczbe (ujemna liczba przeywa dzialanie): " );
		int number = this.scanner.nextInt();
		this.scanner.nextLine();
		
		if ( number < 0 )
			this.finished = true;
	}
	
	@Override
	public boolean done()
	{
		return this.finished;
	}
	
	@Override
	public void onStart()
	{
		System.out.println( "zachowanie startuje" );
	}
	
	@Override
	public int onEnd()
	{
		System.out.println( "achowanie zakoñczone" );
		
		return super.onEnd();
	}
}
