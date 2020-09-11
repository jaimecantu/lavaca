/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejtree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author TecMilenio
 */
public class Tree {
    
    private TreeNode root; //Nodo raíz
    private int index = 0; //Se utiliza para imprimir el árbol completo en preorden
    private int level = 0; //Se utiliza para imprimir 3 niveles del árbol

    private String content; //Se utiliza para guardar el árbol en memoria

    public Tree() { //Constructor vacío
    }

    public Tree(TreeNode root) { //Constructor con nodo raíz
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public void preorden(TreeNode currentNode){ //Imprime el arbol en preorden
        if (currentNode!=null){ //Caso recursivo. Se repite hasta que el nodo actual sea nulo
            System.out.println(index+currentNode.getData()+"\n"+currentNode.getAnsYes()+"\n"+currentNode.getAnsNo());
            
            index++;
            preorden(currentNode.getYes());
            preorden(currentNode.getNo());
        }
    }
    
    public void findNode (TreeNode currentNode, String data){ //Busca un nodo basado en su data
        if (currentNode!=null){ //Caso recursivo. Se repite hasta que el nodo actual sea nulo
            if (currentNode.getData().equals(data)){ //Caso de salida
                printNodes(currentNode); //Imprimir 3 niveles del árbol utilizando el nodo encontrado como raíz
            }
            else{ //Caso recursivo cuando el nodo no es nulo
                findNode(currentNode.getYes(), data);
                findNode(currentNode.getNo(), data);
            }
        } 
    }
    
    public void printNodes (TreeNode currentNode){ //Imprime 3 niveles de un árbol
        if (currentNode!=null&&level<=3){ //Caso recursivo. Se repite hasta que el nivel sea mayor a 3
            System.out.println(currentNode.getData()+"\n"+currentNode.getAnsYes()+"\n"+currentNode.getAnsNo()+"\n");
            
            printNodes(currentNode.getYes());
            if(currentNode.getYes()!=null||currentNode.getNo()!=null){
                level++; //Si el nodo tiene descendencia, se suma 1 al nivel
            }
            printNodes(currentNode.getNo());
           
        }
    }    

    public void load() throws FileNotFoundException{ //Carga el archivo de texto de memoria
        
        File file = new File("SaveData.txt");
        Scanner scanner = new Scanner(file);
        
        build(this.root,scanner); //Si se encontró, entonces arma el árbol
        
    }
    
    //Método para armar un árbol a partir de un archivo de texto en preorden
    public void build(TreeNode currentNode, Scanner scanner) throws FileNotFoundException{

        if(scanner.hasNextLine()){ //Caso recursivo. Continúa hasta que no haya una línea siguiente en el archivo
            String question = scanner.nextLine(); //Línea 1: data
            String ansYes = scanner.nextLine(); //Línea 2: ansYes
            String ansNo = scanner.nextLine(); //Línea 3: ansNo
        
            currentNode.setData(question);
            currentNode.setAnsYes(ansYes);
            currentNode.setAnsNo(ansNo);
            
            if (!ansYes.equals("null") && ansNo.equals("null")){ //1 Hijo derecho
                currentNode.setAnsNo(null); //Porque null != "null"
                currentNode.setYes(null);
                currentNode.setNo(new TreeNode());
                build(currentNode.getNo(), scanner);
            }
            
            else if (ansYes.equals("null") && !ansNo.equals("null")){ //1 Hijo Izq
                currentNode.setAnsYes(null); //Porque null != "null"
                currentNode.setNo(null);
                currentNode.setYes(new TreeNode());
                build(currentNode.getYes(),scanner);
            }
            
            else if (ansYes.equals("null") && ansNo.equals("null")){ //2 hijos
                currentNode.setYes(new TreeNode());
                currentNode.setNo(new TreeNode());
                currentNode.setAnsYes(null); //Porque null != "null"
                currentNode.setAnsNo(null);
                build(currentNode.getYes(),scanner);
                build(currentNode.getNo(), scanner);
            }
        }
        
    }
    
    //Verifica que exista el archivo de salida
    public void save(TreeNode currentNode) throws IOException{
        FileWriter fileWriter = new FileWriter("SaveData.txt");
        saveBuild(currentNode,fileWriter); //En caso de que sí, guarda el árbol
        fileWriter.close();
    }
    
    //Método para guardar el árbol en un archivo de texto en preorden
    public void saveBuild(TreeNode currentNode, FileWriter fileWriter) throws IOException{
        if (currentNode!=null){ //Caso recursivo. Continúa mientras el nodo no sea nulo
            content = currentNode.getData()+"\n"+currentNode.getAnsYes()+"\n"+currentNode.getAnsNo()+"\n";
            fileWriter.write(content);
            saveBuild(currentNode.getYes(),fileWriter);
            saveBuild(currentNode.getNo(),fileWriter);
        }

    }

}

    
    

