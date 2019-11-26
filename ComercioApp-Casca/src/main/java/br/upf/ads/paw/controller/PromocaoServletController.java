package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.Permissao;
import br.upf.ads.paw.entidades.Promocao;
import br.upf.ads.paw.entidades.Produto;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Anderson
 */
@WebServlet(name = "PromocaoServletController", urlPatterns = {"/promocao"})
public class PromocaoServletController extends HttpServlet {

    GenericDao<Promocao> daoPromocao = new GenericDao(Promocao.class);
    GenericDao<Produto> daoProduto = new GenericDao(Produto.class);

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

        Permissao p = Valida.acesso(req, resp, "Promocao");

        if (p == null) {
            RequestDispatcher dispatcher
                    = getServletContext().
                            getRequestDispatcher("/login?url=/promocao");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("permissao", p);
            String action = req.getParameter("search");
            if (action != null) {
                switch (action) {
                    case "searchById":
                        searchById(req, resp);
                        break;
                    case "search":
                        if (p.getConsultar()) {
                            search(req, resp);
                        } else {
                            req.setAttribute("message", "Você não tem permissão para consultar.");
                        }
                        forwardList(req, resp, null);
                        break;
                }
            } else {
                List<Promocao> result = null;
                if (p.getConsultar()) {
                    result = daoPromocao.findEntities();
                } else {
                    req.setAttribute("message", "Você não tem permissão para consultar.");
                }
                forwardList(req, resp, result);
            }
        }
    }

    private void searchById(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        Promocao obj = null;
        try {
            obj = daoPromocao.findEntity(id);
        } catch (Exception ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("obj", obj);
        req.setAttribute("listProduto", daoProduto.findEntities());
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/form-promocao.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        Query q = daoPromocao.getEntityManager().createQuery("FROM Promocao p WHERE p.produto.descricao LIKE '%" + search + "%'");
        List<Promocao> result = q.getResultList();  // buscar por nome
        forwardList(req, resp, result);
    }

    private void forwardList(HttpServletRequest req, HttpServletResponse resp, List entityList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-promocao.jsp";
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
        String nextJSP = "/jsp/form-promocao.jsp";
        List<Produto> list = daoProduto.findEntities();
        req.setAttribute("listProduto", list);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void addAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            long idProduto = Long.parseLong(req.getParameter("produto"));
            String inicio = req.getParameter("inicio");
            String fim = req.getParameter("fim");
            Double desconto = Double.parseDouble(req.getParameter("porcentualDesconto"));
            Boolean soFidelidade = req.getParameter("soFidelidade") != null;

            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = sf.parse(inicio);
            Date dataFim = sf.parse(fim);

            Promocao obj = new Promocao(null, dataInicio, dataFim, desconto, soFidelidade, daoProduto.findEntity(idProduto));
            daoPromocao.create(obj);
            long id = obj.getId();
            req.setAttribute("id", id);
            String message = "Um novo registro foi criado com sucesso.";
            req.setAttribute("message", message);
            doGet(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        String inicio = req.getParameter("inicio");
        String fim = req.getParameter("fim");
        Double desconto = Double.parseDouble(req.getParameter("porcentualDesconto"));
        Boolean soFidelidade = req.getParameter("soFidelidade") != null;
        long idProduto = Long.parseLong(req.getParameter("produto"));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        Date dataInicio = null;
        try {
            dataInicio = sf.parse(inicio);
        } catch (ParseException ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Date dataFim = null;
        try {
            dataFim = sf.parse(fim);
        } catch (ParseException ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Promocao obj = new Promocao(id, dataInicio, dataFim, desconto, soFidelidade, daoProduto.findEntity(idProduto));

        boolean success = false;
        try {
            daoPromocao.edit(obj);
            success = true;
        } catch (Exception ex) {
            Logger.getLogger(EstadoServletController.class.getName()).log(Level.SEVERE, null, ex);
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
            daoPromocao.destroy(id);
            confirm = true;
        } catch (Exception ex) {
            String message = "ERRO: Promocao sendo usada por outra entidade.";
            req.setAttribute("message", message);
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
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
