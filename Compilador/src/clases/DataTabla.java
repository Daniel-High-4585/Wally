package clases;

/**
 *
 * @author oliveros
 */
public class DataTabla {
    public static String lexema;
    public static int linea,columna;
    public DataTabla(){
    
    }

    public static void setLexema(String lexema) {
        DataTabla.lexema = lexema;
    }

    public static void setLinea(int linea) {
        DataTabla.linea = linea;
    }

    public static void setColumna(int columna) {
        DataTabla.columna = columna;
    }

    public static String getLexema() {
        return lexema;
    }

    public static int getLinea() {
        return linea;
    }

    public static int getColumna() {
        return columna;
    }
    
}
