/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github;

/**
 *
 * @author roger
 */
public class RBT {
    private class Node{
        int value; 
        Node left; 
        Node right;
        boolean color; //red= true black = false 
        boolean visited=false; 
        
        Node (int value, boolean color)
        {
            this.value = value; 
            this.color=color; 
        }
    }
    private static final boolean RED= true; 
    private static final boolean BLACK = false; 
    private Node root; 
    private boolean isRed(Node x)
    {
        return x.color;
    }
    
    private Node rotateLeft(Node h)
    {
      Node x= h.right; 
      h.right= x.left;
      x.left = h; 
      x.color = h.color; 
      h.color = RED; 
      return x; 
    }
    private Node rotateRight(Node h)
    {
        Node x = h.left; 
        h.left=x.right; 
        x.right=h; 
        x.color=h.color;
        h.color = RED; 
        return x; 
    }
    private void flipColor(Node h)
    {
        h.color=RED; 
        h.left.color = BLACK; 
        h.right.color = BLACK; 
    }
    
    private Node put(Node x, int value)
    {
        if(x==null)
            return new Node(value, RED); 
        
        if(value>x.value)
            x.right= put(x.right, value); 
        else if(value<x.value)
            x.left= put(x.left, value); 
        else x.value= value; 
        
        if(isRed(x.right)&&!isRed(x.left))
            x=rotateLeft(x); 
        if(isRed(x.left)&&isRed(x.left.left))
            x=rotateRight(x); 
        if(isRed(x.left)&&isRed(x.right))
            flipColor(x); 
        return x; 
     }
    public void put(int value)
    {
        root = put(root, value); 
    }
    private boolean visit(Node current, int value)
    {
        if(current==null)
            return false; 
        if(value==current.value)
        {
            if(!current.visited) 
            {
                current.visited= true; 
                return true; 
            }
            else 
                return false; 
        }
        else if(value>current.value)
            return visit(current.right, value); 
        else 
            return visit(current.left, value); 
    }
    public boolean isThere(int value)
    {
        return visit(root , value); 
    }
    
    
 
    
    
}
