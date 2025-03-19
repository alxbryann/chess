package model;

import java.util.ArrayList;

public class Pawn implements Piece {

    private int x, y;
    private boolean isFirstTime = true;
    private boolean isWhite;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public ArrayList showMovements(int x, int y) {
        ArrayList movements = new ArrayList<>();
        this.x = x;
        this.y = y;
    
        if (isWhite) {
            if (x == 6) {
                movements.add(x - 1);
                movements.add(y);
                movements.add(x - 2);
                movements.add(y);
            } else {
                movements.add(x - 1);
                movements.add(y);
            }
        } else {
            if (x == 1) {
                movements.add(x + 1);
                movements.add(y);
                movements.add(x + 2);
                movements.add(y);
            } else {
                movements.add(x + 1);
                movements.add(y);
            }

        }
        return movements;
    }

    @Override
    public int[] move(int x, int y) {
        int[] movement = new int[4];
        movement[0] = this.x;
        movement[1] = this.y;
        movement[2] = x;
        movement[3] = y;
        return movement;
    }
    
    @Override
    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = x;
        position[1] = y;
        return position;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isWhite() {
        if (isFirstTime) {
            if (x == 6)
                isWhite = true;
            else
                isWhite = false;
            isFirstTime = false;
        }
        return isWhite;
    }

}
