/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Usuario;
import model.patitoAPI;
//
///**
// *
// * @author Victor Perera
// */

public class MainCtrl implements ActionListener {

    private final UserInterface viewLogin;
    private final patitoAPI rp;

    public MainCtrl(UserInterface viewLogin, patitoAPI rp) {
        this.viewLogin = viewLogin;
        this.rp = rp;
        this.viewLogin.btnLogin.addActionListener(this);
        this.viewLogin.setLocationRelativeTo(null);
        this.viewLogin.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.viewLogin.btnLogin) {
            String name = this.viewLogin.txtName.getText();
            String pass = String.copyValueOf(this.viewLogin.pwdLog.getPassword());
            if (!name.isEmpty() && !pass.isEmpty()) {
                try {
                    Usuario usr = new Usuario(name, pass);
                    ArrayList<Usuario> users = rp.login(usr);
                    if (users.size() > 0) {
                        JOptionPane.showMessageDialog(viewLogin, "Bienvenido Usuario", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                        AsientosFrame viewSeat = new AsientosFrame();
                        this.viewLogin.dispose();
                        int idUser = users.get(0).getId();

                        AsientosCtrl seatCtrl = new AsientosCtrl(viewSeat, rp, idUser);
                    } else {
                        JOptionPane.showMessageDialog(viewLogin, "Usuario invalido", "Login Failed", JOptionPane.ERROR_MESSAGE);
                        this.viewLogin.txtName.setText("");
                        this.viewLogin.pwdLog.setText("");
                    }
                } catch (RemoteException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(viewLogin, "No hay conexión con la base de datos", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(viewLogin, "Datos incompletos para iniciar sesion", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
