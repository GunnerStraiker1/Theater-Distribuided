package server;

import cliente.AsientosCtrl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server
 *
 */
public class PatitoServer {

    public static ServerSocket sc;
    public static Socket so;

    public String mensajeRecibido;

    public static void main(String[] args) throws IOException {
        try {

            //Create and get reference to rmi registry
            int rmiPortNum = 1099;
            //Registry registry = LocateRegistry.createRegistry(1099);
            startRegistry(rmiPortNum);

            //Instantiate server object
            AsientoObject po = new AsientoObject();
            Naming.rebind("Patito", po);
            //Register server object
            //registry.rebind("Patito", po);
            
            System.out.println("PatitoServer is created!!!");

        } catch (RemoteException e) {
            System.out.println(e);
        }
    }

    private static void startRegistry(int RMIPortNum)
            throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
        } catch (RemoteException e) {
            // No valid registry at that port.
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
        }
    } 
} 
