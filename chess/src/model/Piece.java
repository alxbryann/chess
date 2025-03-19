package model;

import java.util.ArrayList;

public interface Piece {
    @SuppressWarnings("rawtypes")
    public ArrayList showMovements(int x, int y);
    public int[] move(int x, int y);
    public int[] getPosition();
    public void setPosition(int x, int y);
    public boolean isWhite();
}
