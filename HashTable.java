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
public class HashTable {
    
    String [] table = new String [1000]; 
    public void insert(String key)
    {
        table[hashFunction(key)]=key; 
    }
    public boolean lookUp(String key)
    {
        if(table[hashFunction(key)]!=null)
            return true; 
        else return false; 
    }
    private int hashFunction(String key)
    {
        int hash = 7;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash*31 + key.charAt(i))%100000;
        }
        return hash;
    }
    
}
