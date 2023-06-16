package tes;

import java.util.List;

public interface Ship {
    boolean isHit(int x, int y);
    boolean isSunk();
}
class BattleShip implements Ship {
    private List<int[]> coordinates;
    private boolean[] hits;

    public BattleShip(List<int[]> coordinates) {
        this.coordinates = coordinates;
        this.hits = new boolean[coordinates.size()];
    }

    @Override
    public boolean isHit(int x, int y) {
        for (int i = 0; i < coordinates.size(); i++) {
            int[] coord = coordinates.get(i);
            if (coord[0] == x && coord[1] == y) {
                hits[i] = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isSunk() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }
}