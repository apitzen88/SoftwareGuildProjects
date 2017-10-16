/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        String input = sc.nextLine();
        return Double.parseDouble(input);

    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            double d = Double.parseDouble(input);
            if (d >= min && d <= max) {
                return d;
            } else {
                print("Invalid Entry, enter a double between " + min + " and " + max);
            }
        } while (true);
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        String input = sc.nextLine();
        return Float.parseFloat(input);
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            float f = Float.parseFloat(input);
            if (f >= min && f <= max) {
                return f;
            } else {
                print("Invalid entry, enter a float between " + min + " and " + max);
            }
        } while (true);
    }

    @Override
    public int readInt(String prompt) {
        int x = 1;
        int valid = 0;
        do {
            try {
                print(prompt);
                String input = sc.nextLine();
                valid = Integer.parseInt(input);
                x = 2;
            } catch (InputMismatchException | NumberFormatException e) {
                print("Enter an integer number");
            }
        } while (x == 1);
        return valid;

    }

    @Override
    public int readInt(String prompt, int min, int max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            int i = Integer.parseInt(input);
            if (i >= min && i <= max) {
                return i;
            } else {
                print("Invalid entry, enter an integer between " + min + " and " + max);
            }
        } while (true);
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        String input = sc.nextLine();
        return Long.parseLong(input);
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        do {
            print(prompt);
            String input = sc.nextLine();
            Long l = Long.parseLong(input);
            if (l >= min && l <= max) {
                return l;
            } else {
                print("Invalid entry, enter a long between " + min + " and " + max);
            }

        } while (true);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String input = sc.nextLine();
        return input;
    }

}
