/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ventas.ventas.dao;

import com.ventas.ventas.acceso.Acceso;
import com.ventas.ventas.dao.interfaces.metodosDao;
import com.ventas.ventas.entidades.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Henry
 */
public class ClienteDAO implements metodosDao<Cliente> {

    private final List<Cliente> lista;
    private Metodos<Cliente> metodos;
    private final String ruta = "cliente.txt";
    private boolean resp;
    private Cliente cliente;

    public ClienteDAO() {
        lista = new ArrayList<>();
        metodos = new Metodos<>(lista);
        cargarLista();
    }

    private void cargarLista() {
        Cliente cliente;
        for (String dato : Acceso.cargarArchivo(ruta)) {
            StringTokenizer st = new StringTokenizer(dato, ",");
            cliente = new Cliente(Integer.parseInt(st.nextToken()), st.nextToken());
            metodos.agregarRegistro(cliente);
        }
    }

    @Override
    public List listar() {
        List<Cliente> registros = new ArrayList<>();
        try {
            for (String dato : Acceso.cargarArchivo(ruta)) {
                StringTokenizer st = new StringTokenizer(dato, ",");
                cliente = new Cliente(Integer.parseInt(st.nextToken()), st.nextToken());
                registros.add(cliente);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Clientes: " + e.getMessage());
        }
        return registros;
    }

    @Override
    public boolean insertar(Cliente obj) {
        resp = false;
        PrintWriter pw;
        FileWriter fw;
        try{
            fw = new FileWriter("Archivos/"+ruta);
            pw = new PrintWriter(fw);
            obj = new Cliente(obj.getNit(), obj.getNombre()); 
            metodos.agregarRegistro(obj);
            for( int i = 0; i < metodos.cantidadRegistro(); i++){
                cliente = metodos.obtenerRegistro(i);
                pw.println(String.valueOf(cliente.getNit()+","+cliente.getNombre()));
            }
            pw.close();
            resp = true;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al insertar Clientes: " + e.getMessage());
        }
        return resp;
    }

    @Override
    public boolean actualizar(Cliente obj) {
        resp = false;
        PrintWriter pw;
        FileWriter fw;
        try{
            fw = new FileWriter("Archivos/"+ruta);
            pw = new PrintWriter(fw);
            obj = new Cliente(obj.getNit(), obj.getNombre()); 
            int codigo = buscaCodigo(obj.getNit());
            if(codigo == -1){
                metodos.agregarRegistro(obj);
            }else{
                metodos.modificar(codigo, obj);
            }
            
            for( int i = 0; i < metodos.cantidadRegistro(); i++){
                cliente = metodos.obtenerRegistro(i);
                pw.println(String.valueOf(cliente.getNit()+","+cliente.getNombre()));
            }
            pw.close();
            resp = true;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al modificar Cliente: " + e.getMessage());
        }
        return resp;
    }

    public int buscaCodigo(int codigo) {
        for(int i = 0; i < metodos.cantidadRegistro(); i++){
            if(codigo == metodos.obtenerRegistro(i).getNit()){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Cliente getObjeto(int codigo) {
        Cliente cliente = null;
        for (int i = 0; i < metodos.cantidadRegistro(); i++) {
            cliente = metodos.obtenerRegistro(i);
            if (cliente.getNit() == codigo) {
                return cliente;
            }
        }
        return cliente;
    }
}
