package ventanas;
import clases.Automatas;
import clases.ErrorLink;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import clases.Lexer;
import clases.Tokens;
import clases.sintactico;
import componentes.PanelTree;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import componentes.PnlTxtArea;
import componentes.PnlFunciones;
import componentes.pnlTree;
import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import clases.TError;
import clases.Tablas;
import clases.Generador;
import clases.PanelConsola;
import clases.VariablesEnsamblador;
import componentes.Lienzo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;




/**
 * @author cbreaker95
 */
public class Editor extends javax.swing.JFrame {
    public static int contador=0;
    public static int errores=0;
    public static int avanzar=0,subir=0,bajar=0,reversa=0,girarD=0,girarI=0,encender=0,apagar=0,espera=0;
    public static int base=0;
    public static String cade="";

    private PanelTree tree;
    private PnlTxtArea pnlSugerencias;
    private PnlFunciones pnlAutomata;
    private PnlFunciones pnlArbol;
    /***/
    private PanelColores codigo;
    /***/
    private ArrayList arrayToken;
    private ArrayList arrayLexema;
    private ArrayList arrayId;
    
    private int countIdentificador = 0;
    
    public static String nombre_archivo;
    
    TError sintaxError = new TError();
    DefaultTableModel tblDefault;
 
        
    public static PanelConsola pnlConsola;
    public static ArrayList<ErrorLink> arrayError;
    ErrorLink error = new ErrorLink();
    
    Graphics g;
    Lienzo lienzo;
    public static String[] arrayCadena;
    public static int y=0;
    
    //control estructura si
    public static ArrayList<String> elementosPila = new ArrayList<String>();

    public static int estructura_si = 0;
    public static Boolean control_si = false;
    
    public Editor() {
        initComponents();
        tree = new PanelTree();
        tree.setSize(600, 500);
        pnlTree.add(tree);
        jScrollPane1.setBorder(new BevelBorder(BevelBorder.RAISED));
        //pnlConsola.setText("Consola");
        this.arrayError = new ArrayList<>();
        this.arrayError.add(new ErrorLink("Consola",null,0,0,""));
        //pnlConsola.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        //pnlConsola.setForeground(new java.awt.Color(204, 255, 255));
        txtIntermedio.setText("Fecha \n 26/08/2019");
        txtIntermedio.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtIntermedio.setForeground(new java.awt.Color(204, 255, 255));
        txtEnsamblador.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtEnsamblador.setForeground(new java.awt.Color(204, 255, 255));        
        pnlSugerencias = new PnlTxtArea();
        pnlAutomata = new PnlFunciones(0);
        pnlArbol = new PnlFunciones(1);     
        codigo=new PanelColores();
        pnlCodigo.add(codigo);
        pnlCodigo.setPreferredSize( new Dimension(800,395));
        this.setLocationRelativeTo(this);
        Cursor hand=new Cursor(HAND_CURSOR);
        lblCorrer.setCursor(hand);
        tblDefault = (DefaultTableModel)tblIdentificadores.getModel();
        this.setSize(1366,768);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new java.awt.Color(84,91,112)); 
        defPanelConsola();
        setError();
        
        lienzo = new Lienzo();
        pnlFunciones.add(lienzo);

    }
    public static void notificarError(String v){
        //pnlConsola.append(v);
    }
    public static void setNombre_archivo(String nombre_archivo) {
        System.out.println(nombre_archivo+" x");
        Editor.nombre_archivo = nombre_archivo;
    }
    

    public void probarLexer()throws IOException{
        File fichero = new File("C:\\Users\\cbreaker95\\Desktop\\VSort\\code.vv");
        //File fichero = new File("C:\\Users\\carch\\Desktop\\VSort\\code.vv");
        PrintWriter writer;
        try{
            writer = new PrintWriter(fichero);
            writer.print(codigo.txt.getText());
            writer.close();
             
        }catch(FileNotFoundException ex){
           ex.printStackTrace();
        }
        Reader reader = new BufferedReader(new FileReader("C:\\Users\\cbreaker95\\Desktop\\VSort\\code.vv"));
        //Reader reader = new BufferedReader(new FileReader("C:\\Users\\carch\\Desktop\\VSort\\code.vv"));
        Lexer lexer = new Lexer(reader);
        sintactico parser = new sintactico(lexer);
        String respuesta = "";
        try{
            parser.parse();
            System.out.println("Tamaño errores "+ arrayError.size());
          if(arrayError.size()>0){
            /*respuesta += "Línea: "+lexer.yyline+" Columna "+lexer.yycolumn +" Lexema : "+lexer.lexeme;
            sintactico.TablaES.iterator().forEachRemaining(
                x -> {
                this.arrayError.add(new ErrorLink(x.getDescripcion(),x.getLexema(),x.getLinea()+1,x.getColumna(),lexer.lexeme));
                pnlConsola.append(
                    "Linea: "+(x.getLinea()+1)+
                    ", Columna: "+x.getColumna()+
                    ", "+x.getTipo()+
                    ", "+x.getDescripcion()+
                    " \""+x.getLexema()+"\"" );
               }
           );*/
            setError();
            this.arrayError.add(new ErrorLink("Compilación falló!",null,0,0,""));
            if(control_si){
            mostrarPilaNegativa();
            generarPila("si,(,condicion,),{,cuerpo,},;", 2);
            }else{
            g.setColor(Color.white);
            g.fillRect(0, 0, 500, 500);
            }
            setNegativeMessage();

           File f= new File("C:\\Users\\cbreaker95\\Desktop\\VSort\\codigo.asm"); 
           //File f= new File("C:\\Users\\carch\\Desktop\\VSort\\codigo.asm"); 
           f.delete();
           
           
           }else{
              
              if(control_si){
                mostrarPila();
                generarPila("si,(,condicion,),{,cuerpo,},;", 2);
                
            }else{
            g.setColor(Color.white);
            g.fillRect(0, 0, 500, 500);
            }
                //pnlConsola.append("\nCompilación terminada!");
                setError();
                
                this.arrayError.add(new ErrorLink("Compilación terminada!",null,0,0,""));
                setPositiveMessage();
                generarArchivo();
           }
           generarTablaIdentificadores();
           sintactico.TablaES.clear();
           sintactico.TablaId.clear();
           control_si = false;
        }catch(Exception e){
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jFrame1 = new javax.swing.JFrame();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        lblCorrer = new javax.swing.JLabel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIntermedio = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtEnsamblador = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblIdentificadores = new javax.swing.JTable();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnlCodigo = new javax.swing.JPanel();
        pnlFunciones = new javax.swing.JPanel();
        pnlTree = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnAbrir = new javax.swing.JMenuItem();
        mnGuardar = new javax.swing.JMenuItem();
        tablaSimbolos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        JMIGenerarAutomata = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        JMIGenerarArbol = new javax.swing.JMenuItem();

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("jMenu4");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(249, 173, 129));

        lblCorrer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/correr2.png"))); // NOI18N
        lblCorrer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCorrerMousePressed(evt);
            }
        });

        jSplitPane3.setDividerLocation(400);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane2.setDividerLocation(800);
        jSplitPane2.setDividerSize(4);

        jTabbedPane2.setBackground(new java.awt.Color(71, 71, 75));
        jTabbedPane2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(71, 71, 75), new java.awt.Color(71, 71, 75), java.awt.Color.white, new java.awt.Color(71, 71, 75)));
        jTabbedPane2.setForeground(new java.awt.Color(71, 71, 75));
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane2.setOpaque(true);

        jPanel3.setBackground(new java.awt.Color(71, 71, 75));

        jScrollPane1.setBackground(new java.awt.Color(71, 71, 75));
        jScrollPane1.setBorder(null);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Salida", jPanel3);

        txtIntermedio.setBackground(new java.awt.Color(71, 71, 75));
        txtIntermedio.setColumns(20);
        txtIntermedio.setRows(5);
        txtIntermedio.setText("\tORG 0x00\n_STATUS EQU 03h");
        jScrollPane2.setViewportView(txtIntermedio);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Intermedio", jPanel4);

        txtEnsamblador.setBackground(new java.awt.Color(71, 71, 75));
        txtEnsamblador.setColumns(20);
        txtEnsamblador.setRows(5);
        txtEnsamblador.setText("\tORG 0x00\n_STATUS EQU 03h");
        jScrollPane3.setViewportView(txtEnsamblador);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Ensamblador", jPanel5);

        jSplitPane2.setLeftComponent(jTabbedPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel1);

        tblIdentificadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Identificador", "Tipo", "Accion", "Valor"
            }
        ));
        jScrollPane4.setViewportView(tblIdentificadores);

        jSplitPane2.setRightComponent(jScrollPane4);

        jSplitPane3.setBottomComponent(jSplitPane2);

        jSplitPane1.setDividerLocation(800);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        javax.swing.GroupLayout pnlCodigoLayout = new javax.swing.GroupLayout(pnlCodigo);
        pnlCodigo.setLayout(pnlCodigoLayout);
        pnlCodigoLayout.setHorizontalGroup(
            pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlCodigoLayout.setVerticalGroup(
            pnlCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(pnlCodigo);

        pnlFunciones.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlFuncionesLayout = new javax.swing.GroupLayout(pnlFunciones);
        pnlFunciones.setLayout(pnlFuncionesLayout);
        pnlFuncionesLayout.setHorizontalGroup(
            pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1261, Short.MAX_VALUE)
        );
        pnlFuncionesLayout.setVerticalGroup(
            pnlFuncionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(pnlFunciones);

        jSplitPane3.setLeftComponent(jSplitPane1);

        pnlTree.setLayout(new java.awt.BorderLayout());

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 255));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 255), null));

        jMenu1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N

        mnAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnAbrir.setText("Abrir");
        mnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(mnAbrir);

        mnGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mnGuardar.setText("Guardar");
        jMenu1.add(mnGuardar);

        jMenuBar1.add(jMenu1);

        tablaSimbolos.setText("Tabla de simbolos");
        tablaSimbolos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaSimbolosMousePressed(evt);
            }
        });

        jMenuItem1.setText("Palabras Reservadas");
        tablaSimbolos.add(jMenuItem1);

        jMenuItem2.setText("Identificadores");
        tablaSimbolos.add(jMenuItem2);

        jMenuItem3.setText("Aritméticos");
        tablaSimbolos.add(jMenuItem3);

        jMenuItem4.setText("Agrupación");
        tablaSimbolos.add(jMenuItem4);

        jMenuBar1.add(tablaSimbolos);

        jMenu5.setText("Automatas");

        JMIGenerarAutomata.setText("Generar Automata");
        JMIGenerarAutomata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIGenerarAutomataActionPerformed(evt);
            }
        });
        jMenu5.add(JMIGenerarAutomata);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Arboles");

        JMIGenerarArbol.setText("Generar Arbol");
        JMIGenerarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIGenerarArbolActionPerformed(evt);
            }
        });
        jMenu6.add(JMIGenerarArbol);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlTree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSplitPane3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCorrer)
                .addGap(568, 568, 568))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblCorrer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAbrirActionPerformed
        codigo.txt.setText(abrirArchivo());
    }//GEN-LAST:event_mnAbrirActionPerformed

    private void lblCorrerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCorrerMousePressed
        try {
            
        
        this.arrayError = new ArrayList<>();
            
            avanzar=0;subir=0;bajar=0;reversa=0;girarD=0;girarI=0;encender=0;apagar=0;espera=0;base=0;
            contador=0;
            errores=0;
            txtIntermedio.setText("CONF INICIAL\n" );
            txtEnsamblador.setText("	ORG 0x00\n" +
"_STATUS EQU 03h\n\n");
            Generador.contadorEtiq=0;
            Generador.contadorTemp=0;
            VariablesEnsamblador.llamadas.clear();
            VariablesEnsamblador.variables.clear();
            VariablesEnsamblador.ruedas.clear();
            VariablesEnsamblador.base.clear();
            VariablesEnsamblador.llamadas_si.clear();
            VariablesEnsamblador.llamadas_no.clear();
            VariablesEnsamblador.sensor_proximidad.clear();
            VariablesEnsamblador.si_activado=0;
            VariablesEnsamblador.last=-1;
            //pnlConsola.setText("");
            //this.arrayError.add(new ErrorLink("",null,0,0,""));
            if(errores==0){
                probarLexer();
            }else{
                javax.swing.JOptionPane.showMessageDialog(this,"No se pudo generar el codigo ensamblador, se encontraron "+errores+" errores");
            }
            
        } catch (IOException ex) {
        }
        
    }//GEN-LAST:event_lblCorrerMousePressed
    public void generarArchivo(){
        BufferedWriter writer;
            try {
                if(errores==0){
                    String texto = txtEnsamblador.getText();
                    String vars="";
                    int inicio = texto.indexOf("inicio:")+8;

                    for(int i=0;i<VariablesEnsamblador.llamadas.size();i++){
                          vars+="	"+VariablesEnsamblador.llamadas.get(i)+"\n";             
                    }
                    txtEnsamblador.insert(vars, inicio);
                    txtEnsamblador.insert("", base);
                    writer = new BufferedWriter(new FileWriter("C:\\Users\\cbreaker95\\Desktop\\VSort\\ensamblador.asm",false));
                    //writer = new BufferedWriter(new FileWriter("C:\\Users\\carch\\Desktop\\VSort\\ensamblador.asm",false));

                    txtEnsamblador.write(writer);
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Archivo ensamblador generado con exito!","File Saved",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    txtEnsamblador.setText("");
                    txtIntermedio.setText("");
                }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error Occured");
            e.printStackTrace();
        }    
    }
    private void tablaSimbolosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSimbolosMousePressed
        
    }//GEN-LAST:event_tablaSimbolosMousePressed

    private void JMIGenerarAutomataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIGenerarAutomataActionPerformed
       
        //pnlArbol.setVisible(false);
        //pnlAutomata.setVisible(true);
        
    }//GEN-LAST:event_JMIGenerarAutomataActionPerformed

    private void JMIGenerarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIGenerarArbolActionPerformed
       // pnlFunciones.setTitle("ARBOL");        
        //pnlAutomata.setVisible(false);
        //pnlArbol.setVisible(true);
    }//GEN-LAST:event_JMIGenerarArbolActionPerformed
    public String abrirArchivo(){
        String aux="", texto="";
        try{
           FileNameExtensionFilter filter = new FileNameExtensionFilter("Codigo",".vv");
           JFileChooser file=new JFileChooser();
           file.setFileFilter(filter);
           file.showOpenDialog(this);
           File abre=file.getSelectedFile();
           if(abre!=null){     
              FileReader archivos=new FileReader(abre);
              BufferedReader lee=new BufferedReader(archivos);
              while((aux=lee.readLine())!=null){
                 texto+= aux+ "\n";
              }
                 lee.close();
            }    
           }
           catch(IOException ex){
             JOptionPane.showMessageDialog(null,ex+"" +
                   "\nNo se ha encontrado el archivo",
                         "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
            }
          return texto;   
    }
    
    public static void abrirarchivo(){
    String aux="", texto="";
     try {
            System.out.println("Abriendo archivo...");
            String currentDirectory = System.getProperty("user.dir");
            
            File objetofile = new File ("C:\\projects\\Lenguaje_Automatas_II\\Compilador\\src\\project\\clases\\"+nombre_archivo);
            //File objetofile = new File (currentDirectory+"\\src\\project\\clases\\"+nombre_archivo);
            System.out.println("Se abrió con exito, "+currentDirectory+"\\src\\project\\clases\\"+nombre_archivo);

            if(objetofile!=null)
       {     
          FileReader archivos=new FileReader(objetofile);
          BufferedReader lee=new BufferedReader(archivos);
          
          while((aux=lee.readLine())!=null)
          {
             // System.out.println("se econtro lo siguiente: "+aux);
             texto+= aux+ "\n";
          }
             lee.close();
        }
        PanelColores.txt.setText(texto);
     }catch (IOException ex) {

            System.out.println(ex);

     }
     
}   
    public void llenarArreglos(Tokens token, String lexema){
//        arrayToken.add(token);
        //arrayLexema.add(lexema);
        
        //pnlConsola.append(token.name());
    }
    
    public void generarTablaIdentificadores(){
        
    
        System.out.println("tamaño"+sintactico.TablaId.size());
        tblDefault.setRowCount(0);
        for(int i = 0; i<sintactico.TablaId.size();i++)
        {
            //System.out.println(i);
            //    System.out.println("antes de agregar a tabla "+sintactico.TablaId.get(i).lexema+" , "+sintactico.TablaId.get(i).tipo);
                tblDefault.addRow(new Object[]{i,sintactico.TablaId.get(i).lexema,sintactico.TablaId.get(i).tipo,sintactico.TablaId.get(i).accion,sintactico.TablaId.get(i).valor}); 
        }
               
        
        
    }
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editor().setVisible(true);
            }
        });
    }
    
    public void defPanelConsola(){
        this.pnlConsola = new PanelConsola();
        this.pnlConsola.setLocation(10, 10);
        this.pnlConsola.setVisible(true);
        
        jScrollPane1.setViewportView(pnlConsola);
    }
    
    public static void setError(){
        pnlConsola.removeAll();
        pnlConsola.addLbl(Editor.arrayError);
        pnlConsola.updateUI();
        
        Editor.arrayError.clear();
    }
    
    public static void setPositiveMessage(){
        pnlConsola.positiveMessage(Editor.arrayError);
        pnlConsola.updateUI();
        Editor.arrayError.clear();
    }
    
    public static void setNegativeMessage(){
        pnlConsola.negativeMessage(Editor.arrayError);
        pnlConsola.updateUI();
        Editor.arrayError.clear();
        
    }
    
    public void generarPila(String cadena,int TIPOPILA){
        System.out.println("Entro gp");
        y=0;
        cadena=cadena.replaceAll(" ","" );
        arrayCadena = cadena.split(",");
        this.g = lienzo.getGraphics();
                    
        System.out.println(""+arrayCadena.length+" \n");
        for (int i = 0; i < arrayCadena.length; i++) {
            System.out.println(""+arrayCadena[i]+" \n");
            System.out.println(""+i+"\n");
            switch(TIPOPILA){
                case 0:
                    System.out.println("caso1 \n");
                    lienzo.caja(200,y,arrayCadena[i],new Color(123,10,10,100),g);
                    break;
                case 1: 
                    lienzo.caja(0,y,arrayCadena[i],new Color(10,123,10,100),g); 
                    System.out.println("caso2 \n");
                    break;
                case 2: 
                    lienzo.caja(200,y,arrayCadena[i],new Color(10,10,123,100),g); 
                    System.out.println("caso2 \n");
                    break;
            }
                                System.out.println("salio \n");

            y+=50;
        }
        System.out.println("salió gp");
    }
    
    
    public void mostrarPila(){
        this.g = lienzo.getGraphics();
        System.out.println("pila positiva");
        elementosPila.forEach(
        x ->{
            lienzo.caja(0,y,x,new Color(10,123,10,100),g); 
            y+=50;
        }
        );
        y=0;
    }
    
    public void mostrarPilaNegativa(){
        this.g = lienzo.getGraphics();
        System.out.println("pila negativa");
        elementosPila.forEach(
        x ->{
            lienzo.caja(0,y,x,new Color(123,10,10,100),g);
            y+=50;
        }
        );
        y=0;
    }

    /*public void defTree(){
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
         
        //create the tree by passing in the root node
        tree = new JTree(root);
        pnlTree.add(tree);
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem JMIGenerarArbol;
    private javax.swing.JMenuItem JMIGenerarAutomata;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblCorrer;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JMenuItem mnAbrir;
    private javax.swing.JMenuItem mnGuardar;
    private javax.swing.JPanel pnlCodigo;
    private javax.swing.JPanel pnlFunciones;
    private javax.swing.JPanel pnlTree;
    private javax.swing.JMenu tablaSimbolos;
    private javax.swing.JTable tblIdentificadores;
    public static javax.swing.JTextArea txtEnsamblador;
    public static javax.swing.JTextArea txtIntermedio;
    // End of variables declaration//GEN-END:variables
}
