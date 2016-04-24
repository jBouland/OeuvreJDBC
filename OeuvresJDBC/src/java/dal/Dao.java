/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Epulapp
 */
public class Dao {

    private PreparedStatement setParametres(PreparedStatement ps, Map mParam) throws Exception {
        String classe;
        for (Object indice : mParam.keySet()) {

        }

        return ps;
    }
    
    public int inc_parametre(Connection connection, String id_param) throws Exception{
        CallableStatement cs = null;
        try{
            cs = connection.prepareCall("{? = call inc_parametre(?)}");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, id_param);
            cs.execute();
            return cs.getInt(1);
        }catch(Exception e){
            throw e;
        }finally{
            try{
                if(cs != null){
                    cs.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
