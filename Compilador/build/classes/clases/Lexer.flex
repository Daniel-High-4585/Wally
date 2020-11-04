package clases;

import static clases.Tokens.*;
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import ventanas.Editor;
%%

%{
    public String lexeme;
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
%}

%class Lexer
%cupsym Simbolos
%cup
%public
%line
%char
%column
%full
%ignorecase
%unicode

L=[a-zA-Z]+
D=[0-9]+
BLANCO=[ \t\r\n]*
BLANK=[ ]?
C=[\"]+

%%

<YYINITIAL> "," {   lexeme = "separador";
                    System.out.println("Reconoció "+yytext()+" separador"); 
                    return new Symbol(Simbolos.separador,               yycolumn, yyline, yytext());}

<YYINITIAL> "." {   lexeme = "punto";
                    System.out.println("Reconoció "+yytext()+" punto"); 
                    return new Symbol(Simbolos.punto,                   yycolumn, yyline, yytext());}

<YYINITIAL> "==" {   lexeme="igual_que";
                    System.out.println("Reconoció "+yytext()+" igual_que"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("condicion");
                    return new Symbol(Simbolos.igual_que,               yycolumn, yyline, yytext());}

<YYINITIAL> "<" {   lexeme="menor_que";
                    System.out.println("Reconoció "+yytext()+" menor_que"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("condicion");
                    return new Symbol(Simbolos.menor_que,               yycolumn, yyline, yytext());}

<YYINITIAL> ">" {   lexeme="mayor_que";
                    System.out.println("Reconoció "+yytext()+" mayorr_que"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("condicion");
                    return new Symbol(Simbolos.mayor_que,               yycolumn, yyline, yytext());}

<YYINITIAL> "<=" {   lexeme="menor_igual_que";
                    System.out.println("Reconoció "+yytext()+" menor_igual_que"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("condicion");
                    return new Symbol(Simbolos.menor_igual_que,               yycolumn, yyline, yytext());}

<YYINITIAL> ">=" {   lexeme="mayor_igual_que";
                    System.out.println("Reconoció "+yytext()+" mayor_igual_que"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("condicion");
                    return new Symbol(Simbolos.mayor_igual_que,               yycolumn, yyline, yytext());}

<YYINITIAL> "=" {   lexeme = "igual";
                    System.out.println("Reconoció "+yytext()+" igual"); 
                    return new Symbol(Simbolos.igual,              yycolumn, yyline, yytext());}

<YYINITIAL> "!=" {   lexeme = "diferente";
                    System.out.println("Reconoció "+yytext()+" diferente_que"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("condicion");
                    return new Symbol(Simbolos.diferente_que,              yycolumn, yyline, yytext());}

<YYINITIAL> "+" {   lexeme = "suma";
                    System.out.println("Reconoció "+yytext()+" suma"); 
                    return new Symbol(Simbolos.suma,                    yycolumn, yyline, yytext());}

<YYINITIAL> "-" {   lexeme = "resta";
                    System.out.println("Reconoció "+yytext()+" resta"); 
                    return new Symbol(Simbolos.resta,                   yycolumn, yyline, yytext());}

<YYINITIAL> "*" {   lexeme = "multiplicacion";
                    System.out.println("Reconoció "+yytext()+" multiplicacion"); 
                    return new Symbol(Simbolos.multiplicacion,          yycolumn, yyline, yytext());}

<YYINITIAL> "/" {   lexeme = "division";
                    System.out.println("Reconoció "+yytext()+" division"); 
                    return new Symbol(Simbolos.division,                yycolumn, yyline, yytext());}

<YYINITIAL> "{" {   lexeme = "delimitador_izquierdo";
                    System.out.println("Reconoció "+yytext()+" delimitador_izquierdo"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("{");
                    return new Symbol(Simbolos.delimitador_izquierdo,   yycolumn, yyline, yytext());}

<YYINITIAL> "}" {   lexeme = "delimitador_derecho";
                    System.out.println("Reconoció "+yytext()+" delimitador_derecho"); 
                    if(Editor.estructura_si>0){
                    Editor.elementosPila.add("}");
                    Editor.estructura_si=0;
                    }
                    return new Symbol(Simbolos.delimitador_derecho,     yycolumn, yyline, yytext());}

<YYINITIAL> "(" {   lexeme = "parentesis_abierto";
                    System.out.println("Reconoció "+yytext()+" parentesis_abierto"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add("(");
                    return new Symbol(Simbolos.parentesis_abierto,      yycolumn, yyline, yytext());}

<YYINITIAL> ")" {   lexeme = "parentesis_cerrado";
                    System.out.println("Reconoció "+yytext()+" parentesis_cerrado"); 
                    if(Editor.estructura_si>0)
                    Editor.elementosPila.add(")");

                    return new Symbol(Simbolos.parentesis_cerrado,      yycolumn, yyline, yytext());}

<YYINITIAL> ";" {   lexeme="fin_linea";
                    System.out.println("Reconoció "+yytext()+" fin_linea"); 
                    return new Symbol(Simbolos.fin_linea,               yycolumn, yyline, yytext());}

<YYINITIAL> rueda {   lexeme="rueda";
                    System.out.println("Reconoció "+yytext()+" rueda"); 
                    return new Symbol(Simbolos.rueda,               yycolumn, yyline, yytext());}
<YYINITIAL> base {   lexeme="base";
                    System.out.println("Reconoció "+yytext()+" base"); 
                    return new Symbol(Simbolos.base,               yycolumn, yyline, yytext());}
<YYINITIAL> sensorProximidad {   lexeme="sensorProximidad";
                    System.out.println("Reconoció "+yytext()+" sensorProximidad"); 
                    return new Symbol(Simbolos.sensorProximidad,               yycolumn, yyline, yytext());}

<YYINITIAL> comienzo {   lexeme="comienzo";
                    System.out.println("Reconoció "+yytext()+" comienzo"); 
                    return new Symbol(Simbolos.comienzo,               yycolumn, yyline, yytext());}

<YYINITIAL> funcion {   lexeme="funcion";
                    System.out.println("Reconoció "+yytext()+" funcion"); 
                    return new Symbol(Simbolos.funcion,               yycolumn, yyline, yytext());}

<YYINITIAL> si {   lexeme="si";
                    System.out.println("Reconoció "+yytext()+" si"); 
                    Editor.estructura_si++;
                    Editor.control_si=true;
                    Editor.elementosPila.add("si");
                    VariablesEnsamblador.palabra_si=true;
                    return new Symbol(Simbolos.si,               yycolumn, yyline, yytext());
                    
                }
<YYINITIAL> sino {   lexeme="sino";
                    VariablesEnsamblador.palabra_si=false;
                    System.out.println("Reconoció "+yytext()+" sino"); 
                    return new Symbol(Simbolos.sino,               yycolumn, yyline, yytext());}

<YYINITIAL> mientras {   lexeme="mientras";
                    System.out.println("Reconoció "+yytext()+" mientras"); 
                    return new Symbol(Simbolos.mientras,               yycolumn, yyline, yytext());}

<YYINITIAL> avanzar {   lexeme="avanzar";
                    System.out.println("Reconoció "+yytext()+" avanzar"); 

if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.avanzar,               yycolumn, yyline, yytext());}
            
<YYINITIAL> encenderWALLY {   lexeme="encenderWALLY";
                    System.out.println("Reconoció "+yytext()+" encenderWALLY"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.encenderWALLY,               yycolumn, yyline, yytext());
}

<YYINITIAL> apagarWALLY {   lexeme="apagarWALLY";
                    System.out.println("Reconoció "+yytext()+" apagarWALLY"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                    return new Symbol(Simbolos.apagarWALLY,               yycolumn, yyline, yytext());
                        }
}

<YYINITIAL> reversa {   lexeme="reversa";
                    System.out.println("Reconoció "+yytext()+" reversa"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.reversa,               yycolumn, yyline, yytext());
}

<YYINITIAL> rotarIzquierda {   lexeme="rotarIzquierda";
                    System.out.println("Reconoció "+yytext()+" rotarIzquierda"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.rotarIzquierda,               yycolumn, yyline, yytext());
}

<YYINITIAL> rotarDerecha {   lexeme="rotarDerecha";
                    System.out.println("Reconoció "+yytext()+" rotarDerecha"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.rotarDerecha,               yycolumn, yyline, yytext());

}

<YYINITIAL> subirBase {   lexeme="subirBase";
                    System.out.println("Reconoció "+yytext()+" subirBase"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.subirBase,               yycolumn, yyline, yytext());
}

<YYINITIAL> bajarBase {   lexeme="bajarBase";
                    System.out.println("Reconoció "+yytext()+" bajarBase"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.bajarBase,               yycolumn, yyline, yytext());
}

<YYINITIAL> avanzarRueda {   lexeme="rotarIzquierda";
                    System.out.println("Reconoció "+yytext()+" rotarIzquierda"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.rotarIzquierda,               yycolumn, yyline, yytext());

}

<YYINITIAL> espera {   lexeme="espera";
                    System.out.println("Reconoció "+yytext()+" espera"); 
if(Editor.estructura_si>0)
                        {
                            Editor.elementosPila.add("cuerpo");   
                        }
                    return new Symbol(Simbolos.espera,               yycolumn, yyline, yytext());
}

<YYINITIAL> 
            verdadero |
            falso |
            ciclo |
            condicion |
            tempo |
            num |
            clase |
            cad {   lexeme = "Reservada";
                    System.out.println("Reconoció "+yytext()+" Reservada");
                    return new Symbol(Simbolos.reservada, yycolumn, yyline, yytext());}

 [0-9]+? { lexeme = "Numero";
                     System.out.println("Reconoció "+yytext()+" Numero");
                     return new Symbol(Simbolos.Numero, yycolumn, yyline, yytext());}

//([0-9]+((\-|\/|\+|\*)[0-9]+)+) { lexeme = "Operacion";
  //                   System.out.println("Reconoció "+yytext()+" Operacion");
  //                   return new Symbol(Simbolos.Operacion, yycolumn, yyline, yytext());}

{C}([a-zA-Z]|{BLANCO}|[-]|[:]|[\/]|[,]|[0-9])*{C} { lexeme = "cadena";
                                                   System.out.println("Reconoció "+yytext()+" Cadena");
                                                   return new Symbol(Simbolos.Cadena, yycolumn, yyline, yytext());}

{BLANK}[a-z]+{D}*([A-Z]*([a-z]|{D})*) { lexeme = "Identificador";
                                        System.out.println("Reconoció "+yytext()+" Identificador");
                                        return new Symbol(Simbolos.Identificador, yycolumn, yyline, yytext());}

{BLANK}([A-Z]([a-z]|{D})+|([_]([a-z]|{D})+)) { lexeme = "Clase";
                                               System.out.println("Reconoció "+yytext()+" Clase");
                                               return new Symbol(Simbolos.Clase, yycolumn, yyline, yytext());}

//Espacios
{BLANCO} {/* ignore */}

//Errores lexicos

. {System.out.println("Error Lexico "+yytext()+" Linea "+yyline+" Columna "+yycolumn+" ");

    String descError = "Error Lexico "+yytext()+" Linea "+(yyline+1)+" Columna "+(yycolumn+1);

    Editor.arrayError.add(new ErrorLink(descError,"Caracter no identificado.",yyline+1,yycolumn+1,yytext()));
    //Editor.setError();

    TError datos = new TError(yytext(),yyline,yycolumn,"Error Lexico","Simbolo no existe en el lenguaje");
    TablaEL.add(datos);

    }


//numero de error
//numero de error
//tipo de erro
//descripcion de erro
//posible sugerencia
//link
//que produccion no llegó a un terminal
//si es un arbol, seguimiento del nodo
//automata en que estado se quedó




