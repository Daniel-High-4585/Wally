/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import static javax.swing.JOptionPane.showMessageDialog;
import jdk.nashorn.internal.codegen.CompilerConstants;
/**
 *
 * @author oliveros
 */

public class PnlTxtArea extends JPanel {
    private JTextArea txtSugerencias;
    private JScrollPane scrollTxtSug;
    
    public PnlTxtArea(){
        scrollTxtSug = new JScrollPane();
        txtSugerencias = new JTextArea();
        txtSugerencias.setEditable(true);
        txtSugerencias.setBackground(new java.awt.Color(71, 71, 75));
        txtSugerencias.setColumns(100);
        txtSugerencias.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtSugerencias.setForeground(new java.awt.Color(204, 255, 255));
        txtSugerencias.setRows(10);
        txtSugerencias.setText("Test");
        txtSugerencias.setMinimumSize(new Dimension(500,500) );
        scrollTxtSug.setViewportView(txtSugerencias);
        this.add(scrollTxtSug);
        this.setVisible(true);
    this.setSize(800,250);
    this.setBackground(new Color(71, 71, 75));
    }
    
    public PnlTxtArea(String msj){
        scrollTxtSug = new JScrollPane();
        txtSugerencias = new JTextArea();
        txtSugerencias.setEditable(true);
        txtSugerencias.setBackground(new java.awt.Color(71, 71, 75));
        txtSugerencias.setColumns(100);
        txtSugerencias.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtSugerencias.setForeground(new java.awt.Color(204, 255, 255));
        txtSugerencias.setRows(15);
        txtSugerencias.setText(msj);
        txtSugerencias.setMinimumSize(new Dimension(500,500) );
        scrollTxtSug.setViewportView(txtSugerencias);
        this.add(scrollTxtSug);
        this.setVisible(false);
    this.setSize(800,250);
    this.setBackground(new Color(71, 71, 75));
    }
    
    public void insertText(String cadena){
       txtSugerencias.setText(cadena);
    }
}
