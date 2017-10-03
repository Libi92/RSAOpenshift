package com.rsaapp.security;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by 10945 on 29-Sep-17.
 */
public class KeyUtility {

    public static KeyUtility getInstance() {
        return new KeyUtility();
    }

    public static void main(String[] args) {
        PublicKey publicKey = getInstance().getPublic();
        byte[] encoded = publicKey.getEncoded();
        String key = Base64.encodeBase64String(encoded);
        System.out.println(key);
    }

    public PrivateKey getPrivate() {
        return (PrivateKey) getKey("/rsa_key.private");
    }

    public PublicKey getPublic() {
//        return (PublicKey) getKey("/rsa_key.public");
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\10945\\IdeaProjects\\rsaapp\\src\\main\\resources\\rsa_key.public"));
            return (PublicKey) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Key getKey(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Key) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
