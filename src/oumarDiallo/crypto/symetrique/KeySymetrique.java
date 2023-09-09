package oumarDiallo.crypto.symetrique;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public class KeySymetrique {
    
    public static void genAndSaveKey(String algo, int taille, String path) throws Exception{
        KeyGenerator kg = KeyGenerator.getInstance(algo);
        kg.init(taille);
        SecretKey sk = kg.generateKey();
        
        FileOutputStream fos = new FileOutputStream(path+".txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sk);
        oos.close();
        fos.close();
        System.out.println("cle enregistrer");
    }
    
    public static SecretKey getSecretKey(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        SecretKey key = (SecretKey) ois.readObject();
        ois.close();
        fis.close();
        return key;
    }
    public static void main(String[] args) throws Exception{
       
    }
    
    
}
