package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03;

public abstract class Car implements Comparable<Car>{

	public String brand;
	public int constructionYear;
	public int price;
	public int ID;
	
	/**
	 * Car Constructor
	 * @param brand Brand name.
	 * @param constructionYear Construction year.
	 * @param price Listed price.
	 */
	Car(String brand, int constructionYear, int price) {
		this.brand = brand;
		this.constructionYear = constructionYear;
		this.price = price;
	}
	
	/**
	 * Compares two cars first by price, if equal, by construction year and then by lexicographical order.
	 * @param o The second car to which to compare to.
	 * @return -1 if this car is first in order, 1 if 2nd car and 0 if they're exactly the same.
	 */
	public int compareTo(Car o) {
		if(this.price > o.price)
			return -1;
		if(this.price < o.price)
			return 1;
		if(this.constructionYear > o.constructionYear)
			return -1;
		if(this.constructionYear < o.constructionYear)
			return 1;
		return this.brand.compareTo(o.brand);
	}
	
	@Override
	public String toString() {
		return "Auto Nr." + ID + " - " + brand + ": Baujahr " + constructionYear + " - Preis: " + price + "€";
	}
	
	/**
	 * Actually does not compare to other objects
	 */
	//public int compareTo(Object o) { return 0; }
}
