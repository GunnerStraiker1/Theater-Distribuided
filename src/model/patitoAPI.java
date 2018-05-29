/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Victor Perera
 */
public interface patitoAPI extends Remote {

    public void comprarAsiento(Asiento seat) throws RemoteException;

    public void seleccionarAsiento(Asiento seat) throws RemoteException;

    public void deseleccionarAsiento(Asiento seat) throws RemoteException;

    public ArrayList<Usuario> login(Usuario user) throws RemoteException;

    public ArrayList<Asiento> asientos() throws RemoteException;
    
    public String notifyMe(String mensaje) throws  RemoteException;
    
    public void registerForCallback( patitoClientAPI callbackClientObject   ) throws java.rmi.RemoteException; 
    
    public void unregisterForCallback( patitoClientAPI callbackClientObject   ) throws java.rmi.RemoteException;
}
