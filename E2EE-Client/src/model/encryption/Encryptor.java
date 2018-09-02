package model.encryption;

public interface Encryptor {
	
	public byte[] encrypt(byte[] plainText) throws Exception;
	public byte[] decrypt(byte[] cipherText) throws Exception;
	
}
