package oumarDiallo.crypto.sign;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import oumarDiallo.crypto.symetrique.Utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.imageio.stream.FileImageInputStream;
import oumarDiallo.crypto.asymetrique.KeyAsymetrique;

public class SignatureInput {
    
    public static void getAndSaveSignature(String pathFileToSign, String pathPrivKey, String pathSign, String algo) throws Exception{
        
        PrivateKey priv;
        if (algo.equals("ECDSA")) {
           priv =  KeyAsymetrique.getPrivateKeyECDSA(pathPrivKey);
        } else {
            priv = KeyAsymetrique.getPrivateKey(pathPrivKey);
        }
        FileInputStream fis = new FileInputStream(pathFileToSign);
        FileOutputStream fos = new FileOutputStream(pathSign+".txt");
        
        byte[] b = new byte[64];
        fis.read(b);
        fis.close(); 
        
        Signature sig = Signature.getInstance(algo);
        sig.initSign(priv);
        MessageDigest md = MessageDigest.getInstance("SHA512");
        md.update(b);
        byte[] empreinte = md.digest();
        sig.update(empreinte);
        byte[] signe = sig.sign();
        fos.write(signe);
        fos.close();
        
    
    }
    
    
    public static boolean getAndCheckSignature(String pathFileToCheck, String pathSigned, String pathPubKey, String algo) throws Exception{
        PublicKey pub;
        
        if (algo.equals("ECDSA")) {
            pub = KeyAsymetrique.getPublicKeyECDSA(pathPubKey);
        } else {
            pub = KeyAsymetrique.getPublicKey(pathPubKey);
        }
        
        File file = new File(pathSigned);
        FileInputStream fis = new FileInputStream(pathFileToCheck);
        FileInputStream fis2 = new FileInputStream(file); 
        byte[] b1 = new byte[64];
        byte[] b2 = new byte[(int) file.length()];
        fis.read(b1);
        fis2.read(b2);
        fis2.close();
        fis.close();
        
       
        Signature sig = Signature.getInstance(algo);
        sig.initVerify(pub);
        MessageDigest md = MessageDigest.getInstance("SHA512");
        md.update(b1);
        byte [] empreinte = md.digest();
        sig.update(empreinte);
        return sig.verify(b2);
    }
    
    
    
    public static void main(String[] args) throws Exception{

    }
}
