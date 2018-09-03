package controller;

import java.util.Scanner;
import javax.swing.SwingUtilities;

import model.AES.AESEncryptor;
import model.RSA.RSAEncryptor;
import view.GUIViewer;

public class E2EEController {

	static AESEncryptor aes;
	static RSAEncryptor rsa;
	static Scanner sc;
	
	public static void main(String[] args) throws Exception {
		
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					new GUIViewer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
