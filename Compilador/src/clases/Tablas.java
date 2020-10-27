/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author oliveros
 */
public class Tablas {
    public static ArrayList<Identificador> listIdentificadores;
    
    public Tablas(){
    
    }
    
    public static void addIdentificador(Identificador i){
        System.out.println("clase: lexema: "+i.lexema+" tipo: "+i.tipo);
        listIdentificadores.add(i);
        //System.out.println("tamaño "+listIdentificadores.size());
        /*listIdentificadores.iterator().forEachRemaining(
                x -> {
                System.out.println("-- "+x);
            });*/
        
    }
    
}
