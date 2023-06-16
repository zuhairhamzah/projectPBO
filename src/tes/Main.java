package tes;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter name for Player 1:");
        String player1Name = myObj.nextLine();
        System.out.println("Enter name for Player 2:");
        String player2Name = myObj.nextLine();

        BattleShipGame game = new BattleShipGame(player1Name, player2Name);
        game.play();
    }
}
