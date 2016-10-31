package servlet;

import metier.Pays;
import requeteSOAP.EnvoiMessageSOAP;

import javax.servlet.RequestDispatcher;
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
        unAppel.connexion();
        unAppel.creationMessageListePays();

        String paysName = request.getParameter("pays");
        if(paysName != null){
            try {
                Pays resP;
                resP = unAppel.getUnPays(paysName);
                request.setAttribute("pays", resP);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/details_pays.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }else {
            try {
                List<Pays> res;
                res = unAppel.emissionReceptionPays();
                request.setAttribute("list_pays", res);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
