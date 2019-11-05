package teste;

import br.upf.ads.paw.controladores.FactoryManager;
import br.upf.ads.paw.entidades.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author pavan
 */
public class TesteJPA {

    public static void main(String[] args) {
        EntityManager em;
        em = FactoryManager.getInstance().getEm();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Estado> cq = cb.createQuery(Estado.class);
        Root<Estado> tag = cq.from(Estado.class);
        cq.where(cb.like(tag.get("nome"), "%Rio%"));

        Query q = em.createQuery(cq);
        List<Estado> estados = q.getResultList();
        for (Estado e : estados) {
            System.out.println(e);
        }
    }
}
