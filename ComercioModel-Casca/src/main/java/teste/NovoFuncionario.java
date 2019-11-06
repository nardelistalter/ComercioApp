package teste;

import br.upf.ads.paw.controladores.FactoryManager;
import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.CategoriaFuncional;
import br.upf.ads.paw.entidades.Cidade;
import br.upf.ads.paw.entidades.Estado;
import br.upf.ads.paw.entidades.Funcionario;
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
public class NovoFuncionario {

    public static void main(String[] args) {
        GenericDao<Cidade> daoC = new GenericDao<>(Cidade.class);
        GenericDao<CategoriaFuncional> daoCF = new GenericDao<>(CategoriaFuncional.class);
        GenericDao<Funcionario> daoF = new GenericDao<>(Funcionario.class);
        
        Cidade c; CategoriaFuncional cf; Funcionario f;
        
        c = (Cidade) JOptionPane.showInputDialog(null, "Escolha a Cidade", "Cidade", 
                JOptionPane.QUESTION_MESSAGE, null, daoC.findEntities().toArray(), null);
        cf = (CategoriaFuncional) JOptionPane.showInputDialog(null, 
                "Escolha a Categoria Funcional", "Categoria Funcional", 
                JOptionPane.QUESTION_MESSAGE, null, daoCF.findEntities().toArray(), null);
        f = new Funcionario(new Date(), null, "ctps est", 50542D, "laercio", "bolinhas", 
                cf, "444", "444", 'M', new Date("07/04/1971"), 
                null, "Rafael Pavan", "BR 285", "192", "Campus I", 
                "São José", "pavan@upf.br", "(54) 3316-8354", "99052-900", c);
        try {
            daoF.create(f);
        } catch (Exception ex) {
            Logger.getLogger(NovoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
