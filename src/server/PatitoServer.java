package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server
 *
 */
public class PatitoServer {
    
    public static final int PUERTO = 2014;

    public static void main(String[] args) throws IOException {
        try {
            //Create and get reference to rmi registry
            Registry registry = LocateRegistry.createRegistry(1099);

            //Instantiate server object
            AsientoObject po = new AsientoObject();
            
            ServerSocket ss = new ServerSocket(PUERTO);
            Log.log("Servidor inicializado en el puerto " + PUERTO);

            //Register server object
            registry.rebind("Patito", po);
            System.out.println("PatitoServer is created!!!");
        } catch (RemoteException e) {
            System.out.println(e);
        }
    }
}
