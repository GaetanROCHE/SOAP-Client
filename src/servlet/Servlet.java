package servlet;

import metier.Pays;
import requeteSOAP.EnvoiMessageSOAP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomasd on 29/10/16.
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

    private static EnvoiMessageSOAP unAppel = new EnvoiMessageSOAP();


    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        processusTraiteRequete(request, response);
    }

    public void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        RequestDispatcher dispatcher;
        if(action == null){
            unAppel.connexion();
            unAppel.creationMessageListePays();
            List<Pays> res;
            res = unAppel.emissionReceptionPays();
            request.setAttribute("list_pays", res);
            dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }else{
            switch(action){
                case "listerPays":
                    unAppel.connexion();
                    unAppel.creationMessageListePays();
                    List<Pays> res;
                    res = unAppel.emissionReceptionPays();
                    request.setAttribute("list_pays", res);
                    dispatcher = getServletContext().getRequestDispatcher("/list_pays.jsp");
                    dispatcher.forward(request, response);
                    break;


                case "detailsPays":
                    unAppel.connexion();
                    String paysName = request.getParameter("pays");
                    Pays resP;
                    resP = unAppel.getUnPays(paysName);
                    request.setAttribute("pays", resP);
                    dispatcher = getServletContext().getRequestDispatcher("/details_pays.jsp");
                    dispatcher.forward(request, response);
                    break;

                case "search":
                    unAppel.connexion();
                    unAppel.creationMessageListePays();
                    List<Pays> res2;
                    res2 = unAppel.emissionReceptionPays();
                    List<String> nomPays = new ArrayList<>();
                    for (Pays pays: res2)
                    {
                        nomPays.add(pays.getNomPays().replaceAll(" ",""));
                    }
                    request.setAttribute("nomPays", nomPays);
                    dispatcher = getServletContext().getRequestDispatcher("/searchPage.jsp");
                    dispatcher.forward(request, response);


                default:

                    break;

            }
        }


    }


}
