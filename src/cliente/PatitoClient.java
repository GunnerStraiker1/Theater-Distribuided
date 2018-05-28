package cliente;


import model.IRemoteProvince;
import model.Province;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import model.Usuario;
import model.patitoAPI;
 
/**
 * PatitoClient: client application
 * 
 */
public class PatitoClient {
 
  public static void main(String[] args) {
    try {
      //Get reference to rmi registry server
      Registry registry = LocateRegistry.getRegistry("127.0.0.1");
 
      //Lookup server object
      patitoAPI rp = (patitoAPI) registry.lookup("Patito");
      userInterface wiew = new userInterface();
      
      MainCtrl main = new MainCtrl(wiew, rp);
 
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
