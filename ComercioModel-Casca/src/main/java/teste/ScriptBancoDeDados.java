/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.Categoria;
import br.upf.ads.paw.entidades.CategoriaFuncional;
import br.upf.ads.paw.entidades.Cidade;
import br.upf.ads.paw.entidades.Estado;
import br.upf.ads.paw.entidades.FormaPagamento;
import br.upf.ads.paw.entidades.Funcionario;
import br.upf.ads.paw.entidades.Permissao;
import br.upf.ads.paw.entidades.Produto;
import br.upf.ads.paw.entidades.Programa;
import br.upf.ads.paw.entidades.Promocao;
import java.util.Date;

/**
 *
 * @author Mior
 */
public class ScriptBancoDeDados {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        GenericDao<Estado> estadoDAO = new GenericDao<>(Estado.class);
        Estado estado1 = new Estado(null, "Rio Grande do Sul", "RS");
        Estado estado2 = new Estado(null, "Santa Catarina", "SC");
        Estado estado3 = new Estado(null, "São Paulo", "SP");
        estadoDAO.create(estado1);
        estadoDAO.create(estado2);
        estadoDAO.create(estado3);

        GenericDao<Cidade> cidadeDAO = new GenericDao<>(Cidade.class);
        Cidade cidade1 = new Cidade(null, "Serafina Corrêa", estado1);
        Cidade cidade2 = new Cidade(null, "Guaporé", estado1);
        Cidade cidade3 = new Cidade(null, "Casca", estado1);
        Cidade cidade4 = new Cidade(null, "Florianópolis", estado2);
        Cidade cidade5 = new Cidade(null, "Chapecó", estado2);
        cidadeDAO.create(cidade1);
        cidadeDAO.create(cidade2);
        cidadeDAO.create(cidade3);
        cidadeDAO.create(cidade4);
        cidadeDAO.create(cidade5);

        GenericDao<Categoria> categoriaDAO = new GenericDao<>(Categoria.class);
        Categoria categoria1 = new Categoria(null, "Bebidas");
        Categoria categoria2 = new Categoria(null, "Carnes");
        Categoria categoria3 = new Categoria(null, "Verduras");
        Categoria categoria4 = new Categoria(null, "Frutas");
        Categoria categoria5 = new Categoria(null, "Limpeza");
        categoriaDAO.create(categoria1);
        categoriaDAO.create(categoria2);
        categoriaDAO.create(categoria3);
        categoriaDAO.create(categoria4);
        categoriaDAO.create(categoria5);

        GenericDao<CategoriaFuncional> categoriaFuncionalDAO = new GenericDao<>(CategoriaFuncional.class);
        CategoriaFuncional categoriaFuncional1 = new CategoriaFuncional(null, "Gerente");
        CategoriaFuncional categoriaFuncional2 = new CategoriaFuncional(null, "Atendente");
        CategoriaFuncional categoriaFuncional3 = new CategoriaFuncional(null, "Fachineira");
        categoriaFuncionalDAO.create(categoriaFuncional1);
        categoriaFuncionalDAO.create(categoriaFuncional2);
        categoriaFuncionalDAO.create(categoriaFuncional3);

        GenericDao<FormaPagamento> formaPagamentoDAO = new GenericDao<>(FormaPagamento.class);
        FormaPagamento formaPagamento1 = new FormaPagamento(null, "Dinheiro");
        FormaPagamento formaPagamento2 = new FormaPagamento(null, "Cartão de Crédito");
        FormaPagamento formaPagamento3 = new FormaPagamento(null, "Cheque");
        formaPagamentoDAO.create(formaPagamento1);
        formaPagamentoDAO.create(formaPagamento2);
        formaPagamentoDAO.create(formaPagamento3);

        GenericDao<Funcionario> funcionarioDAO = new GenericDao<>(Funcionario.class);
        Funcionario funcionario1 = new Funcionario(new Date(), null, "260.54206.42-5", 31254.00, "admin", "admin", categoriaFuncional1, "157.337.580-23", "123456789", 'M', new Date(), null, "Eduardo Silva", "Rua Do Fransisco", "512", "Casa branca", "Bairro da Penha", "dudu@hotmail.com", "54 991223466", "99250-000", cidade1);
        Funcionario funcionario2 = new Funcionario(new Date(), null, "398.41898.53-2", 954.00, "root", "root", categoriaFuncional1, "215.692.030-32", "333456789", 'M', new Date(), null, "Roger Freitas", "Avenina Joséca", "123", "Apartamento 5", "Centro", "roger@hotmail.com", "54 944231266", "99250-000", cidade1);
        Funcionario funcionario3 = new Funcionario(new Date(), null, "739.02324.14-0", 1054.00, "bolinhas", "bolinhas", categoriaFuncional1, "942.547.780-90", "993451289", 'M', new Date(), null, "Anderson Leaks", "Rua Capes Baixo", "599", "", "Bairro Silva e Penha", "anderson@hotmail.com", "54 934931211", "99200-000", cidade2);
        funcionarioDAO.create(funcionario1);
        funcionarioDAO.create(funcionario2);
        funcionarioDAO.create(funcionario3);
        
        GenericDao<Programa> programaDAO = new GenericDao<>(Programa.class);
        Programa programa1 = new Programa(null, "Categoria");
        Programa programa2 = new Programa(null, "CategoriaFuncional");
        Programa programa3 = new Programa(null, "Cidade");
        Programa programa4 = new Programa(null, "Estado");
        Programa programa5 = new Programa(null, "FormaPagamento");
        Programa programa6 = new Programa(null, "Funcionario");
        Programa programa7 = new Programa(null, "Permissao");
        Programa programa8 = new Programa(null, "Programa");
        Programa programa9 = new Programa(null, "Promocao");
        Programa programa10 = new Programa(null, "Produto");
        Programa programa11 = new Programa(null, "Pagamento");
        programaDAO.create(programa1);
        programaDAO.create(programa2);
        programaDAO.create(programa3);
        programaDAO.create(programa4);
        programaDAO.create(programa5);
        programaDAO.create(programa6);
        programaDAO.create(programa7);
        programaDAO.create(programa8);
        programaDAO.create(programa9);
        programaDAO.create(programa10);
        programaDAO.create(programa11);

        GenericDao<Permissao> permissaoDAO = new GenericDao<>(Permissao.class);
        Permissao permissao1 = new Permissao(null, true, true, true, true, programa1, categoriaFuncional1);
        Permissao permissao2 = new Permissao(null, true, true, true, true, programa2, categoriaFuncional1);
        Permissao permissao3 = new Permissao(null, true, true, true, true, programa3, categoriaFuncional1);
        Permissao permissao4 = new Permissao(null, true, true, true, true, programa4, categoriaFuncional1);
        Permissao permissao5 = new Permissao(null, true, true, true, true, programa5, categoriaFuncional1);
        Permissao permissao6 = new Permissao(null, true, true, true, true, programa6, categoriaFuncional1);
        Permissao permissao7 = new Permissao(null, true, true, true, true, programa7, categoriaFuncional1);
        Permissao permissao8 = new Permissao(null, true, true, true, true, programa8, categoriaFuncional1);
        Permissao permissao9 = new Permissao(null, true, true, true, true, programa9, categoriaFuncional1);
        Permissao permissao10 = new Permissao(null, true, true, true, true, programa10, categoriaFuncional1);
        Permissao permissao11 = new Permissao(null, true, true, true, true, programa11, categoriaFuncional1);
        permissaoDAO.create(permissao1);
        permissaoDAO.create(permissao2);
        permissaoDAO.create(permissao3);
        permissaoDAO.create(permissao4);
        permissaoDAO.create(permissao5);
        permissaoDAO.create(permissao6);
        permissaoDAO.create(permissao7);
        permissaoDAO.create(permissao8);
        permissaoDAO.create(permissao9);
        permissaoDAO.create(permissao10);
        permissaoDAO.create(permissao11);
        
        GenericDao<Produto> produtoDAO = new GenericDao<>(Produto.class);
        Produto produto1 = new Produto(null, "Coca Cola", "L", 20D, 0D, 100D, 5.4, 0.4, categoria1, null);
        Produto produto2 = new Produto(null, "Bife De Frango", "K", 30D, 1D, 100D, 6.6, 0.2, categoria2, null);
        Produto produto3 = new Produto(null, "Alface", "K", 50D, 0D, 100D, 1.4, 1.4, categoria3, null);
        produtoDAO.create(produto1);
        produtoDAO.create(produto2);
        produtoDAO.create(produto3);
        
        GenericDao<Promocao> promocaoDAO = new GenericDao<>(Promocao.class);
        Promocao promocao1 = new Promocao(null, new Date(), new Date(), 10D, false, produto1);
        Promocao promocao2 = new Promocao(null, new Date(), new Date(), 10D, false, produto2);
        Promocao promocao3 = new Promocao(null, new Date(), new Date(), 10D, false, produto3);
        promocaoDAO.create(promocao1);
        promocaoDAO.create(promocao2);
        promocaoDAO.create(promocao3);
    }
    
}