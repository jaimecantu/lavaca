/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejtree;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author TecMilenio
 */
public class EjTree {
    
    

    /**
     * @param args the command line arguments
     */
    
    static Scanner scanner = new Scanner(System.in);
    
    //Cuando el jugador gana, se pregunta en qué animal pensó y se agrega al árbol
    static void ask(TreeNode currentNode, boolean type) throws IOException{ //true = yes, false = no
        String animal;
        String question;
        System.out.println("¿En qué animal pensaste?");
        scanner.nextLine();
        animal = scanner.nextLine();
        System.out.println("¿Qué lo hace diferente?");
        question = "¿"+scanner.nextLine()+"?";
        if(type){ //YES
            currentNode.setYes(new TreeNode(question,animal,currentNode.getAnsYes(),null,null));
            currentNode.setAnsYes(null);
            game.save(game.getRoot());
        }
        else{ //NO
            currentNode.setNo(new TreeNode(question,animal,currentNode.getAnsNo(),null,null));
            currentNode.setAnsNo(null);
            game.save(game.getRoot());
        }
        
    }
    
    //Método para el juego
    static void play(TreeNode currentNode) throws IOException{
        if(currentNode.IsLast()){ //Caso de salida. El nodo no tiene descendencia
            System.out.println(currentNode.getData());
            System.out.print("Y/N: ");
            ans = scanner.next();
            if(ans.startsWith("Y")){ //En caso que el jugador responda que sí
                System.out.println("Es "+currentNode.getAnsYes()+"? (Y/N)");
                ans = scanner.next();
                if(ans.startsWith("Y")){ //Sí
                    System.out.println("Fin del juego.\n");
                    game.save(game.getRoot());
                }
                else{ //No
                    ask(currentNode,true);
                }
            }
            else{ //En caso que el jugador responda que no
                System.out.println("Es "+currentNode.getAnsNo()+"? (Y/N)");
                ans = scanner.next();
                if(ans.startsWith("Y")){//sí
                    System.out.println("Fin del juego.\n");
                    game.save(game.getRoot());
                }
                else{//no
                    ask(currentNode,false);
                }
            }
        }
        else{ //Caso recursivo. Continúa jugando con los nodos siguientes
            System.out.println(currentNode.getData());
            System.out.print("Y/N: ");
            ans = scanner.next();
            if(ans.charAt(0)=='Y'){ //Sí
                if(currentNode.getAnsYes()==null){
                    play(currentNode.getYes());
                }
                else{
                    System.out.println("Es "+currentNode.getAnsYes()+"? (Y/N)");
                    ans = scanner.next();
                    if(ans.startsWith("Y")){
                        System.out.println("Fin del juego.\n");
                        game.save(game.getRoot());
                    }
                    else{
                        ask(currentNode,true);
                    }
                }
            }
            else{ //No
                if(currentNode.getAnsNo()==null){
                    play(currentNode.getNo());
                }
                else{
                    System.out.println("Es "+currentNode.getAnsNo()+"? (Y/N)");
                    ans = scanner.next();
                    if(ans.startsWith("Y")){
                        System.out.println("Fin del juego.\n");
                        game.save(game.getRoot());
                    }
                    else{
                        ask(currentNode,false);
                    }
                }
            }
        }
    }
    
    static void menu(){
        System.out.println("           __n__n__\n" +
"    .------`-\\00/-'\n" +
"   /  ##  ## (oo)\n" +
"  / \\## __   ./\n" +
"     |//YY \\|/\n" +
"     |||   |||");
        System.out.println("Selecciona una opción:\n");
        System.out.println("1. Jugar");
        System.out.println("2. Imprimir árbol (3 niveles)");
        System.out.println("3. Salir\n");
        System.out.print("Selección: ");
    }
    
    static Tree game = new Tree(new TreeNode()); //Árbol que contiene el juego
    static String ans = null; //Guarda la respuesta del usuario (Y/N)
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        game.load(); //Cargar el árbol desde un archivo de texto en memoria
        byte select; //Contiene la opción seleccionada por el usuario
        do{
            menu();
            select = scanner.nextByte();
 
            switch(select){
                case 1: //Jugar
                    play(game.getRoot());
                    break;
                
                case 2: //Imprimir árbol
                    String nodeQ;
                    System.out.println("¿A partir de qué nodo?");
                    System.out.print("Ingresa la pregunta: ");
                    scanner.nextLine();
                    nodeQ = scanner.nextLine();
                    System.out.println("---------------------------------");
                    game.findNode(game.getRoot(), nodeQ);
                    System.out.println("---------------------------------\n");
                    game.setLevel(0);
                    break;
                    
                case 3: //Salir
                    System.out.println("moo!");
                    break;
                    
                default: //Cualquier otro caso
                    System.out.println("Opción inválida. Intenta otra vez.");
                    break;
            }
        }
        while(select!=3); //El ciclo continúa hasta que el jugador elija salir
        
    }
    
}
