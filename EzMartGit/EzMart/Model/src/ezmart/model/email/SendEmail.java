//package ezmart.model.email;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.NoSuchProviderException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class SendEmail {
//
//    public final static String CONFIRMATION_REGISTER = "CONFIRMATION_REGISTER";
//
//    private String mailSMTPServer;
//    private String mailPassword;
//    private String subject;
//    private String msg;
//
//    // Parametros
//    // email -> EMAIL DESTINÁTARIO
//    // userName -> Nome do Usuário
//    public void sendEmail(String email, String userName, String type) throws NoSuchProviderException, AddressException, MessagingException {
//
//        try {
//            if (type.equals(CONFIRMATION_REGISTER)) {
//                subject = "Email de confirmação";
//                msg = "Olá " + userName + ",\n"
//                        + "Clique no link para confirmar seu cadastro! \n\n"
//                        + "http://localhost:8084/ezmartWeb/'" + email + "'/authentication";
//            }
//            mailSMTPServer = "ezmartfai@gmail.com";
//            mailPassword = "jessicapistola";
//
//            Properties props = new Properties();
//            props.setProperty("mail.transport.protocol", "smtp");
//            props.setProperty("mail.host", "smtp.gmail.com");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", "465");
//            props.put("mail.debug", "true");
//            props.put("mail.smtp.socketFactory.port", "465");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.socketFactory.fallback", "false");
//
//            Session session = Session.getDefaultInstance(props,
//                    new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(mailSMTPServer, mailPassword);
//                }
//            });
//
//            Transport transport = session.getTransport();
//            InternetAddress addressFrom = new InternetAddress(mailSMTPServer);
//
//            MimeMessage message = new MimeMessage(session);
//            message.setSender(addressFrom);
//            message.setSubject(subject);
//            message.setContent(msg, "text/plain");
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//
//            transport.connect();
//            Transport.send(message);
//            transport.close();
//        } catch (Exception e) {
//            // Tratar
//        }
//    }
//}
