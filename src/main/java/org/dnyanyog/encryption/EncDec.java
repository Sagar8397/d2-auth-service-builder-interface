package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncDec {

	public static SecretKey generateAesKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		return keyGenerator.generateKey();
	}

	public static String encrypt(String plainText, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] plainTextByte = plainText.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedData = cipher.doFinal(plainTextByte);

		String encryptedDataInString = Base64.getEncoder().encodeToString(encryptedData);
		return encryptedDataInString;
	}

	public static String decrypt(String encryptedData, SecretKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] encryptedByteArrayData = Base64.getDecoder().decode(encryptedData);

		byte[] decrytedButes = cipher.doFinal(encryptedByteArrayData);
		return new String(decrytedButes, StandardCharsets.UTF_8);
	}
	
	public static void main(String args[]) throws Exception{
		SecretKey key = generateAesKey();
		String plainText = "Test@123";
		
		String encryptedData = encrypt(plainText, key);
		System.out.println("encrypted : " +encryptedData);
		
		String decryptedData = decrypt(encryptedData, key);
		System.out.println("decrypted :  " +decryptedData);
	}
}
