package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.CartaoFidelidade;
import br.upf.ads.paw.entidades.Movimento;
import br.upf.ads.paw.entidades.Permissao;
import br.upf.ads.paw.entidades.Pessoa;
;import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleListProperty;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pavan
 */
@WebServlet(name = "CartaoFidelidadeServletController", urlPatterns = {"/cartaoFidelidade"})
public class CartaoFidelidadeServletController extends HttpServlet {

    GenericDao<CartaoFidelidade> daoCartaoFidelidade = new GenericDao(CartaoFidelidade.class);
    GenericDao<Pessoa> daoPessoa = new GenericDao(Pessoa.class);

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        Permissao p = Valida.acesso(req, resp, "CartaoFidelidade");

        if (p == null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login?url=/cartaoFidelidade");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("permissao", p);
            String action = req.getParameter("searchAction");
            if (action != null) {
                switch (action) {
                    case "searchById":
                        searchById(req, resp);
                        break;
                    case "searchByName":
                        if (p.getConsultar()) {
                            searchByName(req, resp);
                        } else {
                            req.setAttribute("message", "Voc� n�o tem permiss�o para consultar.");
                        }
                        forwardList(req, resp, null);
                }
            } else {
                List<CartaoFidelidade> result = null;
                if (p.getConsultar()) {
                    result = daoCartaoFidelidade.findEntities();
                } else {
                    req.setAttribute("message", "Voc� n�o tem permiss�o para consulrar");
                }
                forwardList(req, resp, result);
            }
        }
    }

    private void searchById(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        CartaoFidelidade obj = null;
        try {
            obj = daoCartaoFidelidade.findEntity(id);
        } catch (Exception ex) {
            Logger.getLogger(CartaoFidelidadeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("obj", obj);
        req.setAttribute("listPessoa", daoPessoa.findEntities());
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/form-cartaoFidelidade.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String search = req.getParameter("search");
        List<CartaoFidelidade> result = daoCartaoFidelidade.findEntitiesByField("descricao", search);  // buscar por nome
        forwardList(req, resp, result);
    }

    private void forwardList(HttpServletRequest req, HttpServletResponse resp, List entityList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-cartaoFidelidade.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("entities", entityList);
        dispatcher.forward(req, resp);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            doGet(req, resp);
        }
        switch (action) {
            case "new":
                newAction(req, resp);
                break;
            case "add":
                addAction(req, resp);
                break;
            case "edit":
                editAction(req, resp);
                break;
            case "remove":
                removeById(req, resp);
                break;
        }
    }
    
    private void newAction(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        String nextJSP = "/jsp/form-cartaoFidelidade.jsp";
        List<Pessoa> list = daoPessoa.findEntities();
        req.setAttribute("listPessoa", list);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void addAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Double vencimento = Double.parseDouble(req.getParameter("vencimento"));
            Double limite = Double.parseDouble(req.getParameter("limite"));
            Double fatorConversao = Double.parseDouble(req.getParameter("fatorConversao"));
            Double qtdPontos = Double.parseDouble(req.getParameter("qtdPontos"));
            Double senha = Double.parseDouble(req.getParameter("senha"));
            Pessoa cliente = daoPessoa.findEntity(Long.parseLong(req.getParameter("cliente")));
            List<Movimento> movimento = new SimpleListProperty<>();

            CartaoFidelidade obj = new CartaoFidelidade(null, vencimento, limite, qtdPontos, fatorConversao, senha, cliente, (ArrayList<Movimento>) movimento);
            
            daoCartaoFidelidade.create(obj);
            long id = obj.getId();
            req.setAttribute("id", id);
            String message = "Um novo registro foi criado com sucesso.";
            req.setAttribute("message", message);
            doGet(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(CartaoFidelidadeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        Double vencimento = Double.parseDouble(req.getParameter("vencimento"));
        Double limite = Double.parseDouble(req.getParameter("limite"));
        Double fatorConversao = Double.parseDouble(req.getParameter("fatorConversao"));
        Double qtdPontos = Double.parseDouble(req.getParameter("qtdPontos"));
        Double senha = Double.parseDouble(req.getParameter("senha"));
        Pessoa cliente = daoPessoa.findEntity(Long.parseLong(req.getParameter("cliente")));
        List<Movimento> movimento = new SimpleListProperty<>();
        
        CartaoFidelidade obj = new CartaoFidelidade(id, vencimento, limite, qtdPontos, fatorConversao, senha, cliente, (ArrayList<Movimento>) movimento);
        boolean success = false;
        try {
            daoCartaoFidelidade.edit(obj);
            success = true;
        } catch (Exception ex) {
            Logger.getLogger(CartaoFidelidadeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message = null;
        if (success) {
            message = "O registro foi atualizado com sucesso";
        }
        req.setAttribute("id", obj.getId());
        req.setAttribute("message", message);
        doGet(req, resp);
    }

    private void removeById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        boolean confirm = false;
        try {
            daoCartaoFidelidade.destroy(id);
            confirm = true;
        } catch (Exception ex) {
            Logger.getLogger(CartaoFidelidadeServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (confirm) {
            String message = "O registro foi removido com sucesso.";
            req.setAttribute("message", message);
        }
        doGet(req, resp);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
