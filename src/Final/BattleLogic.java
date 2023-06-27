package Final;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BattleLogic {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private Scanner scanner;
    private boolean[][] board;

    public BattleLogic(String player1Name, String player2Name) {
        this.player1 = new BattleShipPlayer(player1Name);
        this.player2 = new BattleShipPlayer(player2Name);
        this.currentPlayer = player1;
        this.otherPlayer = player2;
        this.scanner = new Scanner(System.in);
        this.board = new boolean[10][10];
    }

    public void initializeShips(Player player) {
        System.out.println("Player " + player.getName() + ", Masukkan berapa kapal yang ingin ditambahkan :");
        int numShips = scanner.nextInt();

        System.out.println("Berapa banyak kapal yang ingin anda siapkan:");
        int shipLength = scanner.nextInt();

        for (int i = 1; i <= numShips; i++) {
            System.out.println("Input Koordinat Kapal " + i + ":");
            List<int[]> shipCoordinates = new ArrayList<>();

            for (int j = 1; j <= shipLength; j++) {
                int x, y;
                boolean coordinatesValid = false;

                while (!coordinatesValid) {
                    System.out.println("Kapal Ke-" + j);
                    System.out.print("Input X koordinat " + j + ": ");
                    x = scanner.nextInt();
                    System.out.print("Input Y koordinat " + j + ": ");
                    y = scanner.nextInt();

                    if (x >= 0 && x < 10 && y >= 0 && y < 10 && !board[x][y]) {
                        coordinatesValid = true;
                        shipCoordinates.add(new int[]{x, y});
                        board[x][y] = true;
                    } else {
                        System.out.println("Koordinat tidak valid atau sudah ada. Masukkan koordinat yang berbeda.");
                    }
                }
            }

            Ship ship = new BattleShip(shipCoordinates);
            player.addShip(ship);
        }
    }

    public void printBoard(boolean[][] board) {
        System.out.println("Map :");
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

            if (x >= 0 && x < 10 && y >= 0 && y < 10) {
                currentPlayer.addShot(x, y);
                boolean hit = otherPlayer.hasShipAt(x, y);
                System.out.println(hit ? "Target hit!" : "Maaf, Serangan Meleset dari " + currentPlayer.getName() + ".");
                currentPlayer.getBoard()[x][y] = true;
                printBoard(currentPlayer.getBoard());

                if (!hit) {
                    Player temp = currentPlayer;
                    currentPlayer = otherPlayer;
                    otherPlayer = temp;
                }
                System.out.println("Sisa kapal " + currentPlayer.getName() + ": " + currentPlayer.getRemainingShips());

                if (!otherPlayer.hasShipsRemaining()) {
                    System.out.println(currentPlayer.getName() + " Selamat Anda Menang!");
                    break;
                }
            } else {
                System.out.println("Koordinat tidak valid. Masukkan koordinat yang berbeda.");
            }
        }
    }

    public void printHistory() {
        System.out.println("History:");
        List<int[]> history1 = ((BattleShipPlayer) player1).getHistory();
        List<int[]> history2 = ((BattleShipPlayer) player2).getHistory();
        System.out.println(player1.getName() + ":");
        for (int[] shot : history1) {
            System.out.println("(" + shot[0] + ", " + shot[1] + ")");
        }
        System.out.println(player2.getName() + ":");
        for (int[] shot : history2) {
            System.out.println("(" + shot[0] + ", " + shot[1] + ")");
        }
    }
}