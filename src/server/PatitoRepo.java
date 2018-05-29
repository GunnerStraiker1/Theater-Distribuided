/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Asiento;
import model.Usuario;

/**
 *
 * @author Victor Perera
 */
public class PatitoRepo {
    public static void comprar(Asiento seat){
        try {
            Connection con = DBManager.getInstance().getConnection();
            ;
            String SQL = "UPDATE asientos SET estado=? WHERE nombre=? AND user=?";

            try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
                pstmt.setString(1, seat.getEstado());
                pstmt.setString(2, seat.getNombre());
                pstmt.setInt(3, seat.getUser());
                
                pstmt.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
    }
    
    public static void seleccionar(Asiento seat){
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "INSERT INTO asientos (nombre,estado,user) values(?,?,?)";

            try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
                pstmt.setString(1, seat.getNombre());
                pstmt.setString(2, seat.getEstado());
                pstmt.setInt(3, seat.getUser());
                pstmt.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
    }
    
    public static void deseleccionar(Asiento seat){
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM asientos WHERE nombre=? AND user=?";

            try (PreparedStatement pstmt = con.prepareStatement(SQL)) {
                pstmt.setString(1, seat.getNombre());
                pstmt.setInt(2, seat.getUser());
                pstmt.executeUpdate();
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
    }
    
    public static ArrayList<Usuario> login(Usuario user) {
        ArrayList<Usuario> usrs = new ArrayList();
        try {
      String QRY = "SELECT * FROM users WHERE name = ? AND password=?";
      Connection con = DBManager.getInstance().getConnection();
            try (PreparedStatement pstmt = con.prepareStatement(QRY)) {
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPassword());
                
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Usuario client = new Usuario(rs.getInt("id"), rs.getString("name"),  rs.getString("password"));
                    usrs.add(client);
                }     }
    } catch (SQLException se) {
      System.out.println(se);
    }
        if (usrs.isEmpty()) {
            return usrs;
        }
        else{
            return usrs;
        }
    }
    
    public static ArrayList<Asiento> findSeatsUser(){
        ArrayList<Asiento> arr = new ArrayList();
 
    try {
      String QRY = "SELECT * FROM asientos";
      Connection con = DBManager.getInstance().getConnection();
            try (PreparedStatement pstmt = con.prepareStatement(QRY)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Asiento asiento = new Asiento(rs.getString("nombre"), rs.getString("estado"), rs.getInt("user"));
                    arr.add(asiento);
                }     }
    } catch (SQLException se) {
      System.out.println(se);
    }
    return arr;
    }
    
}
