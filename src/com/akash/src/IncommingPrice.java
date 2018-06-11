/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.src;

import java.util.HashMap;

/**
 *
 * @author Pratidhi Chowdhury
 */
public class IncommingPrice {
    HashMap<Character, Character> map = new HashMap<>();

    public IncommingPrice() {
        map.put('S', '1');
        map.put('F', '2');
        map.put('W', '3');
        map.put('E', '4');
        map.put('L', '5');
        map.put('O', '6');
        map.put('M', '7');
        map.put('G', '8');
        map.put('A', '9');
        map.put('N', '0');
    }
    
    public float decodePrice(String p)
    {
        String res = "";
        p = p.toUpperCase();
        char parr[] = p.toCharArray();
        for(char c : parr)
        {
            if(map.containsKey(c))
                res += map.get(c);
            else
                res += c;
        }
        System.err.println("----------------- "+res);
        return Float.parseFloat(res);
    }
    
}
