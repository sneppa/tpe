package test.de.hs_mannheim.ss15.tpe.group_2_4.uebung02;

import de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe03.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestCar {

	@Test
	public void EmissionTierCorrect() {
                GasolineCar car1 = new GasolineCar(5,"Land Rover",1996,10000);
                HybridCar car2 = new HybridCar(1,5000,"Tesla",1996,10000);
		assertEquals(5, car1.getEmissionTier());
		assertEquals(1, car2.getEmissionTier());
	}
        
	@Test
	public void VoltageCorrect() {
                HybridCar car1 = new HybridCar(1,5000,"Tesla",1996,10000);
		assertEquals(5000, car1.getVoltage());
	}
        
	@Test
	public void compareCorrect() {
                GasolineCar car1 = new GasolineCar(5,"Land Rover",1996,10000);
                GasolineCar car2 = new GasolineCar(5,"Land Rover",1996,10000);
		assertEquals(0, car1.compareTo(car2));
	}
        
	@Test
	public void compareFalseBrand() {
                GasolineCar car1 = new GasolineCar(5,"Land Rover",1996,10000);
                GasolineCar car2 = new GasolineCar(5,"BMW",1996,10000);
                assertNotSame(0, car1.compareTo(car2));
	}
        
	@Test
	public void compareFalsePrice() {
                GasolineCar car1 = new GasolineCar(5,"Land Rover",1996,10000);
                GasolineCar car2 = new GasolineCar(5,"Land Rover",1996,11000);
                assertNotSame(0, car1.compareTo(car2));
	}
        
	@Test
	public void compareFalseYear() {
                GasolineCar car1 = new GasolineCar(5,"Land Rover",1996,10000);
                GasolineCar car2 = new GasolineCar(5,"Land Rover",1997,10000);
                assertNotSame(0, car1.compareTo(car2));
	}

}
