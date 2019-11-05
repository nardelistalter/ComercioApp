package teste;

import br.upf.ads.paw.controladores.FactoryManager;
import br.upf.ads.paw.entidades.Estado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author pavan
 */
public class AlterarEstado {

    public static void main(String[] args) {
        EntityManager em;
        em = FactoryManager.getInstance().getEm();
        
        String s = JOptionPane.showInputDialog("Codigo:");
        Estado e = em.find(Estado.class, Long.parseLong(s));

        System.out.println(e.getId() + " " + e.getNome() + " " + e.getUf());

        s = JOptionPane.showInputDialog("Altere o nome:", e.getNome());
        e.setNome(s);
        s = JOptionPane.showInputDialog("Altere a UF:", e.getUf());
        e.setUf(s);

        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();

        em.close();
    }
}
