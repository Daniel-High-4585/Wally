/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author cbreaker95
 */
public class Automatas extends javax.swing.JFrame {

    /**
     * Creates new form Automatas
     */
    public Automatas() {
        initComponents();
        correcto();
    }
    public void mostrar(){
        ImageIcon fot=new ImageIcon("png.png");
        Icon icono=new ImageIcon(fot.getImage().getScaledInstance(lblFondo.getWidth(),lblFondo.getHeight(),Image.SCALE_DEFAULT));
        lblFondo.setIcon(icono);
        this.repaint();
    }
        public void dibujar(String dirDot,String dirPng)
    {
        try {
            ProcessBuilder pbuilder;
            pbuilder = new ProcessBuilder("dot","-Tpng","-o",dirPng,dirDot);
            pbuilder.redirectErrorStream(true);
            System.out.println("11******");
            pbuilder.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void correcto(){
        try{
//            RegExp r = new RegExp("(base)( )([a-z]+[0-9]*([A-Z]*([a-z]|[0-9])*))(\\;)");
            //Caso correcto RegExp r = new RegExp("([a-z]+[0-9]*([A-Z]*([a-z]|[0-9])*))(=)([0-9]+)(\\;)");
            //Caso falso RegExp r = new RegExp("([a-z]+[0-9]*([A-Z]*([a-z]|[0-9])*))(=)([a-zA-Z]+)(\\;)");
            //RegExp r = new RegExp("([a-z]+[0-9]*([A-Z]*([a-z]|[0-9])*))([0-9]+)(\\;)");
            RegExp r = new RegExp("([a-z]+[0-9]*([A-Z]*([a-z]|[0-9])*))(=)([0-9]+)");
            Automaton a = r.toAutomaton();
            try{
                crearTxt(a.toDot());
        }catch(IOException ex){
            
        }
            dibujar("archivo.txt","png.png");
            mostrar();
        }catch(Exception e){
        
        }        
    }
    public void crearTxt(String aux) throws IOException{
        String ruta="archivo.txt";
        File archivo=new File(ruta);
        BufferedWriter bw;
        if(archivo.exists()){
            bw=new BufferedWriter(new FileWriter(archivo));
            bw.write(aux);
            
        }
        else{
            bw=new BufferedWriter(new FileWriter(archivo));
            bw.write(aux);
            
        }
        bw.close();
    }

   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFondo, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Automatas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Automatas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Automatas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Automatas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Automatas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel lblFondo;
    // End of variables declaration//GEN-END:variables
}
