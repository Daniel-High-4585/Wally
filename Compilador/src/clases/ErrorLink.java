package clases;

import javax.swing.JLabel;

public class ErrorLink {
    private String descripcionError,link,lexema;
    int fila,columna;
    public ErrorLink(){
    
    }
    
    public ErrorLink(String descripcionError,String link,int fila,int columan,String lexema){
        this.descripcionError = descripcionError;
        this.link = link;
        this.fila = fila;
        this.columna = columna;
        this.lexema = lexema;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public String getLink() {
        return link;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getLexema() {
        return lexema;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    
    
    
}
