package clases;

import java.util.ArrayList;
import ventanas.Editor;

/**
 *
 * @author cbreaker95
 */
public class VariablesEnsamblador {
    public static ArrayList<String> ruedas = new ArrayList<String>();
    public static ArrayList<String> base=new ArrayList<String>();
    public static ArrayList<String> sensor_proximidad= new ArrayList<String>();
    public static ArrayList<IdValor> variables=new ArrayList<IdValor>();
    public static ArrayList<String> llamadas=new ArrayList<String>();
    public static ArrayList<String> llamadas_si=new ArrayList<String>();
    public static ArrayList<String> llamadas_no=new ArrayList<String>();
    public static boolean palabra_si; //Variable que modifica el lexer segun la condicion y el token SI o SINO detectado
    public static int last,si_activado,mientras_activado, operador_logico; //si_activado, mientras_activado: Para saber si esta usando un if o while, 0 no se usa 1 se usa
    public static String opLog,varSi;
    public static boolean si,mientras,respuesta_si;
    public static boolean condicion(String operador, int v1, int v2){
                switch(opLog){
                    case "<":
                        if(v1<v2){
                            return true;
                        }
                        return false;
                    case ">":
                        if(v1>v2){
                            return true;
                        }
                        return false;
                    case "<=":
                        if(v1<=v2){
                            return true;
                        }
                        return false;
                    case ">=":
                        if(v1>=v2){
                            return true;
                        }
                        return false;
                    case "==":
                        if(v1==v2){
                            return true;
                        }
                        return false;
                    case "!=":
                        if(v1!=v2){
                            return true;
                        }
                        return false;                       
                } 
                return false;
    }
    
    public static boolean asignar(String elemento, int valor){
        for(int i=0;i<variables.size();i++){
            if(variables.get(i).id.equals(elemento)){
                variables.get(i).valor=valor;
                variables.get(i).estado="asignacion";
                return true;
            }          
        }
        IdValor vr=new IdValor(elemento,valor,"asignacion");
        variables.add(vr);
        return true;
    }
    public static IdValor buscar(String elemento,String metodo){
        for(int i=0;i<variables.size();i++){
            if(variables.get(i).id.equals(elemento)){
                if(variables.get(i).estado.equals("asignacion")){
                   return variables.get(i); 
                }else{
                    System.out.println("La variable no ha sido inicializada");
                    Editor.errores++;
                }
                
            }
            else{
                System.out.println("Variable no ha sido declarada");
                Editor.errores++;
                return null;
            }
        }
        return null;
    }
}
