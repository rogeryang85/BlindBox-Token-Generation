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
public class kmp {
      public ArrayList<Integer> KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
        ArrayList<Integer> indexarr = new ArrayList<Integer>(); 
 
       
        int lps[] = new int[M];
        int j = 0; // index for pat[]
 
       
        computeLPSArray(pat,M,lps);
 
        int i = 0; // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                System.out.println("Found pattern " + "at index "  + (i-j));
                indexarr.add(i-j); 
                j = lps[j-1];
            }
 
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        return indexarr; 
    }
 
    void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0
 
        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
            
                if (len != 0)
                {
                    len = lps[len-1];
 
                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
 
}
