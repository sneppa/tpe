package de.hs_mannheim.ss15.tpe.group_2_4.uebung04.aufgabe04;

import static gdi.MakeItSimple.*;

public class Ringpuffer {
	
	volatile Integer[] contents;
	int  back = 0
		,front = 0;
	
	public static void main(String[] args) {
		System.out.println("Bitte geben sie die Laufdauer in Minuten an: ");
		int i = readInt();
		readLine();
		System.out.println("Bitte geben sie die Größe des Puffers an: ");
		int j = readInt();
		new Ringpuffer(j, i);
	}
	
	Ringpuffer(int size, int time) {
		contents = new Integer[size];
		Thread[] threads = new Thread[5];
		threads[0] = new Putter(this, 1000, 0.7);
		threads[0].start();
		threads[1] = new Putter(this, 2000, 0.8);
		threads[1].start();
		threads[2] = new Putter(this, 1500, 0.2);
		threads[2].start();
		threads[3] = new Getter(this, 1000, 0.7);
		threads[3].start();
		threads[4] = new Getter(this, 1500, 0.2);
		threads[4].start();
		
		try{
			Thread.sleep(time*60000);
		} catch(InterruptedException e) {
			System.out.println("Interrupted.");
		}
		for(Thread thread : threads) {
			thread.stop();
		}
		System.out.println("End.");
		System.out.print("[");
		for(Integer i : contents)
			System.out.print(i + " ");
		System.out.println("]");
	}
	
	synchronized void put(Integer i) {
		if(back == front && contents[front] != null) { // Array full
			try{
				wait();
			} catch(InterruptedException e) {
				put(i);
			}
		}
		contents[front] = i;
		front = (front + 1) % contents.length;
		notifyAll(); // Notify all other threads as array changed
	}
	
	synchronized Integer get() {
		if(back == front && contents[back] == null){ // Array empty
			try{
				wait();
			} catch(InterruptedException e) {
				get();
			}
		}
		Integer val = contents[back];
		contents[back] = null;
		back = (back +1) % contents.length;
		notifyAll();
		return val;
	}
	
	

}
