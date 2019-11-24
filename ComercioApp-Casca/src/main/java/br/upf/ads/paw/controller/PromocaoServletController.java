/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.Promocao;
import br.upf.ads.paw.entidades.Produto;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if (Valida.acesso(req, resp, "Promocao")==null) {
            RequestDispatcher dispatcher
                    = getServletContext().
                            getRequestDispatcher("/login?url=/promocao");
            dispatcher.forward(req, resp);
        } else {
            String action = req.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "searchById":
                        searchById(req, resp);
                        break;
                    case "search":
                        search(req, resp);
                        break;
                }
            } else {
                List<Promocao> result = daoPromocao.findEntities();
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

    private void search(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String search = req.getParameter("search");
        List<Promocao> result = daoPromocao.findEntitiesByField("nome", search);  // buscar por nome
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
            String nome = req.getParameter("nome");
            long idProduto = Long.parseLong(req.getParameter("produto"));
                    
            
            Promocao obj = new Promocao(null, new Date(), new Date(), Double.POSITIVE_INFINITY,false,new Produto());
            daoPromocao.create(obj);
            long id = obj.getId();
            List<Promocao> objList = daoPromocao.findEntities();
            req.setAttribute("id", id);
            String message = "Um novo registro foi criado com sucesso.";
            req.setAttribute("message", message);
            forwardList(req, resp, objList);
        } catch (Exception ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        String nome = req.getParameter("nome");
        long idProduto = Long.parseLong(req.getParameter("produto"));

        Promocao obj = new Promocao(null, new Date(), new Date(), Double.POSITIVE_INFINITY,false,new Produto());
        boolean success = false;
        try {
            daoPromocao.edit(obj);
            success = true;
        } catch (Exception ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message = null;
        if (success) {
            message = "O registro foi atualizado com sucesso";
        }
        List<Promocao> objList = daoPromocao.findEntities();
        req.setAttribute("id", obj.getId());
        req.setAttribute("message", message);
        forwardList(req, resp, objList);
    }

    private void removeById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        boolean confirm = false;
        try {
            daoPromocao.destroy(id);
            confirm = true;
        } catch (Exception ex) {
            Logger.getLogger(PromocaoServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (confirm) {
            String message = "O registro foi removido com sucesso.";
            req.setAttribute("message", message);
        }
        List<Promocao> objList = daoPromocao.findEntities();
        forwardList(req, resp, objList);
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
