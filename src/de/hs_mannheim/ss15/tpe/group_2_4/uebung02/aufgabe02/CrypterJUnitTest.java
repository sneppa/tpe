package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe02;

import static org.junit.Assert.*;

import org.junit.Test;

public class CrypterJUnitTest {

	@Test
	public void caesarStandard() {
		Crypter caesar = new CaesarCrypter(3); //Standard encryption
		assertEquals("EDED DOOHU EDEDV!", caesar.encrypt("BABA ALLER BABAS!"));
		assertEquals("ÄÖÜ.-,", caesar.encrypt("ÄÖÜ.-,"));
		assertEquals("BABA ALLER BABAS!", caesar.decrypt("EDED DOOHU EDEDV!"));
	}
	
	@Test
	public void caesar0Key() {
		Crypter caesar = new CaesarCrypter(0); //Key = 0
		assertEquals("BABA ALLER BABAS!", caesar.encrypt("BABA ALLER BABAS!"));
		assertEquals("BABA ALLER BABAS!", caesar.decrypt("BABA ALLER BABAS!"));
	}
	
	@Test
	public void caesarOneChar() {
		Crypter caesar = new CaesarCrypter(5);
		assertEquals("F", caesar.encrypt("a")); //One char
	}
	
	@Test
	public void caesarZeroChar() {
		Crypter caesar = new CaesarCrypter(5);
		assertEquals("", caesar.encrypt(""));
		assertEquals("", caesar.decrypt(""));
	}
	
	@Test
	public void caesarRolloverDecryption() {
		Crypter caesar = new CaesarCrypter(5);
		assertEquals("Z", caesar.decrypt("e")); //Rollover
	}
	
	@Test
	public void caesarRolloverEncryption() {
		Crypter caesar = new CaesarCrypter(5);
		assertEquals("D", caesar.encrypt("y")); //Rollover
	}
	
	@Test
	public void reverseStandard() {
		Crypter reverse = new ReverseCrypter();
		assertEquals(".muyok anima ,ella treisaR", reverse.encrypt("Rasiert alle, amina koyum."));
	}
	
	@Test
	public void reverseOneChar() {
		Crypter reverse = new ReverseCrypter();
		assertEquals("A", reverse.encrypt("A"));
	}
	
	@Test
	public void reverseZeroChar() {
		Crypter reverse = new ReverseCrypter();
		assertEquals("", reverse.encrypt(""));
	}

}
