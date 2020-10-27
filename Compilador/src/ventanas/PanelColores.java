package ventanas;

import clases.TextLineNumber;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class PanelColores extends JPanel{
    public static JTextPane txt;
    public String red="(\\W)*(clase|inicio|funcion|sino)";
    public String blue="(\\W)*(num|tempo|rueda|base|sensorProximidad|avanzar|encenderVSort|apagarVSort|reversa|girarIzquierda|girarDerecha|subirBase|bajarBase|avanzarRueda|espera)";
    public String pink="(\\W)*(si|mientras|verdadero|falso|ciclo|condicion)";
    public static int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }
    public static int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
    
    public static void highLight(JTextComponent component, String patteren) {
    try {
        System.out.println("HighLight");
        Document doc = component.getDocument();
        String text = component.getText(0, doc.getLength());
        int pos = component.getCaretPosition();
        //int pos = 0;
        boolean found = false;
        int findLength = patteren.length();
        // Rest the search position if we're at the end of the document
        if (pos + findLength > doc.getLength()) {
            pos = findLength;
        }
        System.out.println("doc length: "+doc.getLength());
        while (pos + findLength <= doc.getLength()) {
            // Extract the text from teh docuemnt
            String match = doc.getText(pos, findLength).toLowerCase();
            System.out.println("highligt: "+match);
            // Check to see if it matches or request
            if (match.equals(patteren)) {
                found = true;
                break;
            }
            pos++;
        }

        if (found) {
            System.out.println("HighLight if in");
            component.setCaretPosition(pos);
            component.setSelectionStart(pos);
            component.setSelectionEnd(pos + patteren.length());
            component.getCaret().setSelectionVisible(true);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public PanelColores () {
        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attrBlue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
        final AttributeSet attrRed = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(235,10,10));
        final AttributeSet attrGreen = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.GREEN);
        final AttributeSet attrPink = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(172,53,168));
        final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLACK);
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if(text.substring(wordL, wordR).matches(red)){
                            setCharacterAttributes(wordL, wordR - wordL, attrRed, false);
                        } 
                        if(text.substring(wordL, wordR).matches(blue)){
                            setCharacterAttributes(wordL, wordR - wordL, attrBlue, false);
                        }
                        if(text.substring(wordL, wordR).matches(pink)){
                            setCharacterAttributes(wordL, wordR - wordL, attrPink, false);
                        }
                        if(!(text.substring(wordL,wordR).matches(red)||text.substring(wordL,wordR).matches(blue)||text.substring(wordL,wordR).matches(pink)))
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);
                if(text.substring(before, after).matches(red)){
                    setCharacterAttributes(before, after - before, attrRed, false);
                } 
                if(text.substring(before, after).matches(blue)){
                    setCharacterAttributes(before, after - before, attrBlue, false);
                }
                if(text.substring(before, after).matches(pink)){
                    setCharacterAttributes(before, after - before, attrGreen, false);
                }
                if(!(text.substring(before,after).matches(red)||text.substring(before,after).matches(blue)||text.substring(before,after).matches(pink)))
                    setCharacterAttributes(before, after - before, attrBlack, false);
                
            }
        };
        txt = new JTextPane(doc);
        JScrollPane jsp=new JScrollPane(txt);
        TextLineNumber tln = new TextLineNumber(txt);
        jsp.setRowHeaderView(tln);
        jsp.setPreferredSize( new Dimension(800, 395 ) );
        this.add(jsp);
        this.setSize(800,395);
    }
}
