/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modeles.Adherent;
import modeles.Oeuvre;
import modeles.Reservation;

/**
 *
 * @author Epulapp
 */
public abstract class ReservationDAO {

    public static void addReservation(Reservation reserv) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("insert into Reservation (date_reservation, id_oeuvre, id_adherent, statut) values (?, ?, ?, ?)");
            java.sql.Date dt = new java.sql.Date(reserv.getDate_reservation().getTime());

            ps.setDate(1, dt);
            ps.setInt(2, reserv.getId_oeuvre());
            ps.setInt(3, reserv.getId_adherent());
            ps.setString(4, reserv.getStatut());
            ps.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw e;
            }

        }
    }

    public static void confirmReservation(java.util.Date date, int idOeuvre) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("Update Reservation set statut = 'Confirmée' where date_reservation = ? and id_oeuvre = ?");
            java.sql.Date dt = new java.sql.Date(date.getTime());

            ps.setDate(1, dt);
            ps.setInt(2, idOeuvre);

            ps.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw e;
            }

        }
    }

    public static void deleteReservation(java.util.Date date, int idOeuvre) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("Delete from Reservation where date_reservation = ? and id_oeuvre = ?");
            java.sql.Date dt = new java.sql.Date(date.getTime());

            ps.setDate(1, dt);
            ps.setInt(2, idOeuvre);

            ps.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw e;
            }

        }
    }

    public static void getReservation(java.util.Date date, int idOeuvre) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);

            java.sql.Date dt = new java.sql.Date(date.getTime());
            ps = connection.prepareStatement("select * from Reservation where date_reservation = ? and id_oeuvre = ?");

            ps.setDate(1, dt);
            ps.setInt(2, idOeuvre);
            ps.executeQuery();

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw e;
            }

        }
    }

    public static ArrayList<Reservation> listerOeuvre() throws Exception {
        ArrayList<Reservation> lReserv = new ArrayList();
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Adherent adherent;
            Reservation reserv;
            Oeuvre oeu;
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from Reservation inner join Oeuvre on oeuvre.id_Oeuvre = Reservation.id_Oeuvre "
                    + "inner join Adherent on Reservation.id_adherent = adherent.id_adherent");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                adherent = new Adherent();
                oeu = new Oeuvre();
                adherent.setId_adherent(rs.getInt("Reservation.id_adherent"));
                adherent.setNom_adherent(rs.getString("nom_adherent"));
                adherent.setPrenom_adherent(rs.getString("prenom_adherent"));
                oeu.setId_oeuvre(rs.getInt("oeuvre.id_oeuvre"));
                oeu.setPrix(rs.getDouble("prix"));
                oeu.setTitre(rs.getString("titre"));
                reserv = new Reservation(oeu.getId_oeuvre(), adherent.getId_adherent());
                reserv.setAdherent(adherent);
                reserv.setOeuvre(oeu);
                reserv.setStatut(rs.getString("Reservation.statut"));
                reserv.setDate_reservation(rs.getDate("Reservation.date_reservation"));
                lReserv.add(reserv);
            }
            return lReserv;

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                throw e;
            }

        }
    }
}
