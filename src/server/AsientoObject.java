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
import model.Asiento;
import model.Usuario;
import model.patitoAPI;

/**
 *
 * @author Victor Perera
 */
public class AsientoObject extends UnicastRemoteObject implements patitoAPI{
    
    private static final long serialVersionUID = 11L;

    public AsientoObject() throws RemoteException{
        super();
    }
    

    @Override
    public void comprarAsiento(Asiento seat)  {
        try {
      System.out.println("Invoke Comprar Asiento from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
        PatitoRepo.comprar(seat);
    }

    @Override
    public void seleccionarAsiento(Asiento seat){
        try {
      System.out.println("Invoke Select Asiento from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
        PatitoRepo.seleccionar(seat);
    }
    
    @Override
    public void deseleccionarAsiento(Asiento seat){
        try {
      System.out.println("Invoke Diselect Asiento from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
        PatitoRepo.deseleccionar(seat);
    }    

    @Override
    public boolean login(Usuario user) {
        try {
      System.out.println("Invoke Login from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
        return PatitoRepo.login(user);
    }

    @Override
    public ArrayList AllAsientosUser(Usuario user) {
        try {
      System.out.println("Invoke AllAsientos from " + getClientHost());
    } catch (ServerNotActiveException snae) {
    }
        return PatitoRepo.findSeatsUser(user);
    }
    
}
