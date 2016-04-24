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
import modeles.Adherent;

/**
 *
 * @author Epulapp
 */
public abstract class AdherentDAO {

    public static ArrayList<Adherent> getListAdherents() throws Exception {
        ArrayList<Adherent> lp = new ArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            Adherent adherent;
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select id_adherent, nom_adherent, prenom_adherent from adherent");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                adherent = new Adherent();
                adherent.setId_adherent(rs.getInt("id_adherent"));
                adherent.setNom_adherent(rs.getString("nom_adherent"));
                adherent.setPrenom_adherent(rs.getString("prenom_adherent"));
                lp.add(adherent);
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
    
    public static Adherent getAdherent(int id) throws Exception{
        Connection connection = null;
        PreparedStatement ps =  null;
        try {
            Adherent adherent = new Adherent();
            Connexion cnx = new Connexion();
            connection = cnx.connecter();
            ps = connection.prepareCall("select * from adherent where id_adherent = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {;
                adherent.setId_adherent(rs.getInt("id_adherent"));
                adherent.setNom_adherent(rs.getString("nom_adherent"));
                adherent.setPrenom_adherent(rs.getString("prenom_adherent"));
            }
            return adherent;

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
