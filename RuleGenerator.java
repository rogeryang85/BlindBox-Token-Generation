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
    
    /*The rule generator takes in a valid snortrule string, then it will be able to filter out all special characters, hexadecimals, etc, then it will put all of these rules
   into an arraylist for the token generator*/
   /*This rule generator goes through each index for the keyword content and not the rule content, then goes through each rule to filter out and add the rule to the arraylist */
    

    public RuleGenerator(String snortrules) 
    {
        ArrayList<Integer> indexes;
        ContentFinder f = new ContentFinder(); 
        indexes = f.find(snortrules);  //stores all indexes of keyword content but does not count it when content is a rule 
        
        for(int i=0; i<indexes.size(); i++) //for all the content keywords, which implies a signal 
        {
            StringBuilder str = new StringBuilder(); //current rule string  
            
            int current = indexes.get(i)+9; //start 
            
            boolean backslash = false;  //checks whether or not a backslash was just encountered, and signals for a special character 
            while(current<snortrules.length())
            {
                if(snortrules.charAt(current)=='\\') 
                {
                    if(backslash)//if already encoutnered, then it means the backslash should be included 
                    {
                        str.append('\\'); //appends it 
                        backslash= false; //no more specail characters 
                        current ++; 
                    }
                    else
                    {
                        backslash = true; //signal for special character 
                        current++; 
                        
                    } 
                }
                else if(snortrules.charAt(current)=='\"') //this handles for when we encounter a quotation mark 
                {
                    if(!backslash) //if not a special character, this signals the end of rule 
                        break; 
                    else 
                    {
                        str.append(snortrules.charAt(current)); //appends as special character
                        backslash=false; //backslash used 
                    }
                }
                else
                {
                    if(snortrules.charAt(current)=='|') //signal for hexadecimal number 
                    {
                        if(backslash) //if it is a special character, then append it 
                        {
                            str.append(snortrules.charAt(current));
                             backslash=false; 
                        }
                        else //not a back slash, signals for a hexadecimal 
                        {
                            StringBuilder tempcurrent = new StringBuilder();  //string that stores the hexadecimal 
                            int temp = current+1;  //current index within the hexadecimal 
                            while(true) //while not the end 
                            {
                               if(snortrules.charAt(temp)=='|') //signals the end 
                               {
                                   current = temp; //resets the pointer 
                                   str.append((char)Integer.parseInt(tempcurrent.toString(),16)); //converts the hexadecimal to a decimal, then casts that decimal to a char based on ASCII to have same binary representation 
                                   break; //finished 
                               }
                               else if(snortrules.charAt(temp)==' ') //new hexadecimal 
                               {
                                   str.append((char)Integer.parseInt(tempcurrent.toString(),16)); //converts the hexadecimal to a decimal, then casts that decimal to a char based on ASCII to have same binary representation 
                                   tempcurrent = new StringBuilder(); //clears it up for the next hexadecimal 
                                  
                               }
                               else 
                                   tempcurrent.append(snortrules.charAt(temp));  //appends the character for parsing 
                               temp++; 
                              
                            }
                            
                            
                        }
                    }
                    else
                    str.append(snortrules.charAt(current)); //if nothing special, then append it 
                }
                current++; //increases pointer 
                
            }
            rules.add(str.toString()); //adds current string to list 
        }
        
        
    }
    
    public ArrayList<String> getRules()
    {
       for(String s : rules)
           System.out.println(s);
       return rules;
    }
    

}
