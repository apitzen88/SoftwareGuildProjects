/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class HealthyHearts {
    public static void main(String[] args) {
        
        Scanner input=new Scanner(System.in);
        
        int age;
        int maxHeartRate;
        int targetMin;
        int targetMax;
        
        System.out.println("TARGET HEART RATE CALCULATOR");
        System.out.println("What is your Age? ");
        age=input.nextInt();
        
        maxHeartRate=220-age;
        targetMin=maxHeartRate/2;
        targetMax=(int) (maxHeartRate*0.85);
        
        System.out.println("Your maximum heart rate should be "+maxHeartRate+" beats per minute");
        System.out.println("Your Target HR zone is between "+targetMin+" and "+targetMax+" beats per minute");
        
    }
}
