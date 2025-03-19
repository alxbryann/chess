package model;

import java.util.ArrayList;

import controller.Controller;

public class Model {

    private Piece[][] chessBoard;
    private Controller controller;
    private Piece lastTouchPiece;
    private boolean turn;

    public Model() {
        chessBoard = new Piece[8][8];

        for (int i = 0; i < chessBoard.length; i++) {
            chessBoard[1][i] = new Pawn();
            chessBoard[6][i] = new Pawn();
        }

        chessBoard[0][0] = new Rock();
        chessBoard[0][7] = new Rock();
        chessBoard[7][0] = new Rock();
        chessBoard[7][7] = new Rock();

        chessBoard[0][1] = new Horse();
        chessBoard[0][6] = new Horse();
        chessBoard[7][6] = new Horse();
        chessBoard[7][1] = new Horse();

        chessBoard[0][2] = new Bishop();
        chessBoard[0][5] = new Bishop();
        chessBoard[7][5] = new Bishop();
        chessBoard[7][2] = new Bishop();

        chessBoard[0][3] = new Queen();
        chessBoard[7][3] = new Queen();

        chessBoard[0][4] = new King();
        chessBoard[7][4] = new King();

        turn = true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void showMovements(int x, int y) {

        lastTouchPiece = chessBoard[x][y];
        ArrayList movements = new ArrayList<>();
        ArrayList canKill = new ArrayList<>();

        if (lastTouchPiece instanceof Pawn) {
            lastTouchPiece.setPosition(x, y);
            if (lastTouchPiece.isWhite() && turn || !lastTouchPiece.isWhite() && !turn) {
                movements = lastTouchPiece.showMovements(x, y);
                canKill = new ArrayList<>();
                canKill = canKill();
                if (!canKill.isEmpty())
                    for (int i = 0; i < canKill.size(); i++)
                        movements.add(canKill.get(i));

                controller.showMovements(movements);
            } else {
            }

        } else if (lastTouchPiece instanceof Rock) {
        } else if (lastTouchPiece instanceof Horse) {
        } else if (lastTouchPiece instanceof Bishop) {
        } else if (lastTouchPiece instanceof Queen) {
        } else if (lastTouchPiece instanceof King) {
        } else {
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ArrayList canKill() {

        ArrayList canKillPositions = new ArrayList<>();
        int x = lastTouchPiece.getPosition()[0];
        int y = lastTouchPiece.getPosition()[1];

        if (lastTouchPiece instanceof Pawn) {

            if (lastTouchPiece.isWhite()) {
                try {
                    Piece rightDiagonal = chessBoard[x - 1][y + 1];
                    if (!rightDiagonal.isWhite()) {
                        canKillPositions.add(x - 1);
                        canKillPositions.add(y + 1);
                    }
                } catch (Exception e) {
                }
                try {
                    Piece leftDiagonal = chessBoard[x - 1][y - 1];
                    if (!leftDiagonal.isWhite()) {
                        canKillPositions.add(x - 1);
                        canKillPositions.add(y - 1);
                    }
                } catch (Exception e) {
                }
            } else {
                try {
                    Piece rightDiagonal = chessBoard[x + 1][y + 1];
                    if (rightDiagonal.isWhite()) {
                        canKillPositions.add(x + 1);
                        canKillPositions.add(y + 1);
                    }
                } catch (Exception e) {
                }
                try {
                    Piece leftDiagonal = chessBoard[x + 1][y - 1];
                    if (leftDiagonal.isWhite()) {
                        canKillPositions.add(x + 1);
                        canKillPositions.add(y - 1);
                    }
                } catch (Exception e) {
                }
            }
            return canKillPositions;
        } else if (lastTouchPiece instanceof Rock) {
            
        } else if (lastTouchPiece instanceof Horse) {
        } else if (lastTouchPiece instanceof Bishop) {
        } else if (lastTouchPiece instanceof Queen) {
        } else if (lastTouchPiece instanceof King) {
        } else {
        }

        return canKillPositions;
    }

    @SuppressWarnings("rawtypes")
    public void checkCollisions(ArrayList movements) {
        for (int i = 0; i < movements.size(); i += 2) {
            try {
                chessBoard[(int) movements.get(i)][(int) movements.get(i + 1)].getPosition();
                System.out.println("Valid box");
            } catch (Exception e) {
                System.out.println("Occupied box");
            }
        }
    }

    public void moveInModel(int x, int y) {
        if (lastTouchPiece instanceof Pawn) {
            int[] movement = lastTouchPiece.move(x, y);
            chessBoard[movement[2]][movement[3]] = chessBoard[movement[0]][movement[1]];
            chessBoard[movement[0]][movement[1]] = null;
            controller.moveInView(movement);
        } else if (lastTouchPiece instanceof Rock) {
        } else if (lastTouchPiece instanceof Horse) {
        } else if (lastTouchPiece instanceof Bishop) {
        } else if (lastTouchPiece instanceof Queen) {
        } else if (lastTouchPiece instanceof King) {
        } else {
        }
        turner();
    }

    public void turner() {
        if (turn)
            turn = false;
        else
            turn = true;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
