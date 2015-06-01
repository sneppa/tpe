package de.hs_mannheim.ss15.tpe.group_2_4.uebung03.aufgabe02;

public interface Crypter {
	
	/**
	 * Encrypts a message with an encryption algorithm.
	 * @param message Message to be encrypted.
	 * @return Encrypted cypher.
	 */
	public String encrypt(String message);
	
	/**
	 * Decrypts a cypher.
	 * @param cypherText Given caesar cypher.
	 * @return Decrypted message.
	 */
	public String decrypt(String cypherText);
}
