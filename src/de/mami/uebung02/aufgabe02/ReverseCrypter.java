package de.mami.uebung02.aufgabe02;


public class ReverseCrypter implements Crypter {

	@Override
	public String encrypt(String message) {
		String cypherText = "";
		
		//Simply reverses the string.
		for(int i = 0; i < message.length(); i++) {
			cypherText += message.charAt((message.length()-1)-i);
		}
		return cypherText;
	}

	@Override
	public String decrypt(String cypherText) {
		return encrypt(cypherText);
	}

	
}
