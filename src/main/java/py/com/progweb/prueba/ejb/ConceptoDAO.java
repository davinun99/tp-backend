package py.com.progweb.prueba.ejb;

import py.com.progweb.prueba.model.ConceptoPuntos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ConceptoDAO {
    @PersistenceContext(unitName="laboratiorioPersistanceUnit")
    private EntityManager em;
    public void add(ConceptoPuntos conceptoPuntos){
        em.persist(conceptoPuntos);
    }
    public ConceptoPuntos getById(Integer idConcepto){
        Query q = this.em.createQuery("select c from ConceptoPuntos c where c.idConcepto = :idConcepto");
        return (ConceptoPuntos) q.setParameter("idConcepto", idConcepto).getSingleResult();
    }
}
