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
public interface patitoAPI extends Remote{
    public void comprarAsiento(Asiento seat) throws RemoteException;
    public void seleccionarAsiento(Asiento seat) throws RemoteException;
    public void deseleccionarAsiento(Asiento seat) throws RemoteException;
    public boolean login(Usuario user) throws RemoteException;
    public ArrayList AllAsientosUser(Usuario user) throws RemoteException;
}
