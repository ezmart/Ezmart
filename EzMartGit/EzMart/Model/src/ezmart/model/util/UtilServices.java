package ezmart.model.util;

import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UtilServices {

    private String mailSMTPServer;
    private String mailPassword;
    private String subject;
    private String msg;

    // Parametros
    // email -> EMAIL DESTINÁTARIO
    // userName -> Nome do Usuário
    public void sendEmail(Map<String, Object> values) throws NoSuchProviderException, AddressException, MessagingException {

        String email = (String) values.get("email");
        String type = (String) values.get("type");

        try {
            if (type.equals(SystemConstant.EMAIL.AUTHENTICATION.CONFIRMATION_REGISTER)) {
                String userName = (String) values.get("name");
                subject = "Email de confirmação";
                msg = "Olá " + userName + ",\n"
                        + "Clique no link para confirmar seu cadastro! \n\n"
                        + "http://localhost:8084/ezmartWeb/" + email + "/authentication";

            } else if (type.equals(SystemConstant.EMAIL.AUTHENTICATION.RECOVERY_PASSWORD)) {
                String newPassword = (String) values.get("newPassword");
                subject = "Recuperar acesso";
                msg = "Olá,\n"
                        + "Sua senha foi alterada! \n\n"
                        + "Nova Senha: " + newPassword + "\n\n"
                        + "Acesse seu perfil e altere esta senha!\n\n\n"
                        + "http://localhost:8084/login";
            }

            mailSMTPServer = "ezmartfai@gmail.com";
            mailPassword = "jessicapistola";

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", "true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailSMTPServer, mailPassword);
                }
            });

            Transport transport = session.getTransport();
            InternetAddress addressFrom = new InternetAddress(mailSMTPServer);

            MimeMessage message = new MimeMessage(session);
            message.setSender(addressFrom);
            message.setSubject(subject);
            message.setContent(msg, "text/plain");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            transport.connect();
            Transport.send(message);
            transport.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    // Gera um número aleatório para uma nova senha
    public String generatePassword() {
        String newPassword = "";
        try {
            Random random = new Random();
            int array[] = new int[10]; // 10 números serão gerados.
            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(9); // Gera números aleatórios com limite 50.
                int chupeta = array[i];
                newPassword += "" + chupeta;
            }
            System.out.println(newPassword);
        } catch (Exception exception) {
            System.out.println(exception);

        }

        return newPassword;
    }

    // trata os 'exception' e os grava em um arquivo
    public void logManager(String TAG, String method, String exception) {

    }
}
