package server;

import server.ProvinceObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
/**
 * Server
 * 
 */
public class PatitoServer {
 
  public static void main(String[] args) {
    try {
      //Create and get reference to rmi registry
      Registry registry = LocateRegistry.createRegistry(1099);
 
      //Instantiate server object
      AsientoObject po = new AsientoObject();
 
      //Register server object
      registry.rebind("Patito", po);
      System.out.println("PatitoServer is created!!!");
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}

