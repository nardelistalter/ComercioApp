package teste;

import br.upf.ads.paw.controladores.FactoryManager;
import br.upf.ads.paw.entidades.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author pavan
 */
public class ListaEstados {

    public static void main(String[] args) {
        EntityManager em;
        em = FactoryManager.getInstance().getEm();

        Query q = em.createQuery("from Estado e order by e.nome");
        List<Estado> estados = q.getResultList();

        for (Estado e : estados) {
            System.out.println(e.getId() + " - " + e.getNome() + " - " + e.getUf());
        }
        em.close();
    }
}
