package de.mami.uebung02.aufgabe02;


public class Test {

	public static void main(String[] args) {
		Crypter caesar = new CaesarCrypter(3);
		System.out.println("Caesar:");
		String message = caesar.encrypt("Baba aller Babas! uvwxyz");
		System.out.println(message);
		message = caesar.decrypt(message);
		System.out.println(message);
		
		Crypter reverse = new ReverseCrypter();
		System.out.println("Reverse:");
		String message2 = reverse.encrypt("Rasiert alle, amina koyum.");
		System.out.println(message2);
		message2 = reverse.decrypt(message2);
		System.out.println(message2);
		
		System.out.println("Geheime Nachricht: XHMSNYYXYJQQJS");
		caesar = new CaesarCrypter(5);
		String message3 = "XHMSNYYXYJQQJS";
		message3 = reverse.decrypt(caesar.decrypt(reverse.decrypt(message3)));
		System.out.println(message3);
	}

}
