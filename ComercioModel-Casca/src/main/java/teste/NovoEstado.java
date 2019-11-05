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
public class NovoEstado {

    public static void main(String[] args) {
        EntityManager em;
        em = FactoryManager.getInstance().getEm();
        
        Estado e = new Estado(null,
                JOptionPane.showInputDialog("Nome do estado:"), 
                JOptionPane.showInputDialog("UF do estado:"));
        
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        
        System.out.println("ID: "+e.getId());
        System.out.println("Nome: "+e.getNome());
        System.out.println("UF: "+e.getUf());
        em.close();
    }    
}
