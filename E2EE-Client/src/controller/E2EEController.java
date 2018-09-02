package controller;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import model.AES.AESEncryptor;
import model.RSA.RSAEncryptor;
import model.helpers.Helper;
import view.ConsoleViewer;

public class E2EEController {

	static ConsoleViewer view;
	static AESEncryptor aes;
	static RSAEncryptor rsa;
	static Scanner sc;
	
	public static void main(String[] args) throws Exception {
		view = new ConsoleViewer();
		aes = new AESEncryptor();
		rsa = new RSAEncryptor();
		sc = new Scanner(System.in);
		
		boolean quit = false;
		
		while (!quit) {
			view.printMenu();
			
			try {
				
				
				switch (Integer.parseInt(sc.nextLine())) {
				case 1:
					view.printPubKey(rsa.getPublicKey());
					break;
				case 2:
					encrypt();
					break;
				case 3:
					decrypt();
					break;
				case 4:
					aes = new AESEncryptor();
					rsa = new RSAEncryptor();
					break;
				case 5:
					quit = true;
					break;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
	}
	
	private static void encrypt() throws Exception {
		// Get key
		view.encryptKeyPrompt();
		String pubKey = new String();
		pubKey += sc.nextLine();
		
		// Get message
		view.encryptMsgPrompt();
		String plainText = new String();
		plainText += sc.nextLine();
		
		// Encrypt message with AES
		byte[] msgCipher = aes.encrypt(plainText.getBytes(StandardCharsets.UTF_8));
		
		// Encrypt AESkey with RSA pubkey
		byte[] keyCipher = RSAEncryptor.encrypt(Helper.hexToBytes(pubKey), aes.getKey());

		
		byte[] cipherText = RSAEncryptor.encrypt(Helper.hexToBytes(pubKey), plainText.getBytes(StandardCharsets.UTF_8));
		
		view.printCipherText(keyCipher, msgCipher);
		
	}
	
	private static void decrypt() throws Exception {
		// Get key cipher
		view.decryptKeyPrompt();
		String keyCipherStr = sc.nextLine();
		
		// Get msg cipher
		view.decryptMsgPrompt();
		String msgCipherStr = sc.nextLine();
		
		// Decrypt AES key
		byte[] key = rsa.decrypt(Helper.hexToBytes(keyCipherStr));
		AESEncryptor aes1 = new AESEncryptor(key);
		
		// Decrypt and print message content
		view.printMessage(aes1.decrypt(Helper.hexToBytes(msgCipherStr)));
	}
	
}
