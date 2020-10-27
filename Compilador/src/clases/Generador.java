package clases;
import java.io.PrintStream;
import ventanas.Editor;
public class Generador {

	public static final int ASIG = 5;
	public static final int GOTO = 6;
	public static final int LABEL = 7;
	public static final int IFEQ = 8;
	public static final int IFLT = 9;
	public static final int PRINT = 10;
    	public final static int HALT = 11; 
        public static final int NVAR = 12; 
        public static final int SEGMENTO_VARS = 13;
        /***********************************************/
        public static final int MAS = 1;
	public static final int MENOS = 2;
	public static final int POR = 3;
	public static final int DIV = 4;
        
        public static final int ENCENDER = 14;
        public static final int APAGAR = 15;
        public static final int ESPERA = 16;
        public static final int AVANZAR = 17;
        public static final int AVANZAR_CASILLAS = 18;
        public static final int AVANZAR_RUEDA = 19;
        public static final int REVERSA = 20;
        public static final int REVERSA_CASILLAS = 21;
        public static final int GIRAR_DERECHA = 22;
        public static final int GIRAR_IZQUIERDA = 23;
        public static final int CICLO = 24;
        public static final int BASE = 25;
        public static final int CONDICION = 26 ;
        public static final int FIN=27;
        public static final int DEC_RUEDAS=28;
        public static final int INICIO=29;
        public static final int DECLARACION_COMPLETA=30;
        public static final int MAIN=31;
        public static final int DEC_BASE=32;
        public static final int DEC_SENSOR_PROXIMIDAD=33;
        public static final int SUBIR_BASE=34;
        public static final int BAJAR_BASE=35;        
        public static final int MIENTRAS_SIN=36;
        public static final int MIENTRAS=37;
        public static final int SI=38;
        public static final int ELSE=39;
        /***********************************************/

	public static int contadorTemp = 0;
	public static int contadorEtiq = 0;
	protected static PrintStream out = System.out;

	public static void gc(int operacion, String arg1, String arg2, String resultado) {
            switch(operacion) {
                case SI:
                    String auxiliar="";
                    for(int i=0;i<VariablesEnsamblador.llamadas_si.size();i++){
                        auxiliar+="	"+VariablesEnsamblador.llamadas_si.get(i)+"\n";             
                    }
                    Editor.txtIntermedio.append("\nSI "+resultado+"\n");
                    Editor.txtEnsamblador.append(
"\n"+resultado+":\n"
+ auxiliar+
"	return\n"              
                    );
                    break;
                case ELSE:
                    String auxiliar2="";
                    for(int i=0;i<VariablesEnsamblador.llamadas_si.size();i++){
                        auxiliar2+="	"+VariablesEnsamblador.llamadas_si.get(i)+"\n";             
                    }
                    Editor.txtIntermedio.append("\nNO "+resultado+"\n");
                    Editor.txtEnsamblador.append(
"\n"+resultado+":\n"
+ auxiliar2+
"	return\n"              
                    );                    
                    break;
                case MIENTRAS_SIN:
                    Editor.txtIntermedio.append("\nESTRUCTURA WHILE:"
                            + "");
                    Editor.txtEnsamblador.append("");
                    break;                    
                case SUBIR_BASE:
                        Editor.txtIntermedio.append(
"\nSUBIR BASE:\n"+ 
"move PUERTOA,080h\n"
                        );
                        Editor.txtEnsamblador.append(
"\nbaseup:\n" +
"	movlw 080h\n" +
"	movwf PUERTOA\n" +
"	return\n"
                        );
                    break;
                case BAJAR_BASE:
                        Editor.txtIntermedio.append(
"\nBAJAR BASE:\n"+ 
"move PUERTOA,040h\n");
                        Editor.txtEnsamblador.append(
"\nbasedown:\n" +
"	movlw 040h\n" +
"	movwf PUERTOA\n" +
"	return\n"
                        );
                    break;                    
                case MAIN:
//                    Editor.txtIntermedio.append("\nFIN INICIO\n");
//                    Editor.txtEnsamblador.append("\n\n  goto inicio\n");
                    break;
                case DECLARACION_COMPLETA:
                    Editor.txtIntermedio.append(
"\nDECLARACION COMPLETA "+arg1+"\n"
                    );
                    Editor.txtEnsamblador.append(
"\nDELAY1 EQU 030h\n" +
"DELAY2 EQU 031h\n" +
"\nconfig:\n" +
"	bsf 03h,5\n" +
"   	movlw 00h\n" +
"	movwf CONFA\n" +
"	movwf CONFB\n" +
"	bcf 03h,5\n");
                    break;
                case INICIO:
                    Editor.txtIntermedio.append(
"\nCONFIGURACION INICIAL\n"
                    );
                    Editor.txtEnsamblador.append(
"	ORG 0x00\n" +
"_STATUS EQU 03h\n");
                    break;
                case DEC_BASE:
                    arg1=arg1.replace(" ", "");
                    Editor.txtIntermedio.append(
"\nDECLARACION BASE "+arg1+"\n");
                    Editor.txtEnsamblador.append(
"\n"+arg1+"A EQU 28h\n"
+arg1+"B EQU 29h\n");
                    break;
                case DEC_SENSOR_PROXIMIDAD:
                    arg1=arg1.replace(" ", "");
                    Editor.txtIntermedio.append(
"DECLARACION SENSOR_PROXIMIDAD "+arg1+"\n");
                    Editor.txtEnsamblador.append(
"\n"+arg1+" EQU 32h\n");
                    break;
                case DEC_RUEDAS:
                    arg1=arg1.replace(" ", "");
                    String A[]=arg1.split(",");
                    Editor.txtIntermedio.append(
"\nCONFIGURACION A,B\n"+
"DEC PORT A,B\n"
                    );
                    Editor.txtEnsamblador.append(
"CONFA EQU 085h\n" +
"CONFB EQU 086h\n" +
"\nPUERTOA EQU 05h\n" +
"PUERTOB EQU 06h\n"
                    );
                    for (int i=0,o=20;i<A.length;i++, o++){
                        Editor.txtIntermedio.append(
"DECLARACION RUEDA "+ A[i]+"\n"
                        );
                        Editor.txtEnsamblador.append(
"\n"+A[i]+"A"+" EQU 0"+o+"h\n"
                        );
                        o++;
                        Editor.txtEnsamblador.append(
A[i]+"B"+" EQU 0"+o+"h\n"
                        );                        
                    }
                    break;
                case FIN:
                        Editor.txtIntermedio.append(
"\nFIN;\n"
                        );
                        Editor.txtEnsamblador.append(
"\nretardogiro:\n" +
"	movlw 03h\n" +
"	movwf DELAY1\n" +
"	movlw 01Bh\n" +
"	movwf DELAY2\n" +
"delaygiro:\n" +
"	decfsz DELAY1,1\n" +
"	goto delaygiro\n" +
"	decfsz DELAY2,1\n" +
"	goto delaygiro\n" +
"	return\n"+ 
"\nretardoSB:\n" +
"	movlw 03h\n" +
"	movwf DELAY1\n" +
"	movlw 014h\n" +
"	movwf DELAY2\n" +
"delaySB:\n" +
"	decfsz DELAY1,1\n" +
"	goto delaySB\n" +
"	decfsz DELAY2,1\n" +
"	goto delaySB\n" +
"	return\n"+ 
"\nretardoBB:\n" +
"	movlw 03h\n" +
"	movwf DELAY1\n" +
"	movlw 013h\n" +
"	movwf DELAY2\n" +
"delayBB:\n" +
"	decfsz DELAY1,1\n" +
"	goto delayBB\n" +
"	decfsz DELAY2,1\n" +
"	goto delayBB\n" +
"	return\n"+ 
"\napagarbase:\n" +
"	movlw 00h\n" +
"	movwf PUERTOA\n" +
"	movwf PUERTOB\n" +
"	return\n"+ 
"\nfin:\n"+ 
"end \n"
                        );
                    break;
                case ASIG:
                        out.println("   " + resultado + " = " + arg1 + ";");
                        break;
                case IFEQ:
                        out.println("   if (" + arg1 + " == " + arg2 + ") goto " + resultado + ";");
                        break;
                case IFLT:
                        out.println("   if (" + arg1 + " < " + arg2 + ") goto " + resultado + ";");
                        break;
                case GOTO:
                        out.println("   goto " + resultado + ";");
                        break;
                case LABEL:
                        out.println(resultado + ":");
                        break;
                case PRINT:
                        out.println("   print " + resultado + ";");
                        break;
                case HALT:
                        out.println("   halt;");
                        break;
                case NVAR:
                        Editor.txtIntermedio.setText(" var "+arg1+";");
                        out.println(" var "+arg1+";");
                        break;
                case SEGMENTO_VARS: 
                        out.println(" START_VARS;");
                        break;
                /******************************************/
                case MAS:
                        Editor.txtIntermedio.append(resultado + " = " + arg1 + ";\n");
                        break;
                case MENOS:
                        Editor.txtIntermedio.append(resultado + " = " + arg1 + ";\n");
                        break;
                case POR:
                        Editor.txtIntermedio.append(resultado + " = " + arg1 +  ";\n");
                        break;
                case DIV:
                        Editor.txtIntermedio.append(resultado + " = " + arg1 + ";\n");
                        break;
                case CONDICION:
                        Editor.txtIntermedio.append(
"\nEVALUAR "+arg1+" "+resultado+","+arg2+";\n"
                        );
                    break;
                case ENCENDER:
/*
                        Editor.txtIntermedio.append(
"\nENCENDER: "+ 
"PinUp PortA,PORTB;\n"
                        );
                        Editor.txtEnsamblador.append(
"\n;encender: \n"
                        );
*/
                        break;
                case APAGAR:
                        Editor.txtIntermedio.append(
"\nAPAGAR:\n"+
"PinDown PortA,PortB;\n"
                        ); 
                        Editor.txtEnsamblador.append(
"\napagar:\n" +
"	movlw 00h\n" +
"	movwf PORTA\n" +
"	movlw 00h\n" +
"	movwf PORTB\n"+
"	return\n"
                        );
                        break;
                case ESPERA:
                        Editor.txtIntermedio.append(
"\nespera;\n"
                        );
                        Editor.txtEnsamblador.append(
"\nespera:\n" +
"	movlw 03h\n" +
"	movwf DELAY1\n" +
"	movlw 010h\n" +
"	movwf DELAY2\n" + 
"delay:\n" +
"	decfsz DELAY1,1\n" +
"	goto delay\n" +
"	decfsz DELAY2,1\n" +
"	goto delay\n" +
"	return\n"
                        );
                        break;
                case AVANZAR:
                        Editor.txtIntermedio.append(
"\nAVANZAR:\n" +
"move PortA,0fh;\n"
                        );
                        Editor.txtEnsamblador.append(
"\navanzar:\n" +
"	movlw 0fh\n" +
"	movwf PUERTOA\n" +
"	return\n"
                            );
                        break;
                case AVANZAR_CASILLAS:
                    Editor.txtIntermedio.append(
"\nAVANZAR_CASILLAS:\n" +
"move PortA,0fh;\n"+
"retardo,"+arg1
                        );
                    Editor.txtEnsamblador.append(
"\navanzar:\n" +
"	movlw 0fh\n" +
"	movwf PUERTOA\n" +
"	return\n"
                        );                            
                        break;
                case REVERSA:
                        Editor.txtIntermedio.append(
"\nREVERSA:\n"+
"move PortB,0fh;\n"
                            );  
                        Editor.txtEnsamblador.append(
"\nreversa:\n"+
"	movlw 0fh\n" +
"	movwf PUERTOB\n" +
"	return\n"
                            );                                
                        break;                                  
                case REVERSA_CASILLAS:
                        Editor.txtIntermedio.append(
"\nREVERSA_CASILLAS:\n" +
"move PortA,0fh;\n"+
"retardo,"+arg1
                            );
                        Editor.txtEnsamblador.append(
"\nreversa:\n" +
"	movlw 0fh\n" +
"	movwf PUERTOB\n" +
"	return\n"
                            );                                
                        break;        
                case AVANZAR_RUEDA:
                    Editor.txtIntermedio.append(
                            "\nAVANZAR_RUEDA:\n"
                    );
                    Editor.txtEnsamblador.append(
                            "\navanzar_rueda: \n"
                    );
                    if(arg2.equals('+')){
                        Editor.txtIntermedio.append(
                            "PinUp PortA,"+resultado+";\n"+ 
                            "delay(5,"+arg1+");"+
                            "pinDown PortA,"+resultado+";\n"
                        );   
                        Editor.txtEnsamblador.append(
                            "   BSF PortA,"+resultado+";\n"+ 
                            "   delay(5,"+arg1+");\n"+
                            "   BCF PortA,"+resultado+";\n"
                        );                                 
                    }                           
                    if(arg2.equals('-')){
                        Editor.txtIntermedio.append(
                            "PinUp PortB,"+resultado+";\n"+ 
                            "delay(5,"+arg1+");\n"+
                            "pinDown PortB,"+resultado+";\n"
                        );
                        Editor.txtEnsamblador.append(
                            "BSF PortB,"+resultado+";\n"+ 
                            "delay(5,"+arg1+");\n"+
                            "BCD PortB,"+resultado+";\n"                                        
                        );                         
                    }
                    break;
                case GIRAR_DERECHA:
                    Editor.txtIntermedio.append(
"\nGIRAR_DERECHA:\n"+
"move PUERTOA,0Ah;\n" +
"move PUERTOB,05h;\n" +
"llamar retardogiro;\n"
                    );
                    Editor.txtEnsamblador.append(
"\nderecha:\n"+
"	movlw 0Ah\n" +
"	movwf PUERTOA\n" +
"	movlw 05h\n" +
"	movwf PUERTOB\n" +
"	call retardogiro\n" +
"	return\n"
                    );                              
                    break;
                case GIRAR_IZQUIERDA:
                    Editor.txtIntermedio.append(
"\nGIRAR_IZQUIERDA:\n"+
"move PUERTOA,06h;\n" +
"move PUERTOB,0Ah;\n" +
"llamar retardogiro;\n"
                    ); 
                    Editor.txtEnsamblador.append(
"\nizquierda:"+
"\n	movlw 05h\n" +
"	movwf PUERTOA\n" +
"	movlw 0Ah\n" +
"	movwf PUERTOB\n" +
"	call retardogiro\n" +
"	return\n"
                    ); 
                    break;
                case BASE:
                    Editor.txtIntermedio.append(
"\nBASE:\n"+
"   move PortA,080h;\n"
                    );    
                    Editor.txtEnsamblador.append(
"\nbaseup:\n"+
"	movlw 080h\n" +
"	movwf PUERTOA\n" +
"	return"
                    );                               
                    break;
                /******************************************/
                default:
                        System.err.println("Error en la generación de código");
            }
	}

	public static String nuevaTemp() {
		return "t" + contadorTemp++;
	}

	public static String nuevaEtiq() {
		return "L" + contadorEtiq++;
	}
}