package cliente;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
            UserInterface wiew = new UserInterface();

            MainCtrl main = new MainCtrl(wiew, rp);

        } catch (NotBoundException | RemoteException e) {
            System.out.println(e);
        }
    }
}
