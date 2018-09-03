package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.AES.AESEncryptor;
import model.RSA.RSAEncryptor;
import model.helpers.Helper;

public class DecryptionPanel extends JPanel implements ActionListener {
	private RSAEncryptor rsa;
	
	JTextArea keyCipher = new JTextArea(20, 60);
	JTextArea msgCipher = new JTextArea(20, 60);
	JButton decrypt = new JButton("Decrypt");
	JTextArea output = new JTextArea(20, 60);
	
	public DecryptionPanel(RSAEncryptor rsa) {
		this.rsa = rsa;
		
		this.setLayout(new GridLayout(3, 1));
		
		decrypt.addActionListener(this);
		output.setEditable(false);
		output.setLineWrap(true);
		keyCipher.setLineWrap(true);
		msgCipher.setLineWrap(true);
		
		keyCipher.setBorder(BorderFactory.createTitledBorder("Key Cipher"));
		msgCipher.setBorder(BorderFactory.createTitledBorder("Message Cipher"));
		output.setBorder(BorderFactory.createTitledBorder("Output"));
		
		this.add(keyCipher);
		this.add(msgCipher);
		this.add(decrypt);
		this.add(new JPanel());
		this.add(output);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			decrypt(keyCipher.getText(), msgCipher.getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private void decrypt(String keyCipherStr, String msgCipherStr) throws Exception {
		// Decrypt AES key
		byte[] key = rsa.decrypt(Helper.hexToBytes(keyCipherStr));
		AESEncryptor aes1 = new AESEncryptor(key);
		
		// Decrypt and print message content
		this.output.setText(new String(aes1.decrypt(Helper.hexToBytes(msgCipherStr))));
	}
	
}
