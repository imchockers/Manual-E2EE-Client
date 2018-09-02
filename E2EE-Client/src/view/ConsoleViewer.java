package view;

import model.helpers.Helper;

public class ConsoleViewer {

	public static final String MENU = "========E2EE========\n"
			+ "1. Get Public Key.\n"
			+ "2. Encrypt Message.\n"
			+ "3. Decrypt Digest.\n"
			+ "4. Refresh Keys.\n"
			+ "5. Quit.\n"
			+ "Enter option: ";
	
	public static final String PUB_KEY_PREFIX = "Public Key: ";
	public static final String ENCRYPT_KEY_PROMPT = "Enter Public Key: ";
	public static final String ENCRYPT_MSG_PROMPT = "Enter Message to encrypt: ";
	public static final String DECRYPT_KEY_PROMPT = "Enter key cipher: ";
	public static final String DECRYPT_MSG_PROMPT = "Enter cipher text to decrypt: ";
	public static final String MSG_CIPHER_PREFIX = "Message Cipher: ";
	public static final String KEY_CIPHER_PREFIX = "Key Cipher: ";
	public static final String MSG_PREFIX = "Message: ";
	
	
	public void printMenu() {
		System.out.print(MENU);
	}
	
	public void printPubKey(byte[] key) {
		System.out.println(PUB_KEY_PREFIX + Helper.bytesToHex(key));
	}
	
	public void encryptKeyPrompt() {
		System.out.print(ENCRYPT_KEY_PROMPT);
	}
	
	public void encryptMsgPrompt() {
		System.out.print(ENCRYPT_MSG_PROMPT);
	}
	
	public void decryptMsgPrompt() {
		System.out.print(DECRYPT_MSG_PROMPT);
	}
	
	public void decryptKeyPrompt() {
		System.out.println(DECRYPT_KEY_PROMPT);
	}
	
	public void printCipherText(byte[] keyCipher, byte[] msgCipher) {
		System.out.println(KEY_CIPHER_PREFIX + Helper.bytesToHex(keyCipher) +
				"\n" + MSG_CIPHER_PREFIX + Helper.bytesToHex(msgCipher));
	}
	
	public void printMessage(byte[] msg) {
		System.out.println(MSG_PREFIX + new String(msg));
	}

}
