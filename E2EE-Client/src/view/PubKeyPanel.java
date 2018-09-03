package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.AES.AESEncryptor;
import model.RSA.RSAEncryptor;
import model.helpers.Helper;

public class PubKeyPanel extends JPanel implements ActionListener {

	private RSAEncryptor rsa;
	private AESEncryptor aes;
	
	private JTextArea myPubKey = new JTextArea(2, 20);
	private JButton refresh = new JButton("Refresh Keys");
	
	public PubKeyPanel(RSAEncryptor rsa, AESEncryptor aes) {
		this.rsa = rsa;
		this.aes = aes;
		
		this.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane(myPubKey);
		
		myPubKey.setEditable(false);
		myPubKey.setText(Helper.bytesToHex(rsa.getPublicKey()));
		myPubKey.setLineWrap(true);
		
		refresh.addActionListener(this);
		
		this.add(scrollPane, BorderLayout.NORTH);
		this.add(refresh, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
