package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03;

public interface Electric {
	public static final int HIGH_VOLTAGE = 600;
	public static final int LOW_VOLTAGE = 480;
	
	/**
	 * @return Should return either HIGH_VOLTAGE or LOW_VOLTAGE
	 */
	int getVoltage();
}
