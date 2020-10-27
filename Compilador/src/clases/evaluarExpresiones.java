package clases;


import java.util.Stack; 
  
public class evaluarExpresiones
{ 
    public static int evaluar(String expresion) 
    { 
        char[] tokens = expresion.toCharArray(); 
  
        Stack<Integer> valores = new Stack<Integer>(); 

        Stack<Character> operadores = new Stack<Character>(); 
  
        for (int i = 0; i < tokens.length; i++) 
        { 
            if (tokens[i] == ' ') 
                continue; 
 
            if (tokens[i] >= '0' && tokens[i] <= '9') 
            { 
                StringBuffer sbuf = new StringBuffer(); 
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 
                    sbuf.append(tokens[i++]); 
                valores.push(Integer.parseInt(sbuf.toString())); 
            } 
            else if (tokens[i] == '(') 
                operadores.push(tokens[i]); 
            else if (tokens[i] == ')') 
            { 
                while (operadores.peek() != '(') 
                  valores.push(applyOp(operadores.pop(), valores.pop(), valores.pop())); 
                operadores.pop(); 
            } 
            else if (tokens[i] == '+' || tokens[i] == '-' || 
                     tokens[i] == '*' || tokens[i] == '/') 
            { 
                while (!operadores.empty() && precedencia(tokens[i], operadores.peek())) 
                  valores.push(applyOp(operadores.pop(), valores.pop(), valores.pop())); 
  
                operadores.push(tokens[i]); 
            } 
        } 
        while (!operadores.empty()) 
            valores.push(applyOp(operadores.pop(), valores.pop(), valores.pop())); 
        return valores.pop(); 
    } 
  
    public static boolean precedencia(char op1, char op2) 
    { 
        if (op2 == '(' || op2 == ')') 
            return false; 
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
            return false; 
        else
            return true; 
    } 
     public static int applyOp(char op, int b, int a) 
    { 
        switch (op) 
        { 
        case '+': 
            return a + b; 
        case '-': 
            return a - b; 
        case '*': 
            return a * b; 
        case '/': 
            if (b == 0) 
                throw new
                UnsupportedOperationException("No se puede dividir en cero"); 
            return a / b; 
        } 
        return 0; 
    } 
    public static void main(String[] args) 
    { 
//        System.out.println(evaluarExpresiones.evaluar("10 + 2 * 6")); 
//        System.out.println(evaluarExpresiones.evaluar("100 * 2 + 12")); 
//        System.out.println(evaluarExpresiones.evaluar("100 * ( 2 + 12 )")); 
//        System.out.println(evaluarExpresiones.evaluar("100 * ( 2 + 12 ) / 14"));
        System.out.println(evaluarExpresiones.evaluar(javax.swing.JOptionPane.showInputDialog("Introduce una operacion")));
    } 
}