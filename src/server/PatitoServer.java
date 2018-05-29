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
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server
 *
 */
public class PatitoServer {

    public static final int PUERTO = 5000;
    public static ServerSocket sc;
    public static Socket so;
    public DataOutputStream salida;
    public String mensajeRecibido;

    public static void main(String[] args) throws IOException {
        try {

            //Create and get reference to rmi registry
            Registry registry = LocateRegistry.createRegistry(1099);

            //Instantiate server object
            AsientoObject po = new AsientoObject();

            //Register server object
            registry.rebind("Patito", po);
            System.out.println("PatitoServer is created!!!");
            sc = new ServerSocket(PUERTO);/* crea socket servidor que escuchara en puerto 5000*/
            so = new Socket();
            System.out.println("Esperando una conexión:");

            while (true) {

                so = sc.accept();
                System.out.println("Un cliente se ha conectado " + so.getInetAddress().getHostAddress());
            }


        } catch (RemoteException e) {
            System.out.println(e);
        }
    }
}
