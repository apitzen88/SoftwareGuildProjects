/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.summativesums;

import static java.lang.Integer.sum;

/**
 *
 * @author apitz_000
 */
public class SummativeSums {
    public static void main(String[] args) {
        
        int[] array1={ 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] array2={ 999, -60, -77, 14, 160, 301 };
        int[] array3= { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
        140, 150, 160, 170, 180, 190, 200, -99 };
        
        System.out.print("Array #1 sum: ");sumArrayValues(array1);
        System.out.print("Array #2 sum: ");sumArrayValues(array2);
        System.out.print("Array #3 sum: ");sumArrayValues(array3);
        
    }
    
    public static void sumArrayValues(int[] array) {
        int sum=0;
        for(int i=0;i<array.length;i++){
        sum=sum+array[i];
        }
        System.out.println(sum);    
    } 
}
