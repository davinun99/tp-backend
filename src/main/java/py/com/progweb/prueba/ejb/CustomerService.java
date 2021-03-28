package py.com.progweb.prueba.ejb;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class CustomerService{
    @Inject
    private EntityManager em;
     
    @Inject
    private Event<MailEvent> eventProducer;
     
    public void saveSuccess() {
        Customer c1 = new Customer();
        c1.setId(1L);
        c1.setName("John Doe");
        em.persist(c1);
         
        sendEmail();
    }
 
    private void sendEmail() {
        MailEvent event = new MailEvent();
        event.setTo("some.email@foo.com");
        event.setSubject("Async email testing");
        event.setMessage("Testing email");
 
        eventProducer.fire(event); //firing event!
    }
}