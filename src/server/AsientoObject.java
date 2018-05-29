/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.RemoteException;
import static java.rmi.server.RemoteServer.getClientHost;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;
import model.Asiento;
import model.Usuario;
import model.patitoAPI;
import model.patitoClientAPI;

/**
 *
 * @author Victor Perera
 */
public class AsientoObject extends UnicastRemoteObject implements patitoAPI {
    private Vector clientList; 
    private static final long serialVersionUID = 11L;

    public AsientoObject() throws RemoteException {
        super();
        clientList = new Vector();
    }

    @Override
    public void comprarAsiento(Asiento seat) {
        try {
           System.out.println("Invoke Comprar Asiento from " + getClientHost());
        } catch (ServerNotActiveException snae) {
        }
        PatitoRepo.comprar(seat);
    }

    @Override
    public void seleccionarAsiento(Asiento seat) {
        try {
            System.out.println("Invoke Select Asiento from " + getClientHost());
        } catch (ServerNotActiveException snae) {
        }
        PatitoRepo.seleccionar(seat);
    }

    @Override
    public void deseleccionarAsiento(Asiento seat) {
        try {
            System.out.println("Invoke Diselect Asiento from " + getClientHost());
        } catch (ServerNotActiveException snae) {
        }
        PatitoRepo.deseleccionar(seat);
    }

    @Override
    public ArrayList<Usuario> login(Usuario user) {
        try {
            System.out.println("Invoke Login from " + getClientHost());
        } catch (ServerNotActiveException snae) {
        }
        return PatitoRepo.login(user);
    }

    @Override
    public ArrayList<Asiento> asientos() {
//System.out.println("Invoke AllAsientosUser from " + getClientHost());
        return PatitoRepo.findSeatsUser();
    }

    @Override
    public String notifyMe(String mensaje) throws RemoteException {
        String returnMessage = "Call back received: " + mensaje;
        return returnMessage;
    }

    @Override
    public synchronized void unregisterForCallback(patitoClientAPI callbackClientObject) throws RemoteException {
        if (clientList.removeElement(callbackClientObject)) {
            System.out.println("Unregistered client. ");
        } else {
            System.out.println("unregister: client wasn't registered.");
        }
    }

    @Override
    public synchronized void registerForCallback(patitoClientAPI callbackClientObject) throws RemoteException {
        if (!(clientList.contains(callbackClientObject))) {
            clientList.addElement(callbackClientObject);
            doCallbacks();
        }
    } 
    
    private synchronized void doCallbacks( ) throws RemoteException  { 
    for (int i = 0; i < clientList.size(); i++)     { 
System.out.println("doing "+ i +"-th callback\n");     
patitoClientAPI nextClient=  (patitoClientAPI) clientList.elementAt(i); 
nextClient.notifyMe("Number of registered clients="+ clientList.size()); 
    } // for 
  } // function 

    @Override
    public String sayHello() throws RemoteException {
        for (int i = 0; i < clientList.size(); i++) {
            patitoClientAPI nextClient=  (patitoClientAPI) clientList.elementAt(i); 
            nextClient.notifyMe("Actualizate prra"); 
        }
        return ("Que hongo!");
    }

}
