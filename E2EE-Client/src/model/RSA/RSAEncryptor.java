/**
 * https://gist.github.com/dmydlarz/32c58f537bb7e0ab9ebf
 * 
 * User: dmydlarz
 * 
 */

package model.RSA;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import model.encryption.Encryptor;

public class RSAEncryptor implements Encryptor {

	private static final int KEY_SIZE = 2048;
	private static final String ALGORITHM = "RSA";
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public RSAEncryptor () throws Exception {
		KeyPair keyPair = buildKeyPair();
		this.publicKey = keyPair.getPublic();
		this.privateKey = keyPair.getPrivate();
	}
	
	public byte[] getPublicKey() {
		return publicKey.getEncoded();
	}
	
	@Override
	public byte[] encrypt(byte[] plainText) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

        return cipher.doFinal(plainText);
	}

	@Override
	public byte[] decrypt(byte[] cipherText) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        
        return cipher.doFinal(cipherText);
	}
	
	private KeyPair buildKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);      
        return keyPairGenerator.genKeyPair();
	}

	public static byte[] encrypt(byte[] publicKeyBytes, byte[] plainText) throws Exception {
		PublicKey publicKey = 
			    KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
		
		Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);  

        return cipher.doFinal(plainText);
	}
}
