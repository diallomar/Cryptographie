/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oumarDiallo.crypto.connexion;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;



/**
 *
 * @author diallomar
 */
public class DataBase {
    
    public static Connection connexion(){
    Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/oumarDiallo", "postgres", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    return con;
    }
    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public static boolean verifyPassword(String inputPassword, String HASHED_PASSWORD) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(inputPassword.getBytes());
            byte[] hashedPasswordBytes = md.digest();

            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hashedPasswordBytes) {
                hashedPassword.append(String.format("%02x", b));
            }

            return HASHED_PASSWORD.equals(hashedPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }
     public static void main(String[] args) {
        
    }
}
