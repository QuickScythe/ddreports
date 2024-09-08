package me.ddreports;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your input: ");
        String input = scanner.nextLine();
        System.out.printf("You entered: %s%n", input);

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + scanner.nextLine());
        }
    }
}