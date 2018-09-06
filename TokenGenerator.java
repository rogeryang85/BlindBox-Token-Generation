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
    
    public TokenGenerator(int size, ArrayList<String> rules)
    {
        for(int i=0; i<rules.size(); i++) 
        {
            for(int j=0; j<=rules.get(i).length()-size; j++) //sliding window, moves by 1 each time  
            {
                tokens.add(rules.get(i).substring(j, j+size)); //size 
            }
        }
    }
    
    public ArrayList<String> getTokens()
    {
        for(String x : tokens)
            System.out.println(x);
        return tokens; 
    }
}
