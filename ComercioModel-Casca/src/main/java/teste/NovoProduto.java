package teste;

import br.upf.ads.paw.controladores.FactoryManager;
import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.CategoriaFuncional;
import br.upf.ads.paw.entidades.Categoria;
import br.upf.ads.paw.entidades.Estado;
import br.upf.ads.paw.entidades.Produto;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author pavan
 */
public class NovoProduto {

    public static void main(String[] args) {
        GenericDao<Categoria> daoC = new GenericDao<>(Categoria.class);
        GenericDao<Produto> daoP = new GenericDao<>(Produto.class);
        
        Categoria c; Produto p;
        
        c = (Categoria) JOptionPane.showInputDialog(null, "Escolha a Categoria", "Categoria", 
                JOptionPane.QUESTION_MESSAGE, null, daoC.findEntities().toArray(), null);
        p = new Produto(null, 
                JOptionPane.showInputDialog("descricao"), 
                JOptionPane.showInputDialog("unidade"), 
                Double.parseDouble(JOptionPane.showInputDialog("Estoque Atual")),
                Double.parseDouble(JOptionPane.showInputDialog("Estoque Mínimo")), 
                Double.parseDouble(JOptionPane.showInputDialog("Estoque Máximo")), 
                Double.parseDouble(JOptionPane.showInputDialog("Preço")),
                Double.parseDouble(JOptionPane.showInputDialog("Margem de lucro")),
                c, null);
        try {
            daoP.create(p);
        } catch (Exception ex) {
            Logger.getLogger(NovoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
