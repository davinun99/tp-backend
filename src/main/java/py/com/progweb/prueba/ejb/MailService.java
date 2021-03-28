package py.com.progweb.prueba.ejb;
@Singleton
public class MailService {
     
    @Inject
    private Session mailSession; //more on this later
     
    @Asynchronous
    @Lock(LockType.READ)
    public void sendMail(@Observes(during = TransactionPhase.AFTER_SUCCESS) MailEvent event) {
        try {
            MimeMessage m = new MimeMessage(mailSession);
            Address[] to = new InternetAddress[] {new InternetAddress(event.getTo())};
 
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(event.getSubject());
            m.setSentDate(new java.util.Date());
            m.setContent(event.getMessage(),"text/plain");
             
            Transport.send(m);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
   }
