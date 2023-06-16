package tes;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Ship> ships;
    private boolean[][] board;
    private List<int[]> shots;

    public Player(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.board = new boolean[7][7];
        this.shots = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public boolean hasShipsRemaining() {
    for (Ship ship : ships) {
        if (!ship.isSunk()) {
            return true;
        }
    }
    return false;
    }

    public boolean hasShipAt(int x, int y) {
        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void addShot(int x, int y) {
        shots.add(new int[]{x, y});
    }

    public List<int[]> getShots() {
        return shots;
    }
}
