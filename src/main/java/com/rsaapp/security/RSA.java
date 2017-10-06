package com.rsaapp.security;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

/**
 * Created by 10945 on 29-Sep-17.
 */
public class RSA {

    public static String encryptWithPH(String str, RSAPaddingTypes paddingTypes, PublicKey pk) throws IOException, GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        try {
            String returnEnCode = null;


            Cipher pkCipher = Cipher.getInstance(paddingTypes.type);//RSA/ECB/PKCS1Padding
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

    public static String decryptWithPH(String data, RSAPaddingTypes paddingTypes, PrivateKey pk) throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        try {

            byte[] dec = Base64.decodeBase64(data.getBytes("UTF-8"));
            Cipher pkCipher = Cipher.getInstance(paddingTypes.type);
            pkCipher.init(Cipher.DECRYPT_MODE, pk);

            byte[] utf8 = pkCipher.doFinal(dec);
            return new String(utf8, "UTF8");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Decryption Error");
        }
    }

    public static void main(String[] args) {
        RSAPaddingTypes paddingTypes = RSAPaddingTypes.OAEPMD5;
        try {
            PublicKey pk = com.rsaapp.security.KeyUtility.getInstance().getPublicFromLocal();
            String enc = encryptWithPH("RSA POC", paddingTypes, pk);
            System.out.println(enc);

            PrivateKey key = KeyUtility.getInstance().getPrivateFromLocal();
            String dec = com.rsaapp.security.RSA.decryptWithPH(enc, paddingTypes, key);
            System.out.println(dec);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
