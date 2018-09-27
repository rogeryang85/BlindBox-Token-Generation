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
import java.io.*; 
public class ContentFinder {
    ArrayList<Integer> indexes = new ArrayList<Integer>();  //the arraylist that will store all the indexes 
    
    //accepts a valid string as text, it finds the content keyword that start the rule sets and skips over everything in it until it approaches a quotation mark without backslashes 
    //this allows the work content that is a rule to be accepted, constant time since the pattern is constant 
       
           
    
    public  ArrayList<Integer> find(String txt)  
    {
        String pat = "content"; //the pattern 
        for(int i=0; i<=txt.length()-7; i++)  //goes until the end index 
        {
            int temp = i;
            for(int j=0; j<pat.length(); j++) //checks for pattern match at every index 
            {
                 
                if(txt.charAt(temp)==pat.charAt(j)) //match 
                {
                    if(j==pat.length()-1) //complete match, then it will start to ignore everything in this and go on 
                    {
                        indexes.add(i); 
                        boolean backslash = false; //if a backslash was present 
                        int current = temp+3; 
                        while(true)
                        {
                            if(txt.charAt(current)=='\\')
                            {
                                if(backslash)           //the backslash is to be included as a rule 
                                    backslash=false;
                                else    //the backslash signals a special character is coming 
                                    backslash=true; 
                            }
                            if(txt.charAt(current)=='\"'&&!backslash) //if it approaches the quotation mark with on backslash, it will mark the end of the rule set 
                            {
                                i = current; //sets the index to to this and continues on 
                                break; 
                            }
                            current ++; //increase pointer 
                        } 
                    }
                    temp++; 
                }
                
                else
                    break; 
                
            }
        }
        return  indexes; //returns the indexes
    }
}
    


