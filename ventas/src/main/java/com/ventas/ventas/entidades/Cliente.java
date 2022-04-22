/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.ventas.entidades;

/**
 *
 * @author Henry
 */
public class Cliente {

    private Integer nit;
    private String nombre;

    public Cliente() {
    }

    public Cliente(Integer nit, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

}
