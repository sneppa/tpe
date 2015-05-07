package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03;

//BTree libraries
import de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe01.*;

public class CarManagement {

	static int carID = 1;
	static BTree carTree = new BTreeClass(1);
	
	public static void main(String[] args) {
		
		//GasolineCar(int emissionTier, String brand, int constructionYear, int price)
		//ElectricCar(int voltage, String brand, int constructionYear, int price) 
		//HybridCar(int emissionTier, int voltage, String brand, int constructionYear, int price)
		addCar(new GasolineCar(2, "BMW", 2012, 25000));
		addCar(new GasolineCar(3, "Audi", 2011, 22000));
		addCar(new ElectricCar(Electric.HIGH_VOLTAGE, "Tesla", 2015, 50000));
		addCar(new HybridCar(1, Electric.LOW_VOLTAGE, "VW", 2014, 30000));
		carTree.printInorder();
	}
	
	/**
	 * Adds a car to the carTree and gives it a unique ID.
	 * @param car Car to be added to the tree.
	 */
	public static void addCar(Car car) {
		car.ID = carID;
		carTree.insert(car);
		carID++;
	}
}
