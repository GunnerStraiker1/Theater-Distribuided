/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.patitoAPI;
//
///**
// *
// * @author Victor Perera
// */

public class AsientosCtrl implements ActionListener {

    private AsientosFrame view;
    private patitoAPI rp;
    private int asientosSeleccionados;
    private List<JButton> buttons = new ArrayList<>();

    public AsientosCtrl(AsientosFrame view, patitoAPI rp) {
        this.view = view;
        this.rp = rp;
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
        this.asientosSeleccionados = 0;
        crearPanelAsientos();
        this.view.buy.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.view.buy) {
            for (int i = 0; i < buttons.size(); i++) {
                if(buttons.get(i).getBackground().equals(Color.BLACK)){
                    System.out.println(buttons.get(i).getBackground().toString());
                }
            }
        }
    }
     
    private void crearPanelAsientos() {
        for (int i = 0; i < 100; i++) {

            JButton lab = new JButton();
            lab.setBackground(Color.GREEN);
            this.view.panel.add(lab);

            lab.addActionListener((ActionEvent e) -> {

                if (!lab.getBackground().equals(Color.GREEN)) {
                    if (lab.getBackground().equals(Color.BLACK)) {
                        this.asientosSeleccionados--;
                        lab.setBackground(Color.GREEN);
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
                        this.asientosSeleccionados++;
                        lab.setBackground(Color.BLACK);
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

