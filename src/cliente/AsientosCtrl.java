/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

//import DAO.DAOAsientos;
//import Interfaz.AsientosFrame;
//import Interfaz.VentaFrame;
//import Modelo.Asiento;
//import Modelo.Funcion;
//import Modelo.Obra;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Usuario;
import model.patitoAPI;
//
///**
// *
// * @author Victor Perera
// */
public class AsientosCtrl implements ActionListener{
    
    private AsientosFrame view;
    private patitoAPI rp;
    private List<JButton> buttons = new ArrayList<>();


    public AsientosCtrl(AsientosFrame view, patitoAPI rp) {
        this.view = view;
        this.rp = rp;
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
        crearPanelAsientos();
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        }
    
//    private AsientosFrame asientosView;
    
//    private DAOAsientos daoAsiento;
//    private List<Asiento> listAsientos;
//    private List<JButton> buttons = new ArrayList<>();
//    private int noAsientosSelect;
//    private List<Asiento> asientosSelect = new ArrayList<>();
//
//    public MainCtrl(AsientosFrame asientosView, DAOAsiento daoAsiento) {
//        this.asientosView = asientosView;
//        this.daoAsiento = daoAsiento;
//        this.asientosView.setLocationRelativeTo(null);
//        this.asientosView.buy.addActionListener(this);
//        listAsientos = daoAsiento.consultarAsientosGral();
//        this.noAsientosSelect=0;
//        crearPanelAsientos(obra);
//        this.asientosView.setVisible(true);        
//    }
//
//    public AsientosFrame getAsientosView() {
//        return asientosView;
//    }
//
//    public void setAsientosView(AsientosFrame asientosView) {
//        this.asientosView = asientosView;
//    }
//
//    public DAOAsiento getDaoAsiento() {
//        return daoAsiento;
//    }
//
//    public void setDaoAsiento(DAOAsiento daoAsiento) {
//        this.daoAsiento = daoAsiento;
//    }
//
//    public List<Asiento> getListAsientos() {
//        return listAsientos;
//    }
//
//    public void setListAsientos(List<Asiento> listAsientos) {
//        this.listAsientos = listAsientos;
//    }
//
//    public List<JButton> getButtons() {
//        return buttons;
//    }
//
//    public void setButtons(List<JButton> buttons) {
//        this.buttons = buttons;
//    }
//
//    public int getNoAsientosSelect() {
//        return noAsientosSelect;
//    }
//
//    public void setNoAsientosSelect(int noAsientosSelect) {
//        this.noAsientosSelect = noAsientosSelect;
//    }
//        
    private void crearPanelAsientos() {
        for (int i = 0; i < 100; i++) {

            JButton lab = new JButton();
            this.view.panel.add(lab);        

            lab.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                        if (!lab.getBackground().equals(Color.BLACK)) {
//                            if (getNoAsientosSelect() < Integer.parseInt(asientosView.noBoletos.getText())) {
//                            seleccionarAsiento(lab, obra);
//                            setNoAsientosSelect(getNoAsientosSelect() + 1);
//                        } else {
//                                JOptionPane.showMessageDialog(asientosView, "Has alcanzado tu limite de asientos");
//                            
//                        }
//                    }
//                    else{
//                            deseleccionarAsiento(lab);
//                            setNoAsientosSelect(getNoAsientosSelect() - 1);
//                        
//                    }
                }
            });
            buttons.add(lab);  
            this.view.panel.updateUI();
        }
    }
}
//    
//    private void seleccionarAsiento(JButton boton, Obra obra){
//            Color bg = boton.getBackground();
//            String palco = null;
//            double porcentaje=0;
//            if (bg.equals(Color.BLUE)) {
//                palco = "Plata";
//                porcentaje=.75;
//            }
//            else if(bg.equals(Color.YELLOW)){
//                palco = "Oro";
//                porcentaje=.9;
//            }
//            else if(bg.equals(Color.GREEN)){
//                palco = "Diamante";
//                porcentaje=1;
//            }
//            else if(bg.equals(Color.LIGHT_GRAY)){
//                palco = "Lata";
//                porcentaje=.5;
//            }
//            else if(bg.equals(Color.ORANGE)){
//                palco = "Cobre";
//                porcentaje=.6;
//            }
//            double precio = daoAsiento.consultarPrecio(obra);
//        
//            Asiento asientoSelect = new Asiento(boton.getText(),palco,(precio*porcentaje));
////            System.out.println(asi.getNombre());
//            asientosSelect.add(asientoSelect);
//            
//            boton.setBackground(Color.BLACK);
//            boton.setForeground(Color.BLACK);
//    }
//    
//    public void deseleccionarAsiento(JButton sit){
//        for (int j = 0; j < asientosSelect.size(); j++) {
//            if(asientosSelect.get(j).getNombre().equals(sit.getText())){
//                if(asientosSelect.get(j).getArea().equalsIgnoreCase("Plata")){
//                   sit.setBackground(Color.BLUE); 
//                }
//                else if(asientosSelect.get(j).getArea().equalsIgnoreCase("Oro")){
//                   sit.setBackground(Color.YELLOW); 
//                }
//                else if(asientosSelect.get(j).getArea().equalsIgnoreCase("Diamante")){
//                   sit.setBackground(Color.GREEN); 
//                }
//                else if(asientosSelect.get(j).getArea().equalsIgnoreCase("Lata")){
//                   sit.setBackground(Color.LIGHT_GRAY); 
//                }
//                else if(asientosSelect.get(j).getArea().equalsIgnoreCase("Cobre")){
//                   sit.setBackground(Color.ORANGE); 
//                }
//                sit.setEnabled(true);
//                asientosSelect.remove(j);
//                //i--;
//            }
//        }
//    }
//    
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

