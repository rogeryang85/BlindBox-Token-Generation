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
public class Driver {
    
    private ArrayList<String> rules = new ArrayList<String>(); 
    private ArrayList<String> tokens = new ArrayList<String>(); 
    private HashMap<String,Integer> map = new HashMap<String, Integer>(); 
    int leaked =0; 
    
    public void doDetection(String text, int size, String rules)
    {
        TokenGenerator generateT = new TokenGenerator(size, text);
        RuleGenerator generateR = new RuleGenerator(rules); 
        tokens = generateT.getTokens(); 
        this.rules = generateR.getRules(); 
        for(int i=0; i<tokens.size(); i++)
        {
            ArrayList<Integer> temp; 
            kmp dokmp=new kmp(); 
            if(!map.containsKey(tokens.get(i)))
            {
                 for(int j=0; j<this.rules.size(); j++)
                 {
                
                     temp = dokmp.KMPSearch(tokens.get(i), this.rules.get(j));
                     if(!temp.isEmpty())
                     {
                       leaked += tokens.get(i).length(); 
                       map.put(tokens.get(i), 1); 
                     }
                }
            }
        }
        
    }
    
}
