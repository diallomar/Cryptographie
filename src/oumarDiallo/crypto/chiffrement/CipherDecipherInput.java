package oumarDiallo.crypto.chiffrement;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import oumarDiallo.crypto.symetrique.KeySymetrique;
import sun.security.rsa.SunRsaSign;

public class CipherDecipherInput {
    
    
    
    public static void getAndSaveCipherSymetrique(String filePath, String fileTosavePath, SecretKey key, String algo) throws Exception{
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        FileInputStream fis = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream(fileTosavePath+".txt");
        CipherInputStream cis = new CipherInputStream(fis,c);
        
        byte[] buf = new byte[16];
        int i = cis.read(buf);

        while (i != -1){
            fos.write(buf,0,i);
            i = cis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Fin du chiffrement");
        
    } 
    
    public static void getAndSaveCipherAsymetrique(String filePath, String fileTosavePath, PublicKey pk, String algo) throws Exception{
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, pk);
        FileInputStream fis = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream(fileTosavePath+".txt");
        CipherInputStream cis = new CipherInputStream(fis,c);
        
        byte[] buf = new byte[16];
        int i = cis.read(buf);

        while (i != -1){
            fos.write(buf,0,i);
            i = cis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Fin du chiffrement");
        
    } 
    
    public static void getAndSaveDecipherSymetrique(String filePath, String fileTosavePath, SecretKey key, String algo) throws Exception{
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, key);
        FileInputStream fis = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream(fileTosavePath+".txt");
        CipherInputStream cis = new CipherInputStream(fis,c);
        
        byte[] buf = new byte[16];
        int i = cis.read(buf);

        while (i != -1){
            fos.write(buf,0,i);
            i = cis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Fin du Déchiffrement");
        
    }
    
    public static void getAndSaveDecipherAsymetrique(String filePath, String fileTosavePath, PrivateKey pk, String algo) throws Exception{
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.DECRYPT_MODE, pk);
        FileInputStream fis = new FileInputStream(filePath);
        FileOutputStream fos = new FileOutputStream(fileTosavePath+".txt");
        CipherInputStream cis = new CipherInputStream(fis,c);
        
        byte[] buf = new byte[16];
        int i = cis.read(buf);

        while (i != -1){
            fos.write(buf,0,i);
            i = cis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("Fin du Déchiffrement");
    }
    
    public static void main(String[] args) throws Exception{
       
    }
}
