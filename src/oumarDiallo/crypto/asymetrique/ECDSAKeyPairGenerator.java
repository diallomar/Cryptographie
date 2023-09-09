package oumarDiallo.crypto.asymetrique;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSAKeyPairGenerator {

    public static void main(String[] args) throws Exception, Exception, Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Générer la paire de clés ECDSA
        generateANdSaveECDSAKeyPair("cle");

        // Enregistrer les clés dans des fichiers
//        saveECPrivateKeyToFile(keyPair.getPrivate(), "private_key.pem");
//        saveECPublicKeyToFile(keyPair.getPublic(), "public_key.pem");
    }

    private static void generateANdSaveECDSAKeyPair(String path) throws Exception {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256r1"); // Courbe elliptique P-256
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
            e.printStackTrace();
            
        }
    }

    private static void saveECPrivateKeyToFile(PrivateKey privateKey, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(privateKey.getEncoded());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveECPublicKeyToFile(PublicKey publicKey, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(publicKey.getEncoded());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
