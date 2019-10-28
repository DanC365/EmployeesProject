package com.sparta.djc.controller;

import java.util.Collection;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class Starter
{
    public static void main( String[] args )
    {
        int h;
        String key = "John";
        System.out.println((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));
        System.out.println(key.hashCode()>>>16);


        System.out.println(77&59);
        HashMap<String, Integer> testMap = new HashMap<>();
        testMap.put("John",145);
        System.out.println("John".hashCode());
        testMap.put("Alistair",12);
        testMap.put("Ian",35);
        testMap.put("Andrew",89);
        testMap.put("Zac",78);
        testMap.put("Emily",15);
        testMap.put("Lou",96);
        testMap.put("Brian",125);
        testMap.put("Ally",132);
        testMap.put("Brennan",56);
        testMap.put("Sophia",101);
        testMap.put("Misty",99);
        testMap.put("Rowan",65);
        testMap.put("Pete",47);
        testMap.put("Ricky",4);



        Collection<Integer> valueList = testMap.values();



        System.out.println( "Hello World!" );
    }
}
