package Final;

import java.util.List;
import java.util.ArrayList;

public interface Player {
    String getName();
    void addShip(Ship ship);
    boolean hasShipsRemaining();
    boolean hasShipAt(int x, int y);
    boolean[][] getBoard();
    void addShot(int x, int y);
    List<int[]> getShots();
    int getRemainingShips();
}

class BattleShipPlayer implements Player {
    private String name;
    private List<Ship> ships;
    private boolean[][] board;
    private List<int[]> shots;
    private List<int[]> history;

    public BattleShipPlayer(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.board = new boolean[10][10];
        this.shots = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addShip(Ship ship) {
        ships.add(ship);
    }

    @Override
    public boolean hasShipsRemaining() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasShipAt(int x, int y) {
        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean[][] getBoard() {
        return board;
    }

    @Override
    public void addShot(int x, int y) {
        shots.add(new int[]{x, y});
        history.add(new int[]{x, y});
    }

    @Override
    public List<int[]> getShots() {
        return shots;
    }

    public List<int[]> getHistory() {
        return history;
    }
    
    @Override
    public int getRemainingShips() {
        int count = 0;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                count++;
            }
        }
        return count;
    }
}