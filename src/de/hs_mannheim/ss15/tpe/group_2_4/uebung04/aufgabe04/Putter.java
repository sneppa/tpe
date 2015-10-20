package de.hs_mannheim.ss15.tpe.group_2_4.uebung04.aufgabe04;

public class Putter extends Thread{
	
	int interval;
	double randomness;
	Ringpuffer parent;
	int count;
	
	
	Putter(Ringpuffer parent, int interval, double randomness) {
		this.interval = interval;
		this.parent = parent;
		count = 0;
		if(0 <= randomness && randomness <= 1)
			this.randomness = randomness;
	}
	
	public void run() {
		try {
			sleep((int)(interval*randomness + interval*(1-randomness)*Math.random()));
		} catch(InterruptedException e) {
			System.out.println("Interrupted.");
		}
		System.out.println(getName() + " put " + count++);
		run();
	}


}
