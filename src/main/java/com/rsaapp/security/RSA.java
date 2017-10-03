package com.rsaapp.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by 10945 on 29-Sep-17.
 */
public class RSA {

    public static String encryptWithPH(String str) throws IOException, GeneralSecurityException {
        try {
            String returnEnCode = null;

            PublicKey pk = KeyUtility.getInstance().getPublic();
            Cipher pkCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");//RSA/ECB/PKCS1Padding
            pkCipher.init(Cipher.ENCRYPT_MODE, pk);
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = pkCipher.doFinal(utf8);

            returnEnCode = new String(Base64.encodeBase64(enc)).replaceAll("[\r\n]+", "");
            return returnEnCode;
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneralSecurityException("Encrypt Error");
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            throw new GeneralSecurityException("Encrypt Error");
        }
    }

    public static String decryptWithPH(String data) throws Exception {
        try {
            PrivateKey pk = KeyUtility.getInstance().getPrivate();

            byte[] dec = Base64.decodeBase64(data.getBytes("UTF-8"));
            Cipher pkCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            pkCipher.init(Cipher.DECRYPT_MODE, pk);

            byte[] utf8 = pkCipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Decryption Error");
        }
    }

    public static void main(String[] args) {
        try {
            String enc = encryptWithPH("RSA Message : help");
            System.out.println(enc);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
