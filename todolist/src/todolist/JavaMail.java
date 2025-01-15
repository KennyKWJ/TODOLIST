/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static todolist.TaskStorage.USERS_FILE;

public class JavaMail {
    private static final String CENTRAL_EMAIL = "leetiewkun@gmail.com"; // Replace with your central email
    private static final String CENTRAL_PASSWORD = "khdjdxcbrbhdldfx"; // Replace with your app-specific password
    
    
    public static void SendMail() {
        if(TaskDetection.detectNearDueDate().isEmpty())
            return;
        else
        {
             String subject = "Reminder: Task Due Soon";
             String messageBody= "Your Task ";
             ArrayList<String> task = TaskDetection.detectNearDueDate();
             for(int i=0;i<task.size();i++)
             {
                 messageBody += ("\" "+task.get(i)+"\" ");
                 if(!(i==task.size()-1))
                     messageBody+=", ";
             }
             messageBody+="is due within 24 hours.";
             String recipient="";
             try( BufferedReader reader = new BufferedReader (new FileReader(USERS_FILE))){
                 String line;
                 while((line=reader.readLine())!=null)
                 {
                    String[] part = line.split(",");
                    if(Todolist.loggedInUsername.equals(part[0]))
                    {
                        recipient = part[2];
                        break;
                    }
                 }
                reader.close();
                if(recipient.equals(""))
                    OutputScene.GmailNotFound();
                else
                {
                    boolean result = sendEmail(recipient, subject, messageBody);
                    if (result) {
                        OutputScene.successToSendGmail();
                    } else {
                        OutputScene.failToSendGmail();
                    }
                }
                    
             }catch(IOException e){
                 OutputScene.fileOpenError();
             }
        }
    }

    private static boolean sendEmail(String recipient, String subject, String messageBody) {
        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session;
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CENTRAL_EMAIL, CENTRAL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(CENTRAL_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    
    
   
}
