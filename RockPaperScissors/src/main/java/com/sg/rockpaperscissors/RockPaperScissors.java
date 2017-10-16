/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apitz_000
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int rounds;
        int turn;

        int rock = 1;
        int paper = 2;
        int scissors = 3;

        int player;
        int computer;

        int wins = 0;
        int losses = 0;
        int ties = 0;

        int playAgain = 1;

        System.out.println("Let's play ROCK PAPER SCISSORS!");

        while (playAgain == 1) {
            System.out.println("How many rounds? ");
            rounds = input.nextInt();

            if (rounds > 10 || rounds < 1) {
                System.out.println("ERROR: Number of rounds must be between 1 and 10");
            } else {
                for (turn = 1; turn <= rounds; turn++) {
                    System.out.println("Rock(1), Paper(2), or Scissors(3)? ");
                    player = input.nextInt();
                    computer = random.nextInt(3) + 1;

                    if (computer == rock) {
                        if (player == rock) {
                            System.out.println("Rock VS Rock: TIE");
                            ties = ties + 1;
                        } else if (player == paper) {
                            System.out.println("Paper VS Rock: WIN");
                            wins = wins + 1;
                        } else if (player == scissors) {
                            System.out.println("Scissors VS Rock: LOSS");
                            losses = losses + 1;
                        }
                    } else if (computer == paper) {
                        if (player == rock) {
                            System.out.println("Rock VS Paper: LOSS");
                            losses = losses + 1;
                        } else if (player == paper) {
                            System.out.println("Paper VS Paper: TIE");
                            ties = ties + 1;
                        } else if (player == scissors) {
                            System.out.println("Scissors Vs Paper: WIN");
                            wins = wins + 1;
                        }
                    } else if (computer == scissors) {
                        if (player == rock) {
                            System.out.println("Rock VS Scisors: WIN");
                            wins = wins + 1;
                        } else if (player == paper) {
                            System.out.println("Paper VS Scissors: LOSS");
                            losses = losses + 1;
                        } else if (player == scissors) {
                            System.out.println("Scissors VS Scissors: TIE");
                            ties = ties + 1;
                        }
                    }
                }

                System.out.print("  WINS: " + wins);
                System.out.print("  LOSSES: " + losses);
                System.out.print("  TIES: " + ties);
                if (wins == losses) {
                    System.out.println("\nYOU TIED");
                } else if (wins > losses) {
                    System.out.println("\nYOU WON");
                } else if (wins < losses) {
                    System.out.println("\nYOU LOST");
                }
                System.out.println("Play Again? (1)=yes (2)=no: ");
                playAgain = input.nextInt();
            }
        }

        System.out.println("Thanks for playing!");
    }
}
