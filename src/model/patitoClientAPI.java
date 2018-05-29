/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.Remote;

/**
 *
 * @author Victor Perera
 */
public interface patitoClientAPI extends Remote{
       public void registerForCallback( patitoAPI callbackClientObject   ) throws java.rmi.RemoteException; 
   public void unregisterForCallback( patitoAPI callbackClientObject   ) throws java.rmi.RemoteException;
}
