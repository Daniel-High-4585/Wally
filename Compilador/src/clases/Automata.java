package clases;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.Automaton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Automata {
    public static String b;
    public static RegExp r = new RegExp(b);
    

public static void main(String[] args) {
        Automaton a = r.toAutomaton();
        System.out.println(a.toDot());
        System.out.println(a.run("a"));
        
    }
    
}
