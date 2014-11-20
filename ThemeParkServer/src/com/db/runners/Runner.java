package com.db.runners;

public abstract class Runner implements Runnable{
	
	// If the thread is running
	private boolean running;
	
	// Called every tick of the run method
	public abstract void act();
	
	public Runner(){
		running = true;
	}
	
	/**
	 * stop stops the thread
	 */
	public void stop(){
		running = false;
	}

	@Override
	public void run() {
		while(running){
			act();
		}
	}
}
