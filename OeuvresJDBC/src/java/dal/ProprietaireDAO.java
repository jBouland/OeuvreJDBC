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
import modeles.Proprietaire;

/**
 *
 * @author Epulapp
 */
public abstract class ProprietaireDAO {

    public static boolean connecter(String login, String pwd) throws Exception {
        Connexion cnx;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from proprietaire where login = ? and pwd = ?");
            ps.setString(1, login);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;

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

    public static Proprietaire getProprio(String login) throws Exception {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Proprietaire proprio = new Proprietaire();
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from proprietaire where login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proprio.setId_proprietaire(rs.getInt("id_proprietaire"));
                proprio.setNom_proprietaire(rs.getString("nom_proprietaire"));
                proprio.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
                proprio.setLogin(rs.getString("login"));
                return proprio;
            }

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
        return null;
    }

    public static Proprietaire getProprio(int id) throws Exception {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Proprietaire proprio = new Proprietaire();
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from proprietaire where id_proprietaire = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proprio.setId_proprietaire(rs.getInt("id_proprietaire"));
                proprio.setNom_proprietaire(rs.getString("nom_proprietaire"));
                proprio.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
                proprio.setLogin(rs.getString("login"));
                return proprio;
            }

        } catch (Exception e) {
            throw e;
        }  finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(Exception e){
                throw e;
            }

        }
        return null;
    }

    public static ArrayList<Proprietaire> getListProprio() throws Exception {
        ArrayList<Proprietaire> lp = new ArrayList();
        Connection connection = null;
        PreparedStatement ps= null;
        try {
            Proprietaire proprio;
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select id_proprietaire, nom_proprietaire, prenom_proprietaire from proprietaire");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                proprio = new Proprietaire();
                proprio.setId_proprietaire(rs.getInt("id_proprietaire"));
                proprio.setNom_proprietaire(rs.getString("nom_proprietaire"));
                proprio.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
                lp.add(proprio);
            }
            return lp;

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch(Exception e){
                throw e;
            }

        }
    }

}
