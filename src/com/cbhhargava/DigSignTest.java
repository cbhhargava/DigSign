package com.cbhhargava;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class DigSignTest{
    public static void main(String args[]){
        String message = "Words are flowing out like endless rain into a paper cup";
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    message.getBytes(StandardCharsets.UTF_8)
            );
            BigInteger msg = new BigInteger(1, encodedHash);
            //Test
            DigSign ds = new DigSign(new BigInteger("2"));
            ds.generateSafePrime(513);
            ds.generateKeys();
            System.out.println("Safe Prime: " + ds.getSafePrime());
            System.out.println("Public Key: " + ds.getPublicKey());
            System.out.println("Private Key: " + ds.getPrivateKey());
            BigInteger signedMessage = ds.signMessage(msg);
            System.out.println("Signed Message: " + signedMessage);
            System.out.println("Verify True: " + ds.verifySign(signedMessage, msg));
            System.out.println("Verify False: " + ds.verifySign(signedMessage, msg.add(new BigInteger("1"))));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}