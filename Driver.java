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
import java.io.*;
import java.util.*; 
public class Driver {
    
    static private ArrayList<String> tokens;
    static private ArrayList<String> rules;

    static private HashMap<String,Integer> map = new HashMap<String, Integer>(); 
    static int leaked =0; 
    static int tokensmatch=0;
    //Precondition: valid text and size 
    //postcondition: finds the number of leaks and number of tokens matched 
    public static int doDetection(String text, int size)
    {
        int last=0; //index of last matched token
        
        for(int i=0; i<tokens.size(); i++)
        {
            map.put(tokens.get(i), 0); //puts all tokens into hashtable, 1 means encountered, 0 otherwise 
        }
        
        for(int i=0; i<=text.length()-size; i++)
        {
            if(map.containsKey(text.substring(i, i+size))&&map.get(text.substring(i, i+size))==0) //not encountered 
            {
                if(last>=i) //checks for overlap with previous matched ones to prevent overccount 
                {
                    leaked = leaked + size-(last-i+1);  
                    last = i+size-1; //sets the new pointer to prevent overcount 
                }
                else    
                {
                    leaked = leaked + size; //no overlap 
                    last = i+size-1; 
                }
                map.replace(text.substring(i, i+size), 1); //puts it into the map as seen to prevent overcount  
                tokensmatch++;  // the number matched increases 
             }
            else if(map.containsKey(text.substring(i, i+size))&&map.get(text.substring(i, i+size))==1) //encountered 
            {
                 if(last>=i) //checks for overlap with previous matched ones but does not increase unique token matched count 
                {
                    leaked = leaked + size-(last-i+1); //still leaked, but token has already been encountered 
                    last = i+size-1; 
                }
                else    
                {
                    leaked = leaked + size; //no overlap 
                    last = i+size-1; 
                }
            }
            else
            {
                
            }
               
        }
        return leaked;             
    }
    public static void main(String [] args) throws IOException
    {
        
       File file = new File("snortrules.txt"); 
       Scanner read = new Scanner(file); 
       StringBuilder s = new StringBuilder(); 
       while(read.hasNextLine())
           s.append(read.nextLine()); 
       
       File file2 = new File("wsj.txt"); 
       Scanner read2 = new Scanner(file2); 
       StringBuilder ss = new StringBuilder(); 
       while(read2.hasNextLine())
           ss.append(read2.nextLine()); 
       
       Scanner input = new Scanner(System.in);  //standard IO 
       RuleGenerator generateR = new RuleGenerator(s.toString()); 
       rules = generateR.getRules(); 
       System.out.println("input token size"); 
       int size= Integer.parseInt(input.nextLine()); 
       TokenGenerator generateT = new TokenGenerator(size, generateR.getRules()); 
       tokens = generateT.getTokens();
       doDetection(ss.toString(), size);
       System.out.println("leaked: " + leaked + " tokensmatch: "+ tokensmatch); 
     
        
             
    }
    
}   
