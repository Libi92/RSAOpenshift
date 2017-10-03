package com.rsaapp.security;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.PublicKey;
import java.security.Security;

/**
 * Created by 10945 on 29-Sep-17.
 */
public class BouncyRSA {

    private static String getHexString(byte[] b) throws Exception {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result +=
                    Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    private static String encrypt(String inputData) {

        Security.addProvider(new BouncyCastleProvider());

        String encryptedData = null;
        try {

            PublicKey key = KeyUtility.getInstance().getPublic();
            byte[] cipherText = null;
            //
            // get an RSA cipher object and print the provider
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding");

            // encrypt the plaintext using the public key
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(inputData.getBytes("UTF-8"));

            encryptedData = Base64.encodeBase64String(cipherText).replaceAll("[\r\n]+", "");
        } catch (Exception e) {
            System.out.println(e);
        }

        return encryptedData;
    }

    public static void main(String[] args) {
        String enc = encrypt("Hello World");
        System.out.println(enc);
    }
}
