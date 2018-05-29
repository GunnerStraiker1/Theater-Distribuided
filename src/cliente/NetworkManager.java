/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author Armando Carvajal
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class NetworkManager {

    private Socket socket;
    private BufferedReader entrada;
    private BufferedWriter salida;
    private static NetworkManager instancia;
    private AsientosFrame interfazAsientos;

       public void setServer(String IP, int port) {
        try {
            socket = new Socket(IP, port);
        } catch (IOException ex) {
            Logger.getLogger(NetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
