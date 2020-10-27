package componentes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import ventanas.Editor;

/**
 *
 * @author oliveros
 */
public class PanelTree extends JPanel{
    private JTree tree;
    private String path;
    private static List<String> directory;
    private int directorySize = 0;
    private DefaultMutableTreeNode[] nFiles;
    private DefaultMutableTreeNode[] nFolders;
    private String[] sFiles;
    private String[] sFolders;
    private static Pattern regexDriveLetter = Pattern.compile("^[a-zA-Z]:");
    private static Pattern regexSlashes = Pattern.compile("\\\\");
    private static Pattern regexUnc = Pattern.compile("^//");
    
    public PanelTree(){    
    this.setSize(600,600);
    getDataTree();
    configArrayNodes();
    configArrayStrings();
    crearNodos();}
    
    public PanelTree(String ruta){
    getDataTree();
    configArrayNodes();
    configArrayStrings();
    crearNodos();
            
    }
    
       
    public void getDataTree(){
    String currentDirectory = System.getProperty("user.dir");
    System.out.println("Ruta actual " + currentDirectory);
    path = currentDirectory+"\\src\\project\\";
        System.out.println("psth: "+path);
    try (Stream<Path> walk = Files.walk(Paths.get(currentDirectory+"\\src\\project"))) {
   
		
        directory = walk.filter(Files::isRegularFile)
                .map(x -> x.toString().replace(path,"")).collect(Collectors.toList());

               directory.forEach(System.out::println);
               
               directorySize = directory.size()-1;
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
    public static String translate(String pathname) {
        if (pathname == null) {
            return null;
    }
        pathname = regexDriveLetter.matcher(pathname).replaceAll("");
        pathname = regexSlashes.matcher(pathname).replaceAll("/");
        pathname = regexUnc.matcher(pathname).replaceAll("/mnt/");
        return pathname;
    }
    public void configArrayNodes(){
        String dirAux = "";
        String[] direccion;
        int countFolder = 0;
        int countFile = 0;
        
        //tamaño de arreglo para numero de nodos
        
        for(int i = 0; i <= directorySize ;i++){
           System.out.println("rutaaaaaaaaaaaaaaaaaaaaaaa-----------------------------> "+directory.get(i));
           direccion = directory.get(i).split("\\\\");
           System.out.println("tamaño de var direccion "+direccion.length);
           if(direccion.length>1){
               
               
               if(!direccion[0].equals(dirAux)){
                 countFolder++;
                 //
                 
               }                    
                   countFile++;
                   //System.out.println("---------"+direccion[1]); 
                  dirAux=direccion[0];
                              
           }
        }
        System.out.println("FOLDERS: "+countFolder+" FILES: "+countFile); 
        nFolders = new DefaultMutableTreeNode[countFolder];
        nFiles = new DefaultMutableTreeNode[countFile];
        sFolders = new String[countFolder];
        //sFiles = new String[countFile];
    }
    
    public void configArrayStrings(){
        String dirAux = "";
        String[] direccion;
        int countFolder = 0;
        int countFile = 0;
        
        //tamaño de arreglo para numero de nodos
        
        for(int i = 0; i <= directorySize;i++){
            
            
           direccion = directory.get(i).split("\\\\");
           if(direccion.length>1){
               
               
               if(!direccion[0].equals(dirAux)){
                 sFolders[countFolder] = direccion[0];
                 countFolder++;
                 
                 System.out.println(direccion[0]);
                 
               }                    
                   countFile++;
                   System.out.println("---------"+direccion[1]); 
                  dirAux=direccion[0];
                              
           }
        }
    }
    public void crearNodos(){
        String dirAux = "";
        String[] direccion;
        int countFolder = 0;
        int countFile = 0;
        int d = 0;
        
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        for(int i = 0; i<=nFolders.length-1;i++){
           direccion = directory.get(d).split("\\\\");
           
           if(direccion.length>1){
                                
                nFolders[i] = new DefaultMutableTreeNode(direccion[0]); //nodo folder
                System.out.println("folder: "+i+" nombre: "+direccion[0]);
                int f = 0;
                while(sFolders[i].equals(direccion[0])&&d<directory.size()){
                    direccion = directory.get(d).split("\\\\");
                    if(sFolders[i].equals(direccion[0])){
                    direccion = directory.get(d).split("\\\\");
                    
                        if(direccion.length>1){
                           
                            nFiles[f] = new DefaultMutableTreeNode(direccion[1]);
                            nFolders[i].add(nFiles[f]);
                            //System.out.println("d"+d+" ---   "+direccion[1]);
                            
                            
                        }
                                         
                        System.out.println("folder: "+i+" d= "+d+" nombre: "+direccion[0]+" arrayF "+ sFolders[i]+" archivo: "+direccion[1]);

                 d++;
                 f++;
                        
                    }
                 
                }
                
               }
               root.add(nFolders[i]);
               countFile++;
               System.out.println("---------"+direccion[0]); 
               dirAux=direccion[0];
                              
          
           //d++;
        }
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("finish");
        String currentPath = currentDirectory+"/src/images/icon.png";
        System.out.println("Ruta: "+currentPath);
        ImageIcon imageIcon = new ImageIcon(currentPath);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();       
        renderer.setLeafIcon(imageIcon);
        tree = new JTree(root);
        tree.addTreeSelectionListener(new TreeSelectionListener() {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();  
        System.out.println("nodo seleccionado: "+ selectedNode );
        Editor.setNombre_archivo(selectedNode.toString());
        Editor.abrirarchivo();
        }
        
});
       
        tree.setCellRenderer(renderer);
        JScrollPane jsp = new JScrollPane(tree);
        jsp.setSize(600,600);
        this.add(jsp);
        this.setVisible(true);
    }
    
}