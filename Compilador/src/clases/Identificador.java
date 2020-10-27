/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.swing.JOptionPane;

/**
 *
 * @author oliveros
 */
public class Identificador {
    public String lexema,tipo,accion,valor;
    
    public Identificador(){
    
    }
    
    public Identificador(String lexema,String tipo,String accion,String valor){
        this.lexema = lexema;
        this.tipo = tipo;
        this.accion = accion;
        this.valor = valor;
    }

    public String getLexema() {
        return lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAccion() {
        return accion;
    }

    public String getValor() {
        return valor;
    }
    
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
}
