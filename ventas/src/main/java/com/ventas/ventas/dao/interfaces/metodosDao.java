/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ventas.ventas.dao.interfaces;

import java.util.List;

/**
 *
 * @author Henry
 */
public interface metodosDao<T> {

    public List<T> listar();

    public boolean insertar(T obj);

    public boolean actualizar(T obj);

    public int buscarCodigo(int codigo);

    public T getObjeto(int codigo);

}
