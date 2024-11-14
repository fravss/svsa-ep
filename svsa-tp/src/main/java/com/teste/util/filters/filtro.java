package com.teste.util.filters;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.teste.model.Usuario;

import lombok.extern.log4j.Log4j;

@Log4j
@WebFilter(urlPatterns = "/restrito/*") 
public class filtro implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); 


        Usuario user = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

        if (user == null) {
            log.info("Filtro: usuário nao está logado");
            res.sendRedirect("http://localhost:7750/svsa-ct/restricted/home/SvsaHome.xhtml");
        } else {
        	log.info("Filtro: usuário logado");
            chain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
