package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03;

public abstract class Car implements Comparable{

	public String brand;
	public int constructionYear;
	public int price;
	
	Car(String brand, int constructionYear, int price) {
		this.brand = brand;
		this.constructionYear = constructionYear;
		this.price = price;
	}
	
	public int compareTo(Car o) {
		if(this.price > o.price)
			return -1;
		if(this.price < o.price)
			return 1;
		return 0;
	}
	
	public int compareTo(Object o) { return 0; }
}
