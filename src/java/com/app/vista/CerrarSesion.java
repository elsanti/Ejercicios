/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.vista;

import com.app.utils.AppException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "CerrarSesion", urlPatterns = {"/cerrarSesion"})
public class CerrarSesion extends GenericoSrv{

    @Override
    public void procesarServlet(PrintWriter out, Connection cnn, String urlServlet, HttpServletRequest request, HttpServletResponse response) throws AppException, Exception {
        request.getSession().invalidate();
        response.sendRedirect("usuario/login");
    }
    
}
