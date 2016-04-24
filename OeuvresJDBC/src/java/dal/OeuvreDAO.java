/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modeles.Oeuvre;
import modeles.Proprietaire;

/**
 *
 * @author Epulapp
 */
public abstract class OeuvreDAO {

    public static void ajouter_Oeuvre(Oeuvre oeuvre) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        Db_outils db_outils;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);
            db_outils = new Db_outils();
            oeuvre.setId_oeuvre(db_outils.getIdentifiant(connection, "OEUVRE"));

            ps = connection.prepareStatement("insert into oeuvre (id_oeuvre, id_proprietaire, titre, prix) values (?, ?, ?, ?)");
            ps.setInt(1, oeuvre.getId_oeuvre());
            ps.setInt(2, oeuvre.getId_proprietaire());
            ps.setString(3, oeuvre.getTitre());
            ps.setDouble(4, oeuvre.getPrix());
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

    public static void update_Oeuvre(Oeuvre oeuvre) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("update oeuvre set Id_proprietaire = ?, Titre = ?, Prix = ? where id_oeuvre = ?");
            ps.setInt(1, oeuvre.getId_proprietaire());
            ps.setString(2, oeuvre.getTitre());
            ps.setDouble(3, oeuvre.getPrix());
            ps.setInt(4, oeuvre.getId_oeuvre());
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

    public static void delete_Oeuvre(int id) throws Exception {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement("delete from oeuvre where id_oeuvre = ?");
            ps.setInt(1, id);
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

    public static Oeuvre getOeuvre(int id) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Oeuvre oeu = new Oeuvre();
            Proprietaire proprio;
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from oeuvre inner join proprietaire on oeuvre.id_proprietaire = proprietaire.id_proprietaire where id_oeuvre = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proprio = new Proprietaire();
                oeu = new Oeuvre();
                proprio.setId_proprietaire(rs.getInt("oeuvre.id_proprietaire"));
                proprio.setNom_proprietaire(rs.getString("nom_proprietaire"));
                proprio.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
                oeu.setProprietaire(proprio);
                oeu.setId_proprietaire(proprio.getId_proprietaire());
                oeu.setId_oeuvre(rs.getInt("oeuvre.id_proprietaire"));
                oeu.setPrix(rs.getDouble("prix"));
                oeu.setTitre(rs.getString("titre"));
            }
            return oeu;
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

    public static ArrayList<Oeuvre> listerOeuvre() throws Exception {
        ArrayList<Oeuvre> lOeuvre = new ArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Proprietaire proprio;
            Oeuvre oeu;
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from oeuvre inner join proprietaire on oeuvre.id_proprietaire = proprietaire.id_proprietaire");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proprio = new Proprietaire();
                oeu = new Oeuvre();
                proprio.setId_proprietaire(rs.getInt("oeuvre.id_proprietaire"));
                proprio.setNom_proprietaire(rs.getString("nom_proprietaire"));
                proprio.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
                oeu.setProprietaire(proprio);
                oeu.setId_proprietaire(proprio.getId_proprietaire());
                oeu.setId_oeuvre(rs.getInt("oeuvre.id_oeuvre"));
                oeu.setPrix(rs.getDouble("prix"));
                oeu.setTitre(rs.getString("titre"));
                lOeuvre.add(oeu);
            }
            return lOeuvre;

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
