/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oumarDiallo.crypto.chiffrement;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import oumarDiallo.crypto.symetrique.KeySymetrique;
import oumarDiallo.crypto.symetrique.Utils;

/**
 *
 * @author diallomar
 */
public class CipherDeciperLive {
    
    public static byte[] cipherLive(String text, SecretKey key, String algo)throws Exception{
        Cipher c  = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] chiffre = c.doFinal(text.getBytes());
        return chiffre;
    }
    
    public static String decipherLive(byte[] text, SecretKey key, String algo)throws Exception{
        Cipher c  = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] chiffre = c.doFinal(text);
        return new String(chiffre);
    }
    
    public static String hachLive(String text, String fonction) throws Exception{
        MessageDigest md = MessageDigest.getInstance(fonction);
        md.update(text.getBytes());
        byte[] hach = md.digest();
        return Utils.toHex(hach);
        
    }
    public static void main(String[] args) throws Exception {

    }
}
