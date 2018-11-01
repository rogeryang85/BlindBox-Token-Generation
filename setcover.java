/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github;

import java.util.*; 

/**
 *
 * @author roger
 */
public class setcover {
    
    static boolean universe []; 
    static int size; 
    static LinkedList<set> sets = new LinkedList<set>(); 
    static Scanner input = new Scanner(System.in); 

    public static void setCover(int thesize)
    {
        size = thesize; 
        universe = new boolean [size]; 
        for(int i=0; i<20; i++)
        {
            int temp [] = new int[4]; 
            for(int j=0; j<temp.length; j++)
                temp[i] = input.nextInt(); 
            System.out.println("enter cost"); 
            set s1 = new set(temp, input.nextInt());
            sets.add(s1); 
        }
        while(true)
        {
           
            double ratio = Integer.MAX_VALUE; 
            int currind=0; 
            int index =0; 
            ListIterator<set> iterate = sets.listIterator();
            while(iterate.hasNext())
            {
                 int unmatched= 0; 
                 for(int i=0; i<iterate.next().set.length; i++)
                 {
                    if(!universe[iterate.next().set[i]])
                    unmatched++; 
                 }
                if(iterate.next().cost/unmatched<ratio)
                {
                    ratio = iterate.next().cost/unmatched; 
                    index = currind;
                }
                currind++; 
            }
            set s1 = sets.get(index); 
            for(int i=0; i<s1.set.length; i++)
            {
                universe[s1.set[i]] =true; 
            }
            sets.remove(index); 
            
        }
        
            
        
        
        
    }
}
