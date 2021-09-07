/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.cart;

import anpdt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class CartDAO implements Serializable{
    public boolean checkOut(String name, int quantity, int price)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql ="insert into OrderDetail (name,quantity,price) "
                        +"values(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setInt(2, quantity);
                stm.setInt(3, price);
                int row = stm.executeUpdate();
                if(row>0){
                    return true;
                }
            }
        }finally{
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }

        }
        return false;
    }
}
