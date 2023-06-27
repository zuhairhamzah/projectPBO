package Final;

import java.util.Scanner;

public class Main{
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input nama Player 1:");
        String player1Name = scanner.nextLine();
        System.out.print("Input nama Player 2:");
        String player2Name = scanner.nextLine();
        BattleLogic game = new BattleLogic(player1Name, player2Name);
        game.play();
        game.printHistory();
    }
}