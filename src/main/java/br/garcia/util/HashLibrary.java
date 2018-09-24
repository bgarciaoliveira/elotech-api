package br.garcia.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashLibrary {

    private HashLibrary() {
        throw new IllegalStateException("Utility class");
    }

    public static byte[] sha256Digest(String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        return algorithm.digest(text.getBytes("UTF-8"));
    }

    public static String sha256(String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        byte[] digest = sha256Digest(text);

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    public static String passwordHash(String plainPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        final byte xorValue = 3;

        byte[] digest = sha256Digest(plainPass);

        for(int i = 0; i < digest.length; i++){
            digest[i] ^=xorValue;
        }

        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
