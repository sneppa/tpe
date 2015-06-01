package de.mami.uebung02.aufgabe03;

public class HybridCar extends Car implements Gasoline, Electric{

	int emissionTier;
	int voltage;
	
	HybridCar(int emissionTier, int voltage, String brand, int constructionYear, int price) {
		super(brand, constructionYear, price);
		this.emissionTier = emissionTier;
		this.voltage = voltage;
	}

	@Override
	public int getVoltage() {
		return voltage;
	}

	@Override
	public int getEmissionTier() {
		return emissionTier;
	}

}
