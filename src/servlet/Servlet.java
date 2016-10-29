package servlet;

import metier.Pays;
import requeteSOAP.EnvoiMessageSOAP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomasd on 29/10/16.
 */
public class Servlet extends HttpServlet {

    private static EnvoiMessageSOAP unAppel = new EnvoiMessageSOAP();


    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        List<Pays> res = new ArrayList<>();
        try {
            unAppel.connexion();
            unAppel.creationMessageListePays();
            res = unAppel.emissionReceptionPays();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.setAttribute("list_pays", res);
        this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
    }


    }
