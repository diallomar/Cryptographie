/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oumarDiallo.crypto.hachage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import oumarDiallo.crypto.symetrique.KeySymetrique;

/**
 *
 * @author HP
 */
public class HachInput {
    
    public static void getAndSaveMD(String pathFileToHach,String fonctionHachage, String pathHach) throws Exception{
         MessageDigest md = MessageDigest.getInstance(fonctionHachage);
        
        FileInputStream fis = new FileInputStream(pathFileToHach);
        FileOutputStream fos = new FileOutputStream(pathHach+".txt");
        DigestInputStream dis = new DigestInputStream(fis, md);
        byte[] b = new byte[64];
        byte[] empreinte =null ;
        while(dis.read(b)!=-1){
            empreinte = md.digest();
            fos.write(empreinte);
        }
        fis.close();
        fos.close();
        dis.close();
    }
    
    public static void getAndSaveMac(String pathFileToHach, String fonctionHachage, String pathKey, String algo, String pathHach) throws Exception{
        
        FileInputStream fis = new FileInputStream(pathFileToHach);
        FileOutputStream fos = new FileOutputStream(pathHach+".txt");
        byte [] b = new byte[64];
        fis.read(b);
        fis.close();
        SecretKey key = KeySymetrique.getSecretKey(pathKey);   
        Mac ma = Mac.getInstance(fonctionHachage);
        Key macKey = new SecretKeySpec(key.getEncoded(), algo);
        ma.init(macKey);
        byte[] hash = ma.doFinal(b);
        fos.write(hash);
        fos.close();
    
    }
    
    public static boolean getHashMd(String pathFile, String pathHash, String algo) throws Exception{
        MessageDigest md = MessageDigest.getInstance(algo);
        File file = new File(pathHash);
        FileInputStream fis = new FileInputStream(pathFile);
        FileInputStream fis1 = new FileInputStream(file);
        DigestInputStream dis = new DigestInputStream(fis, md);
        byte[] b = new byte[64];
        byte[] empreinte =null ;
        byte[] hash = new byte[(int) file.length()];
        fis1.read(hash);
        while(dis.read(b)!=-1){
            empreinte = md.digest();
        }
        fis.close();
        fis1.close();
        dis.close();
        return Arrays.equals(hash, empreinte);
    }
    
    public static boolean getHashMac(String pathFileToHach, String pathHach, String fonctionHachage, String pathKey, String algo) throws Exception{
        FileInputStream fis = new FileInputStream(pathFileToHach);
        File file = new File(pathHach);
        FileInputStream fis1 = new FileInputStream(file);
        byte [] b = new byte[64];
        byte [] empreinteVerif = new byte[(int) file.length()];
        fis1.read(empreinteVerif);
        fis.read(b);
        fis.close();
        fis1.close();
        SecretKey key = KeySymetrique.getSecretKey(pathKey);   
        Mac ma = Mac.getInstance(fonctionHachage);
        Key macKey = new SecretKeySpec(key.getEncoded(), algo);
        ma.init(macKey);
        byte[] hash = ma.doFinal(b);
        
    return Arrays.equals(empreinteVerif, hash);
    }
            
    public static void main(String[] args) throws Exception{
          String pathFile = "/home/diallomar/Documents/fichier.txt";
          String pathHash = "/home/diallomar/Documents/hach";
          String pathHash1 = "/home/diallomar/Documents/hach.txt";
          String key = "/home/diallomar/Documents/des.txt";
          
          getAndSaveMac(pathFile, "HmacSHA1", key, "DES", pathHash);
          System.out.println(getHashMac(pathFile, pathHash1, "HmacSHA1", key, "DES"));
    }
}
