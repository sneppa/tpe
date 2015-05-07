package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03;

public interface Electric {
	static final int HIGH_VOLTAGE = 600;
	static final int LOW_VOLTAGE = 480;
	
	/**
	 * @return Shoud return either HIGH_VOLTAGE or LOW_VOLTAGE
	 */
	int getVoltage();
}
