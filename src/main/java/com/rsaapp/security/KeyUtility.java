package com.rsaapp.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PEMWriter;

import java.io.*;
import java.security.*;

/**
 * Created by 10945 on 29-Sep-17.
 */
public class KeyUtility {

    private String localPath = "C:/Users/10945/IdeaProjects/rsaapp/src/main/resources";

    public static com.rsaapp.security.KeyUtility getInstance() {
        return new com.rsaapp.security.KeyUtility();
    }

    public static void main(String[] args) {
        getInstance().createKeySpec();
    }

    public PrivateKey getPrivate() {
        Reader reader = getCloudReader("/rsa_private.pem");
        return (PrivateKey) getKey(reader);
    }

    public PublicKey getPublic() {
        Reader reader = getCloudReader("/rsa_public.pem");
        return (PublicKey) getKey(reader);
    }

    public PrivateKey getPrivateFromLocal() {
        Reader reader = getLocalReader(localPath + "/rsa_private.pem");
        return (PrivateKey) getKey(reader);
    }

    public PublicKey getPublicFromLocal() {
        Reader reader = getLocalReader(localPath + "/rsa_public.pem");
        return (PublicKey) getKey(reader);
    }

    private Key getKey(Reader reader) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            if (reader != null) {
                PEMReader pemReader = new PEMReader(reader);
                Object object = pemReader.readObject();
                if (object instanceof KeyPair) {
                    return ((KeyPair) object).getPrivate();
                }
                return (Key) object;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reader getLocalReader(String file) {
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Reader getCloudReader(String file) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(file);
        return new InputStreamReader(inputStream);
    }

    private void createKeySpec() {
        PublicKey publicKey = getPublicFromLocal();
        byte[] key = publicKey.getEncoded();
        File file = new File("rsa_key.public");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(key);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateKeyPare() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            KeyPair myPair = kpg.generateKeyPair();

            File privateFile = new File("rsa_private.pem");
            File publicFile = new File("rsa_public.pem");

            PEMWriter pemWriter = new PEMWriter(new FileWriter(privateFile));
            pemWriter.writeObject(myPair.getPrivate());
            pemWriter.flush();
            pemWriter.close();

            pemWriter = new PEMWriter(new FileWriter(publicFile));
            pemWriter.writeObject(myPair.getPublic());
            pemWriter.flush();
            pemWriter.close();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
