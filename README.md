# Manual-E2EE-Client

Just a rudimentary, definitely insecure E2EE client using RSA and AES to encrypt messages.

Usage:
  - Now has a GUI!
  - Generate a public key
  - Copy and send public key to someone
  - They then use the key to encrypt a message
  - They send you the key cipher and message cipher
  - You decrypt the message
  - Voila!
  - Refresh RSA and AES Encryption keys at any time with option 4
  - Note: If you refresh or restart the program, your private key is lost, therefore you cannot decrypt a message encrypted with your old   public key!
  - Note: Refresh doesn't work yet with GUI
