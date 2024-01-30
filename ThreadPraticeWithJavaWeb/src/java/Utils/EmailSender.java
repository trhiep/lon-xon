/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tranh
 */
public class EmailSender extends Thread{
    
    @Override
    public void run() {
        sendVerificationCodeToEmail(to, "123456");
    }
    
    static String from = "YOUR_GMAIL_HERE";
    static String password = "YOUR_PASSWORD_HERE";

    private String to;

    public void setTo(String to) {
        this.to = to;
    }
    
    public static boolean sendVerificationCodeToEmail(String email, String code) {
        long currentTimeMillis = System.currentTimeMillis();
        String subject = "[TEST] TEST THREAD AT " + currentTimeMillis;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        // Create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset = UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent("<table style=\"font-family:'Open Sans',sans-serif\" width=\"100%\" border=\"0\">\n"
                    + "        <tbody>\n"
                    + "            <tr>\n"
                    + "                <td style=\"word-break:break-word;padding:28px 33px 25px;font-family:'Open Sans',sans-serif\"\n"
                    + "                    align=\"left\">\n"
                    + "                    <div>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Hi <strong>" + email + "</strong>, here is your verification code: </p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            <strong>" + code + "</strong></p>\n"
                    + "                        <p style=\"font-size:14px;line-height:200%;\">\n"
                    + "                            Any questions please contact: <a href=\"mailto:g5investment.swp391@gmail.com\"\n"
                    + "                                target=\"_blank\">g5investment.swp391@gmail.com</a> to\n"
                    + "                            be answered.</p>\n"
                    + "                    </div>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </tbody>\n"
                    + "    </table>", "text/html;charset=UTF-8");
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
        }
        return false;
    }
}
