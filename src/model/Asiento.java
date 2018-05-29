/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Victor Perera
 */
public class Asiento implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String estado;
    private int user;

    public Asiento(String nombre, String estado, int user) {
        this.nombre = nombre;
        this.estado = estado;
        this.user = user;
    }

    public Asiento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Asiento{" + "nombre=" + nombre + ", estado=" + estado + ", user=" + user + '}';
    }

    
}
