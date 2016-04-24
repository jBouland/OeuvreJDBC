/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import dal.AdherentDAO;
import dal.OeuvreDAO;
import dal.ReservationDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import outils.*;
import modeles.*;

/**
 *
 * @author alain
 */
public class slReservation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String erreur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String demande;
        String vueReponse = "/home.jsp";
        erreur = "";
        try {
            demande = getDemande(request);
            if (demande.equalsIgnoreCase("reserver.res")) {
                vueReponse = reserverOeuvre(request);
            } else if (demande.equalsIgnoreCase("enregistrerReservation.res")) {
                vueReponse = enregistrerReservation(request);
            } else if (demande.equalsIgnoreCase("listeReservations.res")) {
                vueReponse = listeReservations(request);
            } else if (demande.equalsIgnoreCase("confirmerReservation.res")) {
                vueReponse = confirmerReservation(request);
            } else if (demande.equalsIgnoreCase("supprimerReservation.res")) {
                vueReponse = supprimerReservation(request);
            }
        } catch (Exception e) {
            erreur = e.getMessage();
        } finally {
            request.setAttribute("erreurR", erreur);
            request.setAttribute("pageR", vueReponse);
            RequestDispatcher dsp = request.getRequestDispatcher("/index.jsp");
            if (vueReponse.contains(".res")) {
                dsp = request.getRequestDispatcher(vueReponse);
            }
            dsp.forward(request, response);
        }
    }

    /**
     * Transforme dans la base de données une réservation en Attente en une
     * réservation Confirmée
     *
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String confirmerReservation(HttpServletRequest request) throws Exception {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String date = request.getParameter("dateres");
            date = date.substring(1, date.length()-1);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dt = format.parse(date);

            ReservationDAO.confirmReservation(dt, id);
            return ("listeReservations.res");

        } catch (Exception e) {
            throw e;
        }
    }

    private String supprimerReservation(HttpServletRequest request) throws Exception {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String date = request.getParameter("dateres");
            date = date.substring(1, date.length()-1);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dt = format.parse(date);

            ReservationDAO.deleteReservation(dt, id);
            return ("listeReservations.res");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * liste des réservations en Attente
     *
     * @param request
     * @return String page de redirection
     * @throws Exception
     */
    private String listeReservations(HttpServletRequest request) throws Exception {

        try {
            ArrayList<Reservation> lReserv = ReservationDAO.listerOeuvre();
            request.setAttribute("lstReservationsR", lReserv);
            return ("/listereservations.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Enregistre une réservation et la met en Attente
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String enregistrerReservation(HttpServletRequest request) throws Exception {

        String titre = "";
        String date = "";
        try {
            int id = Integer.parseInt(request.getParameter("lstAdherents"));
            Oeuvre oeu = OeuvreDAO.getOeuvre(Integer.parseInt(request.getParameter("id")));
            Adherent adherent = AdherentDAO.getAdherent(id);
            titre = oeu.getTitre();
            date = request.getParameter("txtDate");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
            Date dt = format.parse(date);

            Reservation r = new Reservation(oeu.getId_oeuvre(), adherent.getId_adherent());
            r.setDate_reservation(dt);
            r.setStatut("Attente");

            ArrayList<Reservation> lReserv = ReservationDAO.listerOeuvre();
            request.setAttribute("lstReservationsR", lReserv);
            ReservationDAO.addReservation(r);
            return ("listeReservations.res");
        } catch (Exception e) {
            erreur = e.getMessage();
            if (erreur.contains("PRIMARY")) {
                erreur = "L'oeuvre " + titre + " a déjà été réservée pour le : " + date + " !";
            }
            throw new Exception(erreur);
        }
    }

    /**
     * Lit une oeuvre, l'affiche et initialise la liste des adhérents pour
     * pouvoir saisir une réservation : Saisie date et sélection de l'adhérent
     *
     * @param request
     * @return
     * @throws Exception
     */
    private String reserverOeuvre(HttpServletRequest request) throws Exception {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Oeuvre oeuvre = OeuvreDAO.getOeuvre(id);
            ArrayList<Adherent> lstAdherentsR = AdherentDAO.getListAdherents();

            request.setAttribute("lstAdherentsR", lstAdherentsR);
            request.setAttribute("oeuvreR", oeuvre);
            return ("/reservation.jsp");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Extrait le texte de la demande de l'URL
     *
     * @param request
     * @return String texte de la demande
     */
    private String getDemande(HttpServletRequest request) {
        String demande = "";
        demande = request.getRequestURI();
        demande = demande.substring(demande.lastIndexOf("/") + 1);
        return demande;
    }

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
