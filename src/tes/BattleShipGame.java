package tes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleShipGame {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private Scanner scanner;

    public BattleShipGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.currentPlayer = player1;
        this.otherPlayer = player2;
        this.scanner = new Scanner(System.in);
    }

    public void initializeShips(Player player) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Player " + player.getName() + ", enter the number of ships you want to add:");
    int numShips = scanner.nextInt();
    
    System.out.println("Berapa banyak kapal yang ingin anda siapkan:");
    int shipLength = scanner.nextInt();
    
    for (int i = 1; i <= numShips; i++) {
        System.out.println("Input Koordinat Kapal " + i + ":");
        List<int[]> shipCoordinates = new ArrayList<>();
        
        for (int j = 1; j <= shipLength; j++) {
            System.out.print("Input X koordinat " + j + ": ");
            int x = scanner.nextInt();
            System.out.print("Input Y koordinat " + j + ": ");
            int y = scanner.nextInt();
            
            shipCoordinates.add(new int[]{x, y});
        }
        
        Ship ship = new BattleShip(shipCoordinates);
        player.addShip(ship);
    }
    }

    public void printBoard(Player player) {
        boolean[][] board = player.getBoard();
        System.out.println("Map untuk " + player.getName() + ":");
        System.out.print("  ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void play() {
        initializeShips(player1);
        initializeShips(player2);

        while (true) {
            System.out.println(currentPlayer.getName() + "'s turn.");
            System.out.print("Input X koordinat tembakan : ");
            int x = scanner.nextInt();
            System.out.print("Input Y koordinat tembakan: ");
            int y = scanner.nextInt();

            currentPlayer.addShot(x, y);
            boolean hit = otherPlayer.hasShipAt(x, y);
            System.out.println(hit ? "Target hit!" : "Maaf, Serangan Meleset dari " + currentPlayer.getName() + "." );
            currentPlayer.getBoard()[x][y] = true;
            printBoard(currentPlayer);

            if (!hit) {
                Player temp = currentPlayer;
                currentPlayer = otherPlayer;
                otherPlayer = temp;
            }

            if (!otherPlayer.hasShipsRemaining()) {
                System.out.println(currentPlayer.getName() + " Selamat Anda Menang!");
                break;
            }
        }
    }
}
