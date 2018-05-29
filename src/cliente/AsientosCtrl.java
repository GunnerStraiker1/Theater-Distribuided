/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Asiento;
import model.patitoAPI;
//
///**
// *
// * @author Victor Perera
// */

public class AsientosCtrl implements ActionListener {

    private final AsientosFrame view;
    private final patitoAPI rp;
    private int asientosSeleccionados;
    private final List<JButton> buttons = new ArrayList<>();
    private final String COMPRADO = "COMPRADO";
    private final String PENDIENTE = "EN ESPERA";
    private final String SINSELECCION = "SIN SELECCION";
    private int user;

    public AsientosCtrl(AsientosFrame view, patitoAPI rp, int user) {
        this.view = view;
        this.rp = rp;
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
        this.asientosSeleccionados = 0;
        crearPanelAsientos();
        this.view.buy.addActionListener(this);
        this.user = user;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.buy) {
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getBackground().equals(Color.BLACK)) {
                    try {
                        String name = buttons.get(i).getName();
                        Asiento asientoComprado = new Asiento(name, COMPRADO, this.user);
                        rp.comprarAsiento(asientoComprado);
                        JOptionPane.showMessageDialog(view, "Ha comprado los boletos", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        buttons.get(i).setBackground(Color.GRAY);
                    } catch (RemoteException ex) {
                        Logger.getLogger(AsientosCtrl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private void crearPanelAsientos() {
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
                        if (rp.asientos().get(j).getNombre().equals(lab.getName()) && rp.asientos().get(j).getUser() == 1) {
                            lab.setBackground(Color.GRAY);
                        }
                        if(rp.asientos().get(j).getNombre().equals(lab.getName()) && rp.asientos().get(j).getUser() != this.user ) {
                            lab.setBackground(Color.RED);
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
                        JOptionPane.showMessageDialog(view, "Asientos seleccionados anteriormente", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
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
}

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == this.asientosView.buy) {
//            try{
//            if(asientosSelect.size()==Integer.parseInt(this.asientosView.noBoletos.getText())){
//                daoAsiento.comprarAsientos(funcion, asientosSelect);
//                JOptionPane.showMessageDialog(asientosView, "Seleccion realizada con éxito");
//                this.asientosView.dispose();
//                VentaFrame ventaView = new VentaFrame();
//                for (int i = 0; i < asientosSelect.size(); i++) {
//                    asientosSelect.get(i).setClvVenta(asientosSelect.get(0).getNombre()+funcion.getClaveFuncion());
//                }
//                VentaCtrl ventaCtrl = new VentaCtrl(ventaView, asientosSelect);
//            }
//            else{
//                throw new Exception("Número de boletos seleccionados incorrecto");
//            }
//            }catch(Exception ex){
//                JOptionPane.showMessageDialog(asientosView, ex.getMessage());
//                ex.printStackTrace();
//            }
//        }
//    }
//    

