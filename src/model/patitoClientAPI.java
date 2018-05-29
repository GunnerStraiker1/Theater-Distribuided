/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.rmi.Remote;

/**
 *
 * @author Armando Carvajal
 */
public interface patitoClientAPI extends Remote {

    public String notifyMe(String message) throws java.rmi.RemoteException;

}
