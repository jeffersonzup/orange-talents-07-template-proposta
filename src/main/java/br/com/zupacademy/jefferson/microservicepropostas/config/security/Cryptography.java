package br.com.zupacademy.jefferson.microservicepropostas.config.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.text.AES256TextEncryptor;

public class Cryptography {

    private static Cryptography instance = null;

    private String documentoLimpo;

    private static String secret = "${jasypt.secret}";

    public static String encrypt(String documentoLimpo){
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secret);
        String encrypted = encryptor.encrypt(documentoLimpo);
        return encrypted;
    }

    public static String decrypt(String encrypted){
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secret);
        return encryptor.decrypt(encrypted);
    }

    public static String hash(String documentoLimpo){
        StringBuilder builder = new StringBuilder();
        return DigestUtils.sha256Hex(builder.append(documentoLimpo).append(secret).toString());
    }
}
