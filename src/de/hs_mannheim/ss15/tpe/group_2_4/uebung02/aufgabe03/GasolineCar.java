package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03;

public class GasolineCar extends Car implements Gasoline{

	int emissionTier;
	
	public GasolineCar(int emissionTier, String brand, int constructionYear, int price) {
		super(brand, constructionYear, price);
		this.emissionTier = emissionTier;
	}

	@Override
	public int getEmissionTier() {
		return emissionTier;
	}

}
