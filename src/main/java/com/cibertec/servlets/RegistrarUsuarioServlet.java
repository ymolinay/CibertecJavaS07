package com.cibertec.servlets;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.dao.impl.UsuarioDaoImpl;
import com.cibertec.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrarUsuario", urlPatterns = "/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {
    private final UsuarioDao usuarioDAO = new UsuarioDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        try {
            Usuario usuario = new Usuario(nombre, apellido, correo, clave);
            usuarioDAO.registrarUsuario(usuario);
            response.sendRedirect("usuarios");
        } catch (SQLException e) {
            throw new ServletException("Error al registrar usuario", e);
        }
    }
}
