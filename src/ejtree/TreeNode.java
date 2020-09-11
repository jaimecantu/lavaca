/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejtree;

/**
 *
 * @author TecMilenio
 */
public class TreeNode{
    
    private String data; //Pregunta
    private String ansYes; //Respuesta si sí
    private String ansNo; //Respuesta si no
    
    private TreeNode yes=null;
    private TreeNode no=null;

    public TreeNode() {
    }

    public TreeNode(String data) {
        this.data = data;
    }

    public TreeNode(String data, String ansYes, String ansNo, TreeNode yes, TreeNode no) {
        this.data = data;
        this.ansYes = ansYes;
        this.ansNo = ansNo;
        this.yes = yes;
        this.no = no;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean IsLast() {
        return (this.ansYes!=null&&this.ansNo!=null);
    }


    public TreeNode getYes() {
        return yes;
    }

    public void setYes(TreeNode yes) {
        this.yes = yes;
    }

    public TreeNode getNo() {
        return no;
    }

    public void setNo(TreeNode no) {
        this.no = no;
    }

    public String getAnsYes() {
        return ansYes;
    }

    public void setAnsYes(String ansYes) {
        this.ansYes = ansYes;
    }

    public String getAnsNo() {
        return ansNo;
    }

    public void setAnsNo(String ansNo) {
        this.ansNo = ansNo;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "data=" + data + ", ansYes=" + ansYes + ", ansNo=" + ansNo + ", yes=" + yes + ", no=" + no + '}';
    }
    
    
}
