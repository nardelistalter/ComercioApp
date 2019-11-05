/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.CategoriaFuncional;
import java.io.IOException;
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
 * @author pavan
 */
@WebServlet(name = "CategoriaFuncionalServletController", urlPatterns = {"/categoriaFuncional"})
public class CategoriaFuncionalServletController extends HttpServlet {

    GenericDao<CategoriaFuncional> dao = new GenericDao(CategoriaFuncional.class);

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
        String action = req.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    searchById(req, resp);
                    break;
                case "searchByName":
                    searchByName(req, resp);
                    break;
            }
        } else {
            List<CategoriaFuncional> result = dao.findEntities();
            forwardList(req, resp, result);
        }
    }

    private void searchById(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        CategoriaFuncional obj = null;
        try {
            obj = dao.findEntity(id);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaFuncionalServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        req.setAttribute("obj", obj);
        req.setAttribute("action", "edit");
        String nextJSP = "/jsp/form-categoriaFuncional.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(req, resp);
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String search = req.getParameter("search");
        List<CategoriaFuncional> result = dao.findEntitiesByField("descricao", search);  // buscar por nome
        forwardList(req, resp, result);
    }

    private void forwardList(HttpServletRequest req, HttpServletResponse resp, List entityList)
            throws ServletException, IOException {
        String nextJSP = "/jsp/list-categoriaFuncional.jsp";
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

    private void addAction(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String descricao = req.getParameter("descricao");

            CategoriaFuncional obj = new CategoriaFuncional(null, descricao);
            dao.create(obj);
            long id = obj.getId();
            List<CategoriaFuncional> objList = dao.findEntities();
            req.setAttribute("id", id);
            String message = "Um novo registro foi criado com sucesso.";
            req.setAttribute("message", message);
            forwardList(req, resp, objList);
        } catch (Exception ex) {
            Logger.getLogger(CategoriaFuncionalServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String descricao = req.getParameter("descricao");
        long id = Integer.valueOf(req.getParameter("id"));
        CategoriaFuncional obj = new CategoriaFuncional(id, descricao);
        boolean success = false;
        try {
            dao.edit(obj);
            success = true;
        } catch (Exception ex) {
            Logger.getLogger(CategoriaFuncionalServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String message = null;
        if (success) {
            message = "O registro foi atualizado com sucesso";
        }
        List<CategoriaFuncional> objList = dao.findEntities();
        req.setAttribute("id", obj.getId());
        req.setAttribute("message", message);
        forwardList(req, resp, objList);
    }

    private void removeById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long id = Integer.valueOf(req.getParameter("id"));
        boolean confirm = false;
        try {
            dao.destroy(id);
            confirm = true;
        } catch (Exception ex) {
            Logger.getLogger(CategoriaFuncionalServletController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (confirm) {
            String message = "O registro foi removido com sucesso.";
            req.setAttribute("message", message);
        }
        List<CategoriaFuncional> objList = dao.findEntities();
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
