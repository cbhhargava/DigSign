package com.cbhhargava;

import java.math.BigInteger;
import java.util.Random;

public class DigSign {

    private BigInteger safePrime;
    private BigInteger generator;
    private int noOfBits;
    private BigInteger privateKey = null;
    private BigInteger publicKey = null;
    private Random random = new Random();

    public DigSign(BigInteger generator){
        safePrime = null;
        this.generator = generator;
    }

    public DigSign(BigInteger safePrime, BigInteger generator){
        this.safePrime = safePrime;
        this.generator = generator;
        this.noOfBits = safePrime.bitLength();
    }

    public BigInteger getSafePrime(){
        return safePrime;
    }

    public void setSafePrime(BigInteger safePrime){
        this.safePrime = safePrime;
        noOfBits = safePrime.bitLength();
    }

    public BigInteger getPublicKey(){
        return publicKey;
    }

    public BigInteger getPrivateKey(){
        return privateKey;
    }

    public void generateKeys(){
        if(safePrime == null){
            throw new NullPointerException("safePrime not set. Please use setSafePrime(BigInteger) or generateSafePrime()");
        }
        privateKey = new BigInteger(noOfBits, random);
        publicKey = generator.modPow(privateKey, safePrime);
    }

    public void generateSafePrime(int noOfBits){
        this.noOfBits = noOfBits;
        safePrime = BigInteger.probablePrime(noOfBits, random);
    }

    public BigInteger signMessage(BigInteger msg){
        return generator.modPow(
                msg.subtract(privateKey).mod(safePrime.subtract(new BigInteger("1"))),
                safePrime
        );
    }

    public boolean verifySign(BigInteger signedMessage, BigInteger msg){
        BigInteger a = signedMessage.multiply(privateKey).mod(safePrime);
        BigInteger b = generator.modPow(msg, safePrime);
        return a.equals(b);
    }
}
