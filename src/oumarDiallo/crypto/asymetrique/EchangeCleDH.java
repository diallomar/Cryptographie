package oumarDiallo.crypto.asymetrique;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import oumarDiallo.crypto.symetrique.Utils;

public class EchangeCleDH {
    
    public static void getAndSaveSecretKeyDH2(String pathPrivKeyA, String pathPubKeyA, String pathPrivKeyB, String pathPubKeyB, String algo, String pathShareKey) throws Exception{
        FileOutputStream fos = new FileOutputStream(pathShareKey+".txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        
        PrivateKey privKeyA, privKeyB;
        PublicKey pubKeyA, pubKeyB;
        
        privKeyA = KeyAsymetrique.getPrivateKey(pathPrivKeyA);
        privKeyB = KeyAsymetrique.getPrivateKey(pathPrivKeyB);
        pubKeyA = KeyAsymetrique.getPublicKey(pathPubKeyA);
        pubKeyB = KeyAsymetrique.getPublicKey(pathPubKeyB);
        
        //        Instance de KeyAgreement
        KeyAgreement aka, bka;
        aka = KeyAgreement.getInstance("DH", "BC");
        bka = KeyAgreement.getInstance("DH", "BC");

        //        Initialisation de KeyAgreement
        aka.init(privKeyA);
        bka.init(privKeyB);
        
        //        Echange des paramtres public
        aka.doPhase(pubKeyB,true);
        bka.doPhase(pubKeyA,true);
        
        //        Génération de la partagée

        SecretKey secretA = aka.generateSecret(algo);
        oos.writeObject(secretA);
        oos.close();
        fos.close();
    }
    
    public static void getAndSaveSecretKeyDH3(String pathPrivKeyA, String pathPubKeyA, String pathPrivKeyB, String pathPubKeyB, String pathPrivKeyC, String pathPubKeyC, String algo, String pathShareKey) throws Exception{
    
        FileOutputStream fos = new FileOutputStream(pathShareKey+".txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        
        PrivateKey privKeyA, privKeyB, privKeyC;
        PublicKey pubKeyA, pubKeyB, pubKeyC;
        
        privKeyA = KeyAsymetrique.getPrivateKey(pathPrivKeyA);
        privKeyB = KeyAsymetrique.getPrivateKey(pathPrivKeyB);
        privKeyC = KeyAsymetrique.getPrivateKey(pathPrivKeyC);

        pubKeyA = KeyAsymetrique.getPublicKey(pathPubKeyA);
        pubKeyB = KeyAsymetrique.getPublicKey(pathPubKeyB);
        pubKeyC = KeyAsymetrique.getPublicKey(pathPubKeyC);
        
        //        Instance de KeyAgreement
        KeyAgreement aka, bka, cka;
        aka = KeyAgreement.getInstance("DH", "BC");
        bka = KeyAgreement.getInstance("DH", "BC");
        cka = KeyAgreement.getInstance("DH", "BC");
        //        Initialisation de KeyAgreement
        aka.init(privKeyA);
        bka.init(privKeyB);
        cka.init(privKeyC);
        
//         Echange des parametres
//        premier tour
        Key ab = aka.doPhase(pubKeyB, false);
        Key bc = bka.doPhase(pubKeyC, false);
        Key ca = cka.doPhase(pubKeyA, false);

//        deuxieme tour
        aka.doPhase(bc, true);
        bka.doPhase(ca, true);
        cka.doPhase(ab, true);
        
        //        Génération de la partagée

        SecretKey secretA = aka.generateSecret(algo);
        oos.writeObject(secretA);
        oos.close();
        fos.close();
        
    }
    
    public static void main(String[] args) throws Exception{

    }
}
