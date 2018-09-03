package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.AES.AESEncryptor;
import model.RSA.RSAEncryptor;


public class GUIViewer extends JFrame {
	
	static AESEncryptor aes;
	static RSAEncryptor rsa;
	
	public GUIViewer () throws Exception {
		aes = new AESEncryptor();
		rsa = new RSAEncryptor();
		
		this.setLayout(new BorderLayout());
		
		JComponent pubKeyPanel = new PubKeyPanel(rsa, aes);
		JComponent centerPanel = new CenterPanel(rsa, aes);
		
		pubKeyPanel.setBorder(BorderFactory.createTitledBorder("Public Key"));
		
		this.add(pubKeyPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
		showFrame();
	}
	
	private void showFrame()
	{
		// set a fixed size (would normally read from config file)
		setBounds(100, 100, 800, 600);
		// so we can close the frame on exit (when 'x' clicked in UI)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// make it visible
		setVisible(true);
	}
	
	class CenterPanel extends JPanel {
		
		CenterPanel(RSAEncryptor rsa, AESEncryptor aes) {
			this.setLayout(new GridLayout(1, 2));
			
			JComponent encPanel = new EncryptionPanel(aes, rsa);
			JComponent decPanel = new DecryptionPanel(rsa);
			
			encPanel.setBorder(BorderFactory.createTitledBorder("Encryption"));
			decPanel.setBorder(BorderFactory.createTitledBorder("Decryption"));
			
			this.add(encPanel);
			this.add(decPanel);
		}
	}
}
