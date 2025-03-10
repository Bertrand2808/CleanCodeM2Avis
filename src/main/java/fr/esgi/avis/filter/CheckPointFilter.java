package fr.esgi.avis.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CheckPointFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // On enrichit l'objet request en ajoutant un attribut dateHeureDebut
        Date dateHeureDebut = new Date();
        request.setAttribute("dateHeureDebut", dateHeureDebut.getTime());
        chain.doFilter(request, response);
    }

}

