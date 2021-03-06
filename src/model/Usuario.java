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
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String password;

    public Usuario(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Usuario(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", name=" + name + ", password=" + password + '}';
    }

}
