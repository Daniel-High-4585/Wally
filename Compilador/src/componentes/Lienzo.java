/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author carch
 */
public class Lienzo extends Canvas{
  public Lienzo(){  
        this.setBackground (Color.white);  
        this.setSize(500, 500);  
  } 
  public Lienzo(String color){
  
  }
  public void paint(Graphics g) 
            { 
            } 
    
  public static void caja(int x, int y,String cadena,Color color,Graphics g){
    //rectangulo color de fondo
    
    g.setColor(color); 
    g.fillRect(x, y, 200, 50);
    //rectangulo color contorno
    int blue = color.getBlue();
    color = new Color(color.getRed(),color.getGreen(),color.getBlue(),color.getAlpha()-50);
    g.setColor(color); 
    g.drawRect(x, y, 200, 50);
    
    
    //g.setColor( new Color(33,107,255,100));
    g.setColor(Color.white);
    g.setFont(new java.awt.Font("Monospaced", 0, 18));
    g.drawString(cadena, 50+x, 25+y);
  }
}