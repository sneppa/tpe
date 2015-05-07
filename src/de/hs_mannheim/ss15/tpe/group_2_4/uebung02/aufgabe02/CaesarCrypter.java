package de.hs_mannheim.ss15.tpe.group_2_4.uebung02.aufgabe02;


public class CaesarCrypter implements Crypter{
	
	private int key;

	public CaesarCrypter(int key) {
		this.key = key%26; //Key could be larger, but it is not necessary to make it large
	}

	public void setKey(int key) { this.key = key%26; }
	
	public int getKey() { return this.key; }
	
	@Override
	public String encrypt(String message) {
		String cypherText = "";
		
		//Encryption by shifting all letters by given key to the right.
		while(message.length() > 0) {
			char h = message.charAt(0);
			//Only a-z and A-Z
			if((('a' <= h)  && (h <= 'z')) || (('A' <= h) && (h <= 'Z'))) {
				if((('a' <= h)  && (h <= 'z')))
					h = (char)(h - 'a'); //Number ranges from 0 - 25...
				else
					h = (char)(h - 'A');
				h = (char)((h + key) % 26); //...so it can be operated with modulo (rollover detection)
				h = (char)(h + 'A'); //Also make everything uppercase
			}
			cypherText += h;
			message = message.substring(1, message.length());
		}
		return cypherText;
	}

	@Override
	public String decrypt(String cypherText) {
		String message = "";
		
		while(cypherText.length() > 0) {
			char h = cypherText.charAt(0);
			//Only a-z and A-Z
			if((('a' <= h)  && (h <= 'z')) || (('A' <= h) && (h <= 'Z'))) {
				if((('a' <= h)  && (h <= 'z')))
					h = (char)(h - 'a'); //See encrypt()
				else
					h = (char)(h - 'A');
				h = (char)((h - key + 26) % 26); //+26 so modulo does work (somehow doesn't with negative ints)
				h = (char)(h + 'A');
			}
			message += h;
			cypherText = cypherText.substring(1, cypherText.length());
		}
		return message;
	}

}
