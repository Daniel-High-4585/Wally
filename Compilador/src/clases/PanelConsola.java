/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import ventanas.PanelColores;
import static ventanas.PanelColores.txt;

public class PanelConsola extends JPanel implements MouseListener{
    private static JLabel lblDesc,lblLink,lblNumero,lblIconoConsola;
    ArrayList<JLabel> arrayLbl;
    JLabel[] arrayLbls = new JLabel[20];
    JPanel[] arrayPnls = new JPanel[20];
    Map attributes;
    int indice=0;
    Border empty;
   
    public PanelConsola(){
        empty = BorderFactory.createEmptyBorder();
        this.setBackground(new Color(71,71,75));
        this.setSize(750, 500);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS) );
        this.setBorder(empty);
        /*JScrollPane jsp=new JScrollPane(this);
        jsp.setRowHeaderView(this);
        jsp.setPreferredSize( new Dimension(800, 395 ) );
        this.add(jsp);
        */
        
        //this.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        for(int i=0;i<=100;i++){
         
        }
    }
    
    public void addLbl(ArrayList<ErrorLink> errorLink){
        
        errorLink.forEach(
                err -> {
                    //definir panel para cada linea de los errores
                    arrayPnls[indice] = new JPanel();
                    arrayPnls[indice].setSize(WIDTH, 700);
                    arrayPnls[indice].setLayout(new BoxLayout(arrayPnls[indice], BoxLayout.X_AXIS));
                    arrayPnls[indice].setBackground(new Color(71,71,75));
                    arrayPnls[indice].setAlignmentX(LEFT_ALIGNMENT);
                    
                    //definir label para el numero de error
                    lblNumero = new JLabel(indice+1+": ");
                    lblNumero.setForeground(new Color(204,255,255));
                    lblNumero.setFont(new java.awt.Font("Monospaced", 0, 14));
                    arrayPnls[indice].add(lblNumero);
                    arrayPnls[indice].repaint();
                    
                    //definir label que muestra la descripción del error
                    lblDesc = new JLabel(err.getDescripcionError()+" ");
                    lblDesc.setForeground(new Color(204,255,255));
                    lblDesc.setFont(new java.awt.Font("Monospaced", 0, 14));
                    arrayPnls[indice].add(lblDesc);
                    arrayPnls[indice].repaint();
                    
                    //generar link 
                    arrayLbls[indice] = new JLabel(err.getLink());
                    
                    arrayLbls[indice].setForeground(Color.ORANGE);
                    Font font = arrayLbls[indice].getFont();
                    arrayLbls[indice].setToolTipText("Click par ir al error.");
                    arrayLbls[indice].setName(err.getLexema());
                    arrayLbls[indice].addMouseListener(this);
                    arrayLbls[indice].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    attributes = font.getAttributes();
                    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    arrayLbls[indice].setFont(font.deriveFont(attributes));
                    arrayPnls[indice].add(arrayLbls[indice]);
                    
                    arrayPnls[indice].repaint();
                    this.add(arrayPnls[indice]);
                    indice++;
                }
        );
        indice = 0;      
                    
        
        
    }

    public void positiveMessage(ArrayList<ErrorLink> errorLink){
        
        errorLink.forEach(
                err -> {
                    //definir panel para cada linea de los errores
                    arrayPnls[indice] = new JPanel();
                    arrayPnls[indice].setSize(WIDTH, 700);
                    arrayPnls[indice].setLayout(new BoxLayout(arrayPnls[indice], BoxLayout.X_AXIS));
                    arrayPnls[indice].setBackground(new Color(71,71,75));
                    arrayPnls[indice].setAlignmentX(LEFT_ALIGNMENT);
                    
                    //definir label que muestra la descripción del error
                    lblDesc = new JLabel(err.getDescripcionError()+" ");
                    lblDesc.setForeground(new Color(20,255,20));
                    lblDesc.setFont(new java.awt.Font("Monospaced", 0, 18));
                    arrayPnls[indice].add(lblDesc);
                    arrayPnls[indice].repaint();
                    
                    //definir el icono que se va a agregar en la termianal
                    lblIconoConsola = new JLabel();
                    lblIconoConsola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/tick.png")));

                    //.setIcon(new ImageIcon(getClass().getResource("/images/checked.png")));
                    arrayPnls[indice].add(lblIconoConsola);
                    arrayPnls[indice].repaint();
                    
                    
                    arrayPnls[indice].repaint();
                    this.add(arrayPnls[indice]);
                }
        );     
    }
    
    public void negativeMessage(ArrayList<ErrorLink> errorLink){
        
        errorLink.forEach(
                err -> {
                    //definir panel para cada linea de los errores
                    arrayPnls[indice] = new JPanel();
                    arrayPnls[indice].setSize(WIDTH, 700);
                    arrayPnls[indice].setLayout(new BoxLayout(arrayPnls[indice], BoxLayout.X_AXIS));
                    arrayPnls[indice].setBackground(new Color(71,71,75));
                    arrayPnls[indice].setAlignmentX(LEFT_ALIGNMENT);
                    
                    //definir label que muestra la descripción del error
                    lblDesc = new JLabel(err.getDescripcionError()+" ");
                    lblDesc.setForeground(new Color(255,0,0));
                    lblDesc.setFont(new java.awt.Font("Monospaced", 1, 18));
                    arrayPnls[indice].add(lblDesc);
                    arrayPnls[indice].repaint();

                    //definir el icono que se va a agregar en la termianal
                    lblIconoConsola = new JLabel();
                    lblIconoConsola.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close.png")));
                    arrayPnls[indice].add(lblIconoConsola);
                    
                    arrayPnls[indice].repaint();
                    this.add(arrayPnls[indice]);
                }
        );     
    }
    
    @Override
    public void mouseClicked(MouseEvent arg0) {
        //int position = PanelColores.findFirstNonWordChar("rueda", 0);
        PanelColores.highLight(PanelColores.txt, arg0.getComponent().getName());
        //PanelColores.txt.setCaretPosition(position);
    }
    

    @Override
    public void mousePressed(MouseEvent arg0) {
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        arg0.getComponent().setForeground(Color.yellow);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        System.out.println(arg0.getComponent().getName());
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    
}
