package com.rsaapp.security;

/**
 * Created by 10945 on 05-Oct-17.
 */
public enum RSAPaddingTypes {

    NONENOPADDING("RSA/NONE/NoPadding"),
    NONEPKSC1("RSA/NONE/PKCS1Padding"),
    NONEOAEP("RSA/NONE/OAEPPadding"),
    NONEOAEPSHA1("RSA/NONE/OAEPwithSHA-1andMGF1Padding"),
    NONEOAEPSHA256("RSA/NONE/OAEPWithSHA-256AndMGF1Padding"),
    NONEOAEPMD5("RSA/NONE/OAEPwithMD5andMGF1Padding"),

    NOPADDING("RSA/ECB/NoPadding"),
    PKSC1("RSA/ECB/PKCS1Padding"),
    OAEP("RSA/ECB/OAEPPadding"),
    OAEPSHA1("RSA/ECB/OAEPwithSHA-1andMGF1Padding"),
    OAEPSHA256("RSA/ECB/OAEPWithSHA-256AndMGF1Padding"),
    OAEPMD5("RSA/ECB/OAEPwithMD5andMGF1Padding");

    String type;

    RSAPaddingTypes(String type) {
        this.type = type;
    }
}
