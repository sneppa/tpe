package de.mami.uebung02.aufgabe02;

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
