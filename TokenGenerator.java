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
import java.util.*;
public class TokenGenerator {
    
    private ArrayList<String> tokens = new ArrayList<String>(); 
    
    public TokenGenerator(int size, String text)
    {
        for(int i=0; i+size<=text.length(); i++)
            tokens.add(text.substring(i, i+size-1)); 
    }
    
    public ArrayList<String> getTokens()
    {
        return tokens; 
    }
}
