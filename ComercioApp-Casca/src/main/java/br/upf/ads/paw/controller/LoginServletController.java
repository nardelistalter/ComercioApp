package br.upf.ads.paw.controller;

import br.upf.ads.paw.controladores.GenericDao;
import br.upf.ads.paw.entidades.Funcionario;
import java.io.IOException;
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
@WebServlet(name = "LoginServletController", urlPatterns = {"/login"})
public class LoginServletController extends HttpServlet {

    GenericDao<Funcionario> dao = new GenericDao(Funcionario.class);

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
        //req.getSession().removeAttribute("func");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/form-login.jsp?url=" + req.getParameter("url"));
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
        req.getSession().removeAttribute("func");
        String url = req.getParameter("url");
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        String forward = "/jsp/form-login.jsp?url=" + url;
        if (login != null && senha != null) {
            String select = "FROM Funcionario f WHERE f.login=:login and f.senha=:senha";
            Query q = dao.getEntityManager().createQuery(select);
            q.setParameter("login", login);
            q.setParameter("senha", senha);
            try {
                Funcionario f = (Funcionario) q.getSingleResult();
                req.getSession().setAttribute("func", f);
                forward = url;
            } catch (Exception e) {
                req.setAttribute("message", "Usuário/Senha Inválida.");
            }
        } else {
            req.setAttribute("message", "Você precisa fazer login para acessar este serviço.");
        }
        System.out.println("forward: " + forward);
        resp.sendRedirect(forward);
//        RequestDispatcher dispatcher = getServletContext().
//                getRequestDispatcher(forward);
//      
//        dispatcher.forward(req, resp);
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
