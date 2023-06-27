package Final;
import java.util.List;

public abstract class Ship {
     protected List<int[]> coordinates;
    protected boolean[] hits;

    public Ship(List<int[]> coordinates) {
        this.coordinates = coordinates;
        this.hits = new boolean[coordinates.size()];
    }

    public abstract boolean isHit(int x, int y);

    public boolean isSunk() {
        for (boolean hit : hits) {
            if (!hit) {
                return false;
            }
        }
        return true;
    }
}

class BattleShip extends Ship {
    public BattleShip(List<int[]> coordinates) {
        super(coordinates);
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
}