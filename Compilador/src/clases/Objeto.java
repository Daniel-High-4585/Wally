package clases;
import ventanas.Editor;
/**
 *
 * @author cbreaker95
 */
public class Objeto {
    public static final int PINUP=0;
    public static final int PINDOWN=1;
    
    public static int contadorTemp = 0;
    public static int contadorEtiq = 0;
    public static void gc(int operacion, String arg1, String arg2, String resultado) {
        switch(operacion) {
            case PINUP:
                Editor.txtEnsamblador.append("BSF "+arg1+","+arg2+"\n");
            break;
            case PINDOWN:
                Editor.txtEnsamblador.append("BCF "+arg1+","+arg2+"\n");
            break;           
        }
    }
}
