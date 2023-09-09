package oumarDiallo.crypto.asymetrique;



import java.io.*;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyAsymetrique {

    public static KeyPair genKey(String algo, int taille) throws Exception{
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
        kpg.initialize(taille, new SecureRandom());
        return kpg.generateKeyPair();
    }

    public static void saveKey(KeyPair kp, String path) throws Exception{
        FileOutputStream fo = new FileOutputStream(path+"_priv.key");
        ObjectOutputStream oos = new ObjectOutputStream(fo);
        oos.writeObject(kp.getPrivate());
        oos.close();
        fo.close();
        FileOutputStream fo1 = new FileOutputStream(path+"_pub.pub");
        ObjectOutputStream oos1 = new ObjectOutputStream(fo1);
        oos1.writeObject(kp.getPublic());
        oos1.close();
        fo1.close();

    }

    public static PrivateKey getPrivateKey(String pathPublic )throws Exception{
        FileInputStream fis = new FileInputStream(pathPublic);
        ObjectInputStream oos = new ObjectInputStream(fis);
        return (PrivateKey) oos.readObject();
    }
    public static PublicKey getPublicKey(String pathPrivate )throws Exception{
        FileInputStream fis = new FileInputStream(pathPrivate);
        ObjectInputStream oos = new ObjectInputStream(fis);
        return (PublicKey) oos.readObject();
    }
    
    public static void generateANdSaveECDSAKeyPair(String path, int taille) throws Exception {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecGenParameterSpec = null;
            switch (taille) {
                case 256:
                    ecGenParameterSpec = new ECGenParameterSpec("secp256r1");
                    break;
                case 384:
                    ecGenParameterSpec = new ECGenParameterSpec("secp384r1");
                    break;
                default:
                    ecGenParameterSpec = new ECGenParameterSpec("secp521r1");
                    break;
            }
            
            keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
            KeyPair kp = keyPairGenerator.generateKeyPair();
            FileOutputStream fos1 = new FileOutputStream(path+"_priv.key");
            fos1.write(kp.getPrivate().getEncoded());
            fos1.close();
            FileOutputStream fos2 = new FileOutputStream(path+"_pub.pub");
            fos2.write(kp.getPublic().getEncoded());
            fos2.close();
            System.out.println("cle enregistrer avec succes");
        } catch (Exception e) {
            
        }
    }
    
    public static PublicKey getPublicKeyECDSA(String fic) throws Exception {
        FileInputStream fis = new FileInputStream(fic);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        KeyFactory kf = KeyFactory.getInstance("ECDSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
        PublicKey pub = kf.generatePublic(spec);
        fis.close();
        return pub;
    }
//
    public static PrivateKey getPrivateKeyECDSA(String fic) throws Exception {
        FileInputStream fis = new FileInputStream(fic);
        byte[] b = new byte[fis.available()];
        fis.read(b);
        KeyFactory kf = KeyFactory.getInstance("ECDSA");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
        PrivateKey priv = kf.generatePrivate(spec);
        fis.close();
        return priv;
    }

    public static void main(String[] args) throws Exception {
       
    }
}
