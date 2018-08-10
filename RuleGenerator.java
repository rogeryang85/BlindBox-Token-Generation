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
    
   static private ArrayList<String> rules = new ArrayList<String>(); 
    
    
    
    public RuleGenerator(String snortrules) 
    {
        ArrayList<Integer> indexes;
        kmp find = new kmp(); 
        indexes = find.KMPSearch("content", snortrules); //searches for keyword content and saves their indexes
        
        for(int i=0; i<indexes.size(); i++)
        {
            StringBuilder str = new StringBuilder(); //current rule string 
            
            int current = indexes.get(i)+9; //start 
            
            boolean backslash = false;  //backslash 
            while(current<snortrules.length())
            {
                if(snortrules.charAt(current)=='\\') 
                {
                    if(backslash)
                    {
                        str.append('\\'); //appends it 
                        backslash= false; 
                    }
                    else
                    {
                        backslash = true; //signal for special character 
                        current++; 
                        continue;
                    } 
                }
                else if(snortrules.charAt(current)=='\"')
                {
                    if(!backslash) //end of rule
                        break; 
                    else 
                    {
                        str.append(snortrules.charAt(current)); //appends as special character
                        backslash=false; 
                    }
                }
                else
                {
                    if(snortrules.charAt(current)=='|') //signal for hexadecimal number 
                    {
                        if(backslash) //special character 
                        {
                            str.append(snortrules.charAt(current));
                             backslash=false; 
                        }
                        else
                        {
                            StringBuilder tempcurrent = new StringBuilder();  //string to store hex
                            int temp = current+1;  //hex index 
                            while(true) //while not the end 
                            {
                                 
                                if(temp==' ') //another hex 
                                {
                                    String hex = Integer.toString(Integer.parseInt(tempcurrent.toString(),16)); //converts to base 10 and then to string 
                                    str.append(hex);  
                                    tempcurrent = new StringBuilder(); //refreshes the stringbuilder  
                                }
                                else if(temp=='|') //end 
                                {
                                    str.append(Integer.toString(Integer.parseInt(tempcurrent.toString(),16))); 
                                    break; 
                                }
                                else 
                                    tempcurrent.append(temp); 
                                temp++; 
                            }
                            
                            
                        }
                    }
                    else
                    str.append(snortrules.charAt(current));
                }
                current++; 
                
            }
            rules.add(str.toString()); //adds current string to list 
        }
        
        
    }
    public ArrayList<String> getRules()
    {
        for(String x : rules)
            System.out.print(x+ " ");
       return rules;
    }
    

}
