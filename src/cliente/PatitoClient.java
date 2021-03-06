package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.patitoAPI;
import model.patitoClientAPI;
import server.AsientoObject;

/**
 * PatitoClient: client application
 *
 */
public class PatitoClient {

    public static void main(String[] args) throws MalformedURLException {
        try {
            //Get reference to rmi registry server

            Registry registry = LocateRegistry.getRegistry("127.0.0.1");

            patitoAPI rp = (patitoAPI) registry.lookup("Patito");
            System.out.println("Lookup completed ");
            //System.out.println("Server said " + h.sayHello());
            
            System.out.println("Registered for callback.");
                        
            UserInterface wiew = new UserInterface();

            MainCtrl main = new MainCtrl(wiew, rp);

        } catch (NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
