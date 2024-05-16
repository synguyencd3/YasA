package com.nashtech.rookie.yasa.util;

import com.nashtech.rookie.yasa.exceptions.CantLoginException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHA521Hasher {
    public static String hash(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            generatedPassword= Base64.getEncoder().encodeToString(bytes);
        }
        catch (NoSuchAlgorithmException e){
            throw new CantLoginException();
        }
        return generatedPassword;
    }


    public static boolean checkPassword(String hash, String attempt, byte[] salt){
        String generatedHash = hash(attempt, salt);
        return hash.equals(generatedHash);
    }
}
