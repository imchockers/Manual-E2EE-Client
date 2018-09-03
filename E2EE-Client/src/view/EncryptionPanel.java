package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.AES.AESEncryptor;
import model.RSA.RSAEncryptor;
import model.helpers.Helper;

public class EncryptionPanel extends JPanel implements ActionListener {

	JTextArea encPubKey = new JTextArea(3, 60);
	JTextArea msg = new JTextArea(3, 60);
	JButton encrypt = new JButton("Encrypt");
	JTextArea keyCipher = new JTextArea(3, 60);
	JTextArea msgCipher = new JTextArea(3, 60);
	
	AESEncryptor aes;
	RSAEncryptor rsa;
	
	public EncryptionPanel(AESEncryptor aes, RSAEncryptor rsa) {
		this.rsa = rsa;
		this.aes = aes;
		
		this.setLayout(new GridLayout(3, 2));
		
		encrypt.addActionListener(this);
		keyCipher.setEditable(false);
		msgCipher.setEditable(false);
		keyCipher.setLineWrap(true);
		msgCipher.setLineWrap(true);
		encPubKey.setLineWrap(true);
		msg.setLineWrap(true);
		
		keyCipher.setBorder(BorderFactory.createTitledBorder("Key Cipher"));
		msgCipher.setBorder(BorderFactory.createTitledBorder("Message Cipher"));
		encPubKey.setBorder(BorderFactory.createTitledBorder("Public Key"));
		msg.setBorder(BorderFactory.createTitledBorder("Message"));
		
		JScrollPane pubKeyScroll = new JScrollPane(encPubKey);
		JScrollPane msgScroll = new JScrollPane(msg);
		JScrollPane keyCipherScroll = new JScrollPane(keyCipher);
		JScrollPane msgCipherScroll = new JScrollPane(msgCipher);
		
		
		this.add(pubKeyScroll);
		this.add(msgScroll);
		this.add(encrypt);
		this.add(new JPanel());
		this.add(keyCipherScroll);
		this.add(msgCipherScroll);
	}
	
	private void encrypt(String pubKey, String plainText) throws Exception {
		// Encrypt message with AES
		byte[] msgCipher = aes.encrypt(plainText.getBytes(StandardCharsets.UTF_8));
		
		// Encrypt AESkey with RSA pubkey
		byte[] keyCipher = RSAEncryptor.encrypt(Helper.hexToBytes(pubKey), aes.getKey());

		this.keyCipher.setText(Helper.bytesToHex(keyCipher));
		this.msgCipher.setText(Helper.bytesToHex(msgCipher));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			encrypt(encPubKey.getText(), msg.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
}
