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
public class RuleGenerator {
    
    private ArrayList<String> rules = new ArrayList<String>(); 
    
    
    
    public RuleGenerator(String snortrules) //will later modify into accepting a textfile 
    {
        ArrayList<Integer> indexes; 
        kmp find = new kmp(); 
        indexes = find.KMPSearch("CONTENT", snortrules); 
        
        for(int i=0; i<indexes.size(); i++)
        {
            StringBuilder str = new StringBuilder(); 
            int current= indexes.get(i); 
   
            while(snortrules.charAt(i+9)!= '"')
            {
                str.append(snortrules.charAt(i+9));
                i++; 
            }
        }
        
        
    }
    public ArrayList<String> getRules()
    {
        return rules; 
    }
    

}
