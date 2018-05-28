/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

//import Conexion.Connect;
//import Modelo.Asientos;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Victor Perera
// */
//public class DAOAsiento {
//    Connect cn = new Connect();
//
//    public void insertarAsiento(Asientos[] asientos,String clv) {
//        Asientos[] asientosComprados;
//        asientosComprados = asientos;
//        String sql = "INSERT INTO "+clv+"(`Asiento`,`Price`) VALUES (?,?)";
//        Connection con;
//        try {
//            Class.forName(cn.getDriver());
//            con = DriverManager.getConnection(cn.getUrl(), cn.getUsuario(), cn.getContraseña());                 
//            for (int j = 0; j < asientosComprados.length; j++) {
//                PreparedStatement pst = con.prepareStatement(sql);
//                pst.setString(1, asientosComprados[j].getNombre());
//                pst.setDouble(2, asientosComprados[j].getPrecio());
//                pst.executeUpdate();                    
//                }
//            con.close();
//            } catch (SQLException|ClassNotFoundException ex) {
//                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
//            } 
//    }
//
//    public void eliminarAsiento(Asientos asiento, String clv) {
//        Asientos asientoEliminado = asiento;
//        String sql = "DELETE FROM"+clv+"WHERE Asiento=?";
//        Connection con;
//        try {
//            Class.forName(cn.getDriver());
//            con = DriverManager.getConnection(cn.getUrl(), cn.getUsuario(), cn.getContraseña());                 
//                PreparedStatement pst = con.prepareStatement(sql);
//                pst.setString(1, asientoEliminado.getNombre());
//                pst.executeUpdate();                    
//            con.close();
//            } catch (SQLException|ClassNotFoundException ex) {
//                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//
//    public void modificar(Object obj) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public Asientos[] consultarAsientosGral() {
//        Asientos[] asientosGral = new Asientos[160];
//        String sql = "SELECT nombre,palco,precio FROM `asientos`,`infoarea` WHERE palco = Color" ;
//        Connection con;   
//        int cont =0;
//        
//        try {
//            Class.forName(cn.getDriver());
//            con = DriverManager.getConnection(cn.getUrl(), cn.getUsuario(), cn.getContraseña());
//            PreparedStatement pst = con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()){
//                Asientos asiento = new Asientos(rs.getString(1),rs.getString(2),rs.getDouble(3));
//                asientosGral[cont] = asiento;
//                cont++;
//            }
//            con.close();
//        } catch (ClassNotFoundException|SQLException ex) {
//            Logger.getLogger(DAOAsiento.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return asientosGral;
//    }
//    
//    private int contarRegistros(String clv){
//        String sql ="SELECT * FROM"+clv;
//        int noRegistros=0;
//        Connection con;
//        
//        try {
//            Class.forName(cn.getDriver());
//            con = DriverManager.getConnection(cn.getUrl(), cn.getUsuario(), cn.getContraseña());
//            PreparedStatement pst = con.prepareStatement(sql);
//            ResultSet rs = pst.executeQuery();
//            while(rs.next()){
//                noRegistros++;
//            }
//            con.close();
//        } catch (ClassNotFoundException |SQLException ex) {
//            Logger.getLogger(DAOAsiento.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return noRegistros;
//    }
//
//    public List<?> filtrar() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//    
//}
