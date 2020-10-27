package componentes;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oliveros
 */
public class pnlTree extends JPanel{
    
    private JTree tree;
    private String path;
    java.util.List<String> directory;
    private DefaultMutableTreeNode[] nFiles;
    private DefaultMutableTreeNode[] nFolders;
    private String[] sFiles;
    private String[] sFolders;
    public pnlTree(int i){}
    public pnlTree(){
        
        getDataTree();
        configArrayNodes();
        configArrayStrings();
        crearNodos();
        
        /*for(int i=0;i<=directory.size()-1;i++){
            nodes[i] = new DefaultMutableTreeNode(arrayDir[i]);
        }
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
         
        //create the tree by passing in the root node
        tree = new JTree(root);
        add(tree);
         
        this.setVisible(true);*/
    }
    
    public void getDataTree(){
    String currentDirectory = System.getProperty("user.dir");
    System.out.println("The current working directory is " + currentDirectory);
    path = currentDirectory+"/src/project/";
    
    try (Stream<Path> walk = Files.walk(Paths.get(currentDirectory+"/src/project/"))) {
   
		
        directory = walk.filter(Files::isRegularFile)
                .map(x -> x.toString().replace(path,"")).collect(Collectors.toList());

		directory.forEach(System.out::println);
               System.out.println(directory.size());
        
                
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
    
    public void configArrayNodes(){
        String dirAux = "";
        String[] direccion;
        int countFolder = 0;
        int countFile = 0;
        
        //tamaño de arreglo para numero de nodos
        
        for(int i = 0; i<=directory.size()-1;i++){
            
            
           direccion = directory.get(i).split("/");
           if(direccion.length>1){
               
               
               if(!direccion[0].equals(dirAux)){
                 countFolder++;
                 System.out.println(direccion[0]);
                 
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
        sFiles = new String[countFile];
    }
    
    public void configArrayStrings(){
        String dirAux = "";
        String[] direccion;
        int countFolder = 0;
        int countFile = 0;
        
        //tamaño de arreglo para numero de nodos
        
        for(int i = 0; i<=directory.size()-1;i++){
            
            
           direccion = directory.get(i).split("/");
           if(direccion.length>1){
               
               
               if(!direccion[0].equals(dirAux)){
                 sFolders[countFolder] = direccion[0];
                 countFolder++;
                 
                 //System.out.println(direccion[0]);
                 
               }                    
                   countFile++;
                   //System.out.println("---------"+direccion[1]); 
                  dirAux=direccion[0];
                              
           }
        }
    }

    /**
     *
     */
    public void crearNodos(){
        String dirAux = "";
        String[] direccion;
        int countFolder = 0;
        int countFile = 0;
        int d = 0;
        
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        for(int i = 0; i<=nFolders.length-1;i++){
           direccion = directory.get(d).split("/");
           
           if(direccion.length>1){
                                
                nFolders[i] = new DefaultMutableTreeNode(direccion[0]); //nodo folder
                System.out.println("folder: "+i+" nombre: "+direccion[0]);
                int f = 0;
                while(sFolders[i].equals(direccion[0])&&d<directory.size()){
                    direccion = directory.get(d).split("/");
                    if(sFolders[i].equals(direccion[0])){
                    direccion = directory.get(d).split("/");
                    
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
               //System.out.println("---------"+direccion[1]); 
               dirAux=direccion[0];
                              
          
           //d++;
        }
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);

        String currentPath = currentDirectory+File.separator+"src"+File.separator+"images"+File.separator+"slides2.png";
        
        ImageIcon imageIcon = new ImageIcon(currentPath);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();       
        renderer.setLeafIcon(imageIcon);
        tree = new JTree(root);
        tree.setCellRenderer(renderer);
        add(new JScrollPane(tree));
    }
    
}
