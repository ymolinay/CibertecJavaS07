package com.cibertec.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/usuarios", "/editarUsuario", "/eliminarUsuario", "/bienvenido.jsp", "/registrarUsuario.jsp", "/registroUsuario.jsp"})
public class SesionFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        // Verificar si hay una sesión activa
        if (session == null || session.getAttribute("usuario") == null) {
            httpResponse.sendRedirect("login.jsp");
        } else {
            // Continuar con la solicitud
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}