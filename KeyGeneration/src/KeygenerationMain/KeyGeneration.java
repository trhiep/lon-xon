/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package KeygenerationMain;

import DAO.ProductKeyDAO;
import java.security.SecureRandom;
import java.util.Scanner;

/**
 *
 * @author tranh
 */
public class KeyGeneration {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    public static String generateLicenseKey(int length) {
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < length / 5; i++) {
            for (int j = 0; j < 5; j++) {
                int randomIndex = random.nextInt(CHARACTERS.length());
                key.append(CHARACTERS.charAt(randomIndex));
            }
            if (i < length / 5 - 1) {
                key.append("-");
            }
        }
        return key.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductKeyDAO productKeyDAO = new ProductKeyDAO();
        int keyLength = 30; // Change this to the desired length of your license key.
        System.out.print("ProductID: "); 
        int productID = Integer.parseInt(sc.nextLine());
        System.out.print("Expiration Date: ");
        String expDate = sc.nextLine();
        if (expDate.equals("")) {
            expDate = null;
        }
        System.out.print("Num Key: ");
        int numKey = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numKey; i++) {
            String licenseKey = generateLicenseKey(keyLength);
            boolean result = productKeyDAO.addKeyToDB(productID, licenseKey, expDate);
            if (result) {
                System.out.println("Add License Key " + licenseKey + " Successfully\n");
            } else {
                System.out.println("Add License Key " + licenseKey + " Failed!\n");
            }
        }
    }

}
