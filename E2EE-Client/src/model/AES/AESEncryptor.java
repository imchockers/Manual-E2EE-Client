/**
 * https://stackoverflow.com/questions/15554296/simple-java-aes-encrypt-decrypt-example
 * StackOverflow User: BullyWiiPlaza
 */
package model.AES;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import model.encryption.Encryptor;

public class AESEncryptor implements Encryptor {

	private byte[] key;
	private static final String ALGORITHM = "AES";
	
	public AESEncryptor () throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
		keyGen.init(256);
		SecretKey secretKey = keyGen.generateKey();
		key = secretKey.getEncoded();
	}
	
	public AESEncryptor (byte[] key) {
		this.key = key;
	}
	
	public byte[] getKey() {
		return key;
	}
	
	@Override
	public byte[] encrypt(byte[] plainText) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		return cipher.doFinal(plainText);
	}

	@Override
	public byte[] decrypt(byte[] cipherText) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		return cipher.doFinal(cipherText);
	}

}