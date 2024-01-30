/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tranh
 */
public class ProductKeyDAO extends DBContext{
    
    public boolean addKeyToDB(int productId, String keyValue, String expiartionDate) {
        PreparedStatement stm;
        try {
            String createUserSQL = "INSERT INTO ProductKey_HE173252 VALUES (?, ?, ?, ?)";
            stm = connection.prepareStatement(createUserSQL);
            stm.setInt(1, productId);
            stm.setString(2, keyValue);
            stm.setString(3, expiartionDate);
            stm.setBoolean(4, false);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
