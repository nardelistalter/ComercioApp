package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.CategoriaFuncional;
import br.upf.ads.paw.entidades.Permissao;
import br.upf.ads.paw.entidades.Programa;
import java.io.IOException;
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
 * @author pavan
 */
@WebServlet(name = "PermissaoServletController", urlPatterns = {"/permissao"})
public class PermissaoServletController extends HttpServlet {

    GenericDao<Permissao> daoPermissao = new GenericDao(Permissao.class);
    GenericDao<Programa> daoPrograma = new GenericDao(Programa.class);
    GenericDao<CategoriaFuncional> daoCategoriaFuncional = new GenericDao(CategoriaFuncional.class);

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

        Permissao p = Valida.acesso(req, resp, "Permissao");

        if (p == null) {
            req.setAttribute("message", "Acesso negado. Tente fazer login.");
            RequestDispatcher dispatcher
                    = getServletContext().
                            getRequestDispatcher("/login?url=/permissao");
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
                            search(req, resp);
                        } else {
                            req.setAttribute("message", "Você não tem permissão para consultar.");
                        }
                        forwardList(req, resp, null);
                        break;
                }
            } else {
                List<Permissao> result = null;
                if (p.getConsultar()) {
                    result = daoPermissao.findEntities();
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
        Permissao obj = null;
        try {
            obj = daoPermissao.findEntity(id);
        } catch (Exception ex) {
            Logger.getLogger(PermissaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("obj", obj);
        req.setAttribute("listPrograma", daoPrograma.findEntities());
        req.setAttribute("listCategoriaFuncional", daoCategoriaFuncional.findEntities());
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/form-permissao.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        Query q = daoPermissao.getEntityManager().createQuery("FROM Permissao p WHERE p.programa.nome LIKE '%" + search + "%' OR p.categoriaFuncional.descricao LIKE '%" + search + "%'");
        List<Permissao> result = q.getResultList();  // buscar por nome
        forwardList(req, resp, result);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void forwardList(HttpServletRequest req, HttpServletResponse resp, List entityList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-permissao.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("entities", entityList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
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
        String nextJSP = "/jsp/form-permissao.jsp";
        req.setAttribute("listPrograma", daoPrograma.findEntities());
        req.setAttribute("listCategoriaFuncional", daoCategoriaFuncional.findEntities());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void addAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            long idPrograma = Long.parseLong(req.getParameter("programa"));
            long idCFuncional = Long.parseLong(req.getParameter("categoriaFuncional"));

            Boolean alterar = req.getParameter("alterar") != null;
            Boolean criar = req.getParameter("criar") != null;
            Boolean excluir = req.getParameter("excluir") != null;
            Boolean consultar = req.getParameter("consultar") != null;
            //Se Alterar e ou Excluir forem permitidos, automaticamente o usuário deverá ter acesso a Consulta
            if (alterar || excluir) {
                consultar = true;
            }

            Permissao obj = new Permissao(null, alterar, criar, excluir, consultar, daoPrograma.findEntity(idPrograma), daoCategoriaFuncional.findEntity(idCFuncional));
            daoPermissao.create(obj);
            long id = obj.getId();
            req.setAttribute("id", id);
            String message = "Um novo registro foi criado com sucesso.";
            req.setAttribute("message", message);
            doGet(req, resp);
        } catch (Exception ex) {
            Logger.getLogger(PermissaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        long idPrograma = Long.parseLong(req.getParameter("programa"));
        long idCFuncional = Long.parseLong(req.getParameter("categoriaFuncional"));

        Boolean alterar = req.getParameter("alterar") != null;
        Boolean criar = req.getParameter("criar") != null;
        Boolean excluir = req.getParameter("excluir") != null;
        Boolean consultar = req.getParameter("consultar") != null;
        //Se Alterar e ou Excluir forem permitidos, automaticamente o usuário deverá ter acesso a Consulta
        if (alterar || excluir) {
            consultar = true;
        }

        Permissao obj = new Permissao(id, alterar, criar, excluir, consultar, daoPrograma.findEntity(idPrograma), daoCategoriaFuncional.findEntity(idCFuncional));
        boolean success = false;
        try {
            daoPermissao.edit(obj);
            success = true;
        } catch (Exception ex) {
            Logger.getLogger(PermissaoServletController.class.getName()).log(Level.SEVERE, null, ex);
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
            daoPermissao.destroy(id);
            confirm = true;
        } catch (Exception ex) {
            Logger.getLogger(PermissaoServletController.class.getName()).log(Level.SEVERE, null, ex);
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
