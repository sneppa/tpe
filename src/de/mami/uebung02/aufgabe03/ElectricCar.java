package de.mami.uebung02.aufgabe03;

public class ElectricCar extends Car implements Electric {

	int voltage;
	
	ElectricCar(int voltage, String brand, int constructionYear, int price) {
		super(brand, constructionYear, price);
		this.voltage = voltage;
	}

	@Override
	public int getVoltage() {
		return voltage;
	}

}
