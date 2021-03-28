package py.com.progweb.prueba.ejb;
@Named
@RequestScoped
public class CustomerAction{
    @Inject
    private CustomerService customerService;
    public void save(){
        customerService.saveSuccess();
    }

}