/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import static ventanas.PanelColores.txt;

import componentes.Lienzo;

/**
 *
 * @author oliveros
 */
public class PnlFunciones extends JPanel{
    JPanel pnlTitle;
    JLabel lblTitle;
    
    Graphics g;
    Lienzo lienzo;
    int y =0;
    public String[] arrayToken;
    
    
    public PnlFunciones(){
        pnlTitle = new JPanel();
        lblTitle = new JLabel("test");
        
        pnlTitle.setSize(20,10);
        pnlTitle.setBackground(Color.yellow);
        pnlTitle.add(lblTitle);
        pnlTitle.setVisible(true);
        
        this.setLayout(new BorderLayout());
        this.setSize(20,10);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.add(pnlTitle,BorderLayout.NORTH);
    }
    public PnlFunciones(int tipo){
        pnlTitle = new JPanel();
        if(tipo==0)
            lblTitle = new JLabel("Automata");
        else lblTitle = new JLabel("Arbol");
        
        
        pnlTitle.setSize(10,30);
        pnlTitle.setBackground(Color.yellow);
        pnlTitle.add(lblTitle);
        pnlTitle.setVisible(true);
        
        this.setLayout(new BorderLayout());
        this.setSize(20,10);
        this.setVisible(false);
        this.setBackground(Color.BLUE);
        JScrollPane jsp=new JScrollPane(pnlTitle);
        this.add(jsp,BorderLayout.NORTH);
    }
    
    public void setTitle(String titulo){
        lblTitle.setText(titulo);
    }
}
