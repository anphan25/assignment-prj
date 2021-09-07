/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.products;

import anpdt.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class ProductsDAO implements Serializable{
    
    private ArrayList<ProductsDTO> productsList;
    public ArrayList<ProductsDTO> getProductsList(){
        return productsList;
    } 
    
        public void loadProducst() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select name, price from Products";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    ProductsDTO dto = new ProductsDTO(name, price);
                    if(productsList == null){
                        productsList = new ArrayList<>();
                    }
                    productsList.add(dto);
                }
            }
        }finally{
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
