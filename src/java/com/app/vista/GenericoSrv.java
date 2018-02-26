/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vista;

import com.app.modelo.conexion.Conexion;
import com.app.utils.AppException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "GenericoSrv", urlPatterns = {"/GenericoSrv"})
public abstract class GenericoSrv extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String err = null;
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("usuario") == null && !request.getServletPath().equals("/usuario/login") && !request.getServletPath().equals("/usuario/crear")) {
                response.sendRedirect("usuario/login");
            } else if (sesion.getAttribute("usuario") != null && (request.getServletPath().equals("/usuario/login"))) {
                response.sendRedirect(request.getContextPath() + "/home");
            }
            err = request.getParameter("e");
            if (err != null) {
                out.print("<script type=\"text/javascript\">\n"
                        + "		alert(\"Error" +err+"\");\n"
                        + "	</script>");
                out.println("<h3>Error " + err + "</h3>");
                //request.getRequestDispatcher(request.getContextPath() + request.getServletPath() + "?e=" + err).forward(request,response);
                //response.sendRedirect(request.getContextPath() + request.getServletPath() + "?e=" + err);
                //return;
            }
            out.println("<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + "	<title></title>\n"
                    + "	<link rel=\"stylesheet\" type=\"text/css\" href=\"" + request.getContextPath() + "/css/estilos.css\">\n"
                    + "</head>\n"
                    + "<body>");
            
            Connection cnn = null;
            try {
                cnn = Conexion.getconexionBD();
                
                if (!request.getServletPath().equals("/usuario/login") && !request.getServletPath().equals("/usuario/crear")) {
                    out.println("<div>\n"
                            + "		<a href=\"#\">HOME</a>  \n"
                            + "		<a href=\"#\">P1</a>  \n"
                            + "		<a href=\"#\">P2</a>  \n"
                            + "		<a href=\"#\">P3</a>  \n"
                            + "		<a href=\"#\">P4</a>  \n"
                            + "		<a href=\"#\">P5</a>  \n"
                            + "		<a href=\"" + request.getContextPath() + "/cerrarSesion" + "\">Cerrar Sesion</a>  \n"
                            + "	</div>");
                }
                
                procesarServlet(out, cnn, request.getServletPath(), request, response);
                Conexion.cerrarCnn(cnn);
            } catch (AppException e) {
                //e.printStackTrace();
                //out.print("<script>alert(\"Error de Ejecucion\"" + e.getMessage() + ");</script>");
                err = e.getMessage().substring(e.getMessage().indexOf(" "));
                request.getRequestDispatcher(request.getServletPath() + "?e=" + err).forward(request, response);
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    Conexion.reversarCnn(cnn);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
            out.println("</body>\n"
                    + "</html>");
            
        }
    }
    
    public abstract void procesarServlet(PrintWriter out, Connection cnn, String urlServlet,
            HttpServletRequest request,
            HttpServletResponse response) throws AppException, Exception;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
