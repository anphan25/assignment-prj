/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.registration;

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
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select username "
                        +"from Registration "
                        +"where username = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
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
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }
    
    private ArrayList<RegistrationDTO> accountsList;
    
    public ArrayList<RegistrationDTO> getAccountsList(){
        return accountsList;
    }
    
    public void searchLastname(String searchValue)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "where lastname like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%"+searchValue+"%");
                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, isAdmin);
                    if(this.accountsList == null){
                        this.accountsList = new ArrayList<>();
                    }
                    this.accountsList.add(dto);
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
    
    private String lastnameOfUser;
    public String getLastnameOfuser(){
        return lastnameOfUser;
    }
    
    public void getLastname(String username)
        throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "select lastname "
                        +"from Registration "
                        +"where username = ?";
                stm=con.prepareStatement(sql);
                stm.setString(1, username);
                rs=stm.executeQuery();
                if(rs.next()){
                    this.lastnameOfUser = rs.getString("lastname");
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
    
    public boolean deleteAccount(String username)
        throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "delete from Registration "+
                        "where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
    
    public boolean updateAccount(String username, String password, boolean isAdmin)
        throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Update Registration SET password =? , isAdmin =? "
                        +"where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                
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
    
    public boolean editAccount(String username, String password, String lastname, boolean isAdmin)
        throws SQLException,NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "Update Registration "
                        +"Set password = ?, lastname = ?, isAdmin = ? "
                        +"where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, lastname);
                stm.setBoolean(3, isAdmin);
                stm.setString(4, username);
                
                int row = stm.executeUpdate();
                if(row > 0){
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
    
    public boolean createAccount(RegistrationDTO dto)
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        
        try{
            con = DBHelper.makeConnection();
            if(con != null){
                String sql = "insert into Registration(username,password,lastname,isAdmin) "
                        +"values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastname());
                stm.setBoolean(4, dto.isIsAdmin());
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
