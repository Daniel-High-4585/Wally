/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author oliveros
 */
public class PnlTablas extends JPanel{
 JPanel pnlTitle;
 JLabel lblTitle;
 JTable tblDefault;
 JScrollPane jScroll;
 
    public PnlTablas(){

        pnlTitle = new JPanel();
        lblTitle = new JLabel("test");
        
        InicializarTabla();
        
        pnlTitle.setSize(200,30);
        pnlTitle.setBackground(Color.yellow);
        pnlTitle.add(lblTitle);
        pnlTitle.setVisible(true);
        
        this.setLayout(new BorderLayout());
        this.setSize(200,200);
        this.setVisible(true);
        this.setBackground(Color.WHITE);
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(jScroll,BorderLayout.CENTER);
    
    }
    
    public PnlTablas(int tipo){
        pnlTitle = new JPanel();
        if(tipo==0)
            lblTitle = new JLabel("Automata");
        else lblTitle = new JLabel("Arbol");
        
        
        pnlTitle.setSize(200,30);
        pnlTitle.setBackground(Color.yellow);
        pnlTitle.add(lblTitle);
        pnlTitle.setVisible(true);
        
        this.setLayout(new BorderLayout());
        this.setSize(200,200);
        this.setVisible(false);
        this.setBackground(Color.BLUE);
        this.add(pnlTitle,BorderLayout.NORTH);
    }
    
    public void setTitle(String titulo){
        lblTitle.setText(titulo);
    }
    
    public void InicializarTabla(){
        tblDefault = new JTable();
        
        tblDefault.setModel(new DefaultTableModel(
    
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
                },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));

        jScroll.setViewportView(tblDefault);
    }
}
