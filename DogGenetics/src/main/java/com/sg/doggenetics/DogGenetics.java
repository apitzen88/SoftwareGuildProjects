/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class DogGenetics {
    public static void main(String[] args) {
        
        Scanner input=new Scanner(System.in);
        
        Random random=new Random();
        
        String dogName;

        
        int randomPercent1=random.nextInt(100);
        int randomPercent2=random.nextInt(100-randomPercent1);
        int randomPercent3=random.nextInt(100-randomPercent1-randomPercent2);
        int randomPercent4=random.nextInt(100-randomPercent1-randomPercent2-randomPercent3);
        int randomPercent5=100-randomPercent1-randomPercent2-randomPercent3-randomPercent4;
                
        System.out.println("What is your dog's name? ");
        dogName=input.nextLine();
        
        System.out.println("\n"+dogName+" is: \n");
        System.out.println("Puli: "+randomPercent1+"%");
        System.out.println("Xoloitzcuintli: "+randomPercent2+"%");
        System.out.println("Chinese Crested: "+randomPercent3+"%");
        System.out.println("Norwegian Lundehund: "+randomPercent4+"%");
        System.out.println("Carolina Dog: "+randomPercent5+"%");
        
        System.out.println("\nWOW! "+dogName+" is quite an interesting dog!");
        }
    }
