import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author trhiep
 */
public class SaltPasswordGenerator {
    public static void main(String[] args) {
        // Specify the length of the salt string you want
        int saltLength = 16; // You can adjust this based on your requirements

        for (int i = 0; i < 100; i++) {
            // Generate a random salt string
            String saltString = generateSaltString(saltLength);

            // Print the salt string
            System.out.println("Random Salt String: " + saltString);
            System.out.println("Length: " + saltString.length());
        }
    }

    // Method to generate a random salt string
    private static String generateSaltString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[length];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
