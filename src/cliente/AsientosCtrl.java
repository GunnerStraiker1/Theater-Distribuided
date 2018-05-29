/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Asiento;
import model.NetworkManager;
import model.patitoAPI;
import model.patitoClientAPI;

/**
 *
 * @author Victor Perera
 */

public final class AsientosCtrl extends UnicastRemoteObject implements ActionListener,patitoClientAPI
{

    private final AsientosFrame view;
    NetworkManager net;
    private final patitoAPI rp;
    private int asientosSeleccionados;
    private final List<JButton> buttons = new ArrayList<>();
    private final String COMPRADO = "COMPRADO";
    private final String PENDIENTE = "EN ESPERA";
    private final String UPDATE = "ACTUALIZACION";
    private final int user;
    final String HOST = "localhost";
    
    public AsientosCtrl(AsientosFrame view, patitoAPI rp, int user) throws IOException {
        this.view = view;
        this.rp = rp;
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
        this.asientosSeleccionados = 0;
        this.view.buy.addActionListener(this);
        this.view.jBBack.addActionListener(this);
        this.user = user;
        crearPanelAsientos();
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Asiento> prepare = new ArrayList<>();
        if (e.getSource() == this.view.buy) {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getBackground().equals(Color.BLACK)) {
                    String name = buttons.get(i).getName();
                    Asiento asientoComprado = new Asiento(name, COMPRADO, this.user);
                    prepare.add(asientoComprado);
                    buttons.get(i).setBackground(Color.GRAY);
                    
                }
            }

            if (!prepare.isEmpty()) {
                for (int i = 0; i < prepare.size(); i++) {
                    try {
                        rp.comprarAsiento(prepare.get(i));
                    } catch (RemoteException ex) {
                        Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                JOptionPane.showMessageDialog(view, "Ha comprado los boletos", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "No ha seleccionado boletos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }

        }

        if (e.getSource() == this.view.jBBack) {
            UserInterface wiew = new UserInterface();
            this.view.dispose();
            MainCtrl main = new MainCtrl(wiew, rp);

            for (int i = 0; i < buttons.size(); i++) {
                String name = buttons.get(i).getName();
                Asiento asientoCancelado = new Asiento(name, PENDIENTE, this.user);
                try {
                    rp.deseleccionarAsiento(asientoCancelado);
                } catch (RemoteException ex) {
                    Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public void crearPanelAsientos() {
        try {
            System.out.println(rp.sayHello());
        } catch (RemoteException ex) {
            Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 1;
        char letra = 64;

        for (int i = 0; i < 100; i++) {

            JButton lab = new JButton();
            if (i % 10 == 0) {
                count = 1;
                letra++;
                lab.setName(Character.toString(letra) + count);

            } else {
                count++;
                lab.setName(Character.toString(letra) + count);
            }
            lab.setBackground(Color.GREEN);
            try {
                if (!rp.asientos().isEmpty()) {
                    for (int j = 0; j < rp.asientos().size(); j++) {
                        Asiento asientos = rp.asientos().get(j);
                        if (asientos.getNombre().equals(lab.getName()) && asientos.getUser() == this.user && !asientos.getEstado().equals(PENDIENTE)) {
                            lab.setBackground(Color.GRAY);
                        }
                        if (asientos.getNombre().equals(lab.getName()) && asientos.getUser() != this.user) {
                            lab.setBackground(Color.RED);
                        }

                        if (asientos.getNombre().equals(lab.getName()) && asientos.getUser() != this.user && asientos.getEstado().equals(PENDIENTE)) {
                            lab.setBackground(Color.BLUE);
                        }

                        if (asientos.getNombre().equals(lab.getName()) && asientos.getUser() == this.user && asientos.getEstado().equals(PENDIENTE)) {
                            lab.setBackground(Color.BLACK);
                        }
                    }
                }

            } catch (RemoteException ex) {
                Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.view.panel.add(lab);

            lab.addActionListener((ActionEvent e) -> {

                if (!lab.getBackground().equals(Color.GREEN)) {
                    if (lab.getBackground().equals(Color.BLACK)) {
                        try {
                            this.asientosSeleccionados--;
                            lab.setBackground(Color.GREEN);
                            String name = lab.getName();
                            Asiento asientoCancelado = new Asiento(name, PENDIENTE, this.user);
                            rp.deseleccionarAsiento(asientoCancelado);
                        } catch (RemoteException ex) {
                            Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (lab.getBackground().equals(Color.GRAY)) {
                        Object options[] = {"Si", "No"};
                        int selec = JOptionPane.showOptionDialog(lab, "Se ha seleccionado antes, ¿desea canelarlo?", "Seleccion", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                        if (selec == 0) {
                            try {
                                String name = lab.getName();
                                Asiento asientoEliminado = new Asiento(name, PENDIENTE, this.user);
                                rp.deseleccionarAsiento(asientoEliminado);
                                lab.setBackground(Color.GREEN);
                            } catch (RemoteException ex) {
                                Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    if (lab.getBackground().equals(Color.BLUE)) {
                        JOptionPane.showMessageDialog(view, "Siendo reservado", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    }

                    if (lab.getBackground().equals(Color.RED)) {
                        JOptionPane.showMessageDialog(view, "Ocupado", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    if (this.asientosSeleccionados < 5) {
                        try {
                            this.asientosSeleccionados++;
                            String name = lab.getName();
                            Asiento asientoSeleccionado = new Asiento(name, PENDIENTE, this.user);
                            rp.seleccionarAsiento(asientoSeleccionado);
                            lab.setBackground(Color.BLACK);
                        } catch (RemoteException ex) {
                            Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Limite de asientos alcanzado para está sesión", "Limite", JOptionPane.INFORMATION_MESSAGE);

                    }

                }
            });
            buttons.add(lab);
            this.view.panel.updateUI();
        }
    }

    @Override
    public String notifyMe(String message) throws RemoteException {
              String returnMessage = "Call back received: " + message; 
              System.out.println(returnMessage); 
            return returnMessage; 
    }

}
