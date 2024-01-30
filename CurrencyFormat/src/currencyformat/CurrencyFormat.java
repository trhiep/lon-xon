/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package currencyformat;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author tranh
 */
public class CurrencyFormat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String amount = "100000000000000000000000000000.00";
        String formattedAmount = formatToCurrency(amount);
        System.out.println(formattedAmount);
    }

    public static String formatToCurrency(String amount) {
        StringBuilder formattedAmount = new StringBuilder();
        int endPoint = amount.indexOf(".");
        String tail = amount.substring(endPoint);
        int count = 0;
        for (int i = endPoint - 1; i >= 0; i--) {
            formattedAmount.insert(0, amount.charAt(i));
            count++;

            if (count % 3 == 0 && i > 0) {
                formattedAmount.insert(0, ",");
            }
        }

        return formattedAmount.toString() + tail;
    }
    
}
