/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cliente.AsientosCtrl;
import cliente.AsientosFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Armando Carvajal
 */
public class NetworkManager {

    private BufferedReader entrada;
    private final String UPDATE = "ACTUALIZACION";
    private BufferedWriter salida;
    private static NetworkManager instancia;
    private Socket socket;
    private AsientosCtrl control;

    public static NetworkManager getInstance() {
        if (instancia == null) {
            instancia = new NetworkManager();
        }
        return instancia;
    }

    public void escucharServidor() {
        inicializarHeartBeat();
        try {
            while (true) {
                String packet = recibir();

                if (packet.startsWith(UPDATE)) {
                    control.crearPanelAsientos();
                }
            }
        } catch (NullPointerException ex) {
        }
    }

    public void enviar(String var) {
        if (!var.isEmpty()) {
            try {
                salida.write(var + "\n");
                salida.flush();
            } catch (IOException ex) {
                Logger.getLogger(NetworkManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String recibir() {
        String s = "";
        try {
            s = entrada.readLine();
        } catch (IOException ex) {

        }
        return s;
    }

    public void setServer(String IP, int port) {
        try {
            socket = new Socket(IP, port);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(NetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicializarHeartBeat() {
        new Thread(() -> {
            long heartbeat = System.currentTimeMillis();
            while (true) {
                if (System.currentTimeMillis() - heartbeat >= 5000) {
                    enviar("BEAT " + System.currentTimeMillis());
                    heartbeat = System.currentTimeMillis();
                }
            }
        }).start();
    }

}
