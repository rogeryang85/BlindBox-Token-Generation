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
public class Trie {
    
    private final static int R = 256; 
    private Node root = new Node(); 
    private static class Node 
    {
        private int val; 
        private boolean visited=false; 
        private Node[] next = new Node [R]; 
    }
    
    public boolean get(String key)
    {
        Node x = get(root, key, 0); 
        if(x==null)
            return false; 
        else 
            return true; 
        
    }
    private Node get(Node x, String key , int length)
    {
        if(x==null) 
            return null; 
        if(length==key.length())
        {
            if(!x.visited)
            {
                x.visited=true;
                return x; 
            }
            else 
                return null; 
                
        }
        char c = key.charAt(length); 
        return get(x.next[c], key, length+1); 
    }    
    
    public void put(String key, int value)
    {
        root = put(root, key, value, 0); 
    }
    private Node put(Node x, String key, int value, int length)
    {
        if(x==null)
            x=new Node();
        if(length==key.length())
        {
            x.val=value; 
            return x; 
        }
        char c = key.charAt(length); 
        x.next[c]= put(x.next[c], key, value, length+1); 
        return x; 
        
        
    }
    
}
