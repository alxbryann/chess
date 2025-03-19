package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Controller;
import java.awt.event.MouseAdapter;

public class View {

    private JPanel window;
    private JLabel[][] chessBoard;
    private JLabel[] horizontalCoordinate;
    private JLabel[] verticalCoordinate;
    private Controller controller;
    @SuppressWarnings("rawtypes")
    private ArrayList showMovements;

    private boolean lastAvailableMovementsShown;
    private int x, y;

    public View() {

        // Window presets
        JFrame chess = new JFrame("CHESS");
        chess.setSize(1280, 720);
        chess.setVisible(true);
        chess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chess.setLocationRelativeTo(null);
        chess.setResizable(false);
        window = new JPanel();
        window.setBounds(0, 0, 1280, 720);
        window.setVisible(true);
        window.setLayout(null);

        // Chessboard presets
        chessBoard = new JLabel[8][8];

        // Coordinates
        verticalCoordinate = new JLabel[8];
        horizontalCoordinate = new JLabel[8];

        // Draw game
        drawCoordinates();
        drawChessBoard();
        drawPieces();

        // Add window to chess
        chess.add(window);

        lastAvailableMovementsShown = false;

    }

    public void drawCoordinates() {
        int asciiCharacter = 65;
        x = 180;
        y = 610;

        for (int i = 0; i < horizontalCoordinate.length; i++) {
            horizontalCoordinate[i] = new JLabel();
            horizontalCoordinate[i].setText(String.valueOf((char) asciiCharacter++));
            horizontalCoordinate[i].setBounds(x, y, 70, 30);
            horizontalCoordinate[i].setOpaque(true);
            window.add(horizontalCoordinate[i]);
            x += 70;
        }

        x = 110;
        y = 50;

        for (int i = 0; i < verticalCoordinate.length; i++) {
            verticalCoordinate[i] = new JLabel();
            verticalCoordinate[i].setText(String.valueOf(i));
            verticalCoordinate[i].setBounds(x, y, 10, 70);
            verticalCoordinate[i].setOpaque(true);
            window.add(verticalCoordinate[i]);
            y += 70;
        }
    }

    public void drawChessBoard() {
        x = 150;
        y = 50;
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                chessBoard[i][j] = new JLabel();
                chessBoard[i][j].setOpaque(true);
                chessBoard[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                chessBoard[i][j].setBackground((i + j) % 2 == 0 ? Color.WHITE : Color.BLACK);
                chessBoard[i][j].setBounds(x, y, 70, 70);
                final int coordx = i;
                final int coordy = j;
                chessBoard[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Border border = chessBoard[coordx][coordy].getBorder();
                        if (border instanceof LineBorder) {
                            LineBorder lineBorder = (LineBorder) border;
                            if (lineBorder.getLineColor().equals(Color.GREEN) && lineBorder.getThickness() == 6) {
                                controller.moveInModel(coordx, coordy);
                                try {
                                    if (!showMovements.isEmpty())
                                    hideMovements();
                                } catch (Exception A) {
                                    System.out.println("exception 1");
                                }
                                
                            } else {
                                try {
                                    if (lastAvailableMovementsShown)
                                    if (!showMovements.isEmpty())
                                        hideMovements();
                                } catch (Exception A) {
                                    System.out.println("exception 2");
                                }
                                
                                controller.showMovements(coordx, coordy);
                                lastAvailableMovementsShown = true;
                            }
                        }
                    }
                });
                window.add(chessBoard[i][j]);
                x += 70;
            }
            x = 150;
            y += 70;
        }
    }

    public void drawPieces() {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(""));
        Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Draw black pawns
        for (int i = 0; i < chessBoard.length; i++) {
            originalIcon = new ImageIcon(getClass().getResource("/img/black_pawn.png"));
            scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);
            chessBoard[1][i].setIcon(scaledIcon);
        }

        // Draw white pawns
        for (int i = 0; i < chessBoard.length; i++) {
            originalIcon = new ImageIcon(getClass().getResource("/img/white_pawn.png"));
            scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(scaledImage);
            chessBoard[6][i].setIcon(scaledIcon);
        }

        // Draw black towers

        originalIcon = new ImageIcon(getClass().getResource("/img/black_rock.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[0][0].setIcon(scaledIcon);
        chessBoard[0][7].setIcon(scaledIcon);

        // Draw white towers
        originalIcon = new ImageIcon(getClass().getResource("/img/white_rock.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[7][0].setIcon(scaledIcon);
        chessBoard[7][7].setIcon(scaledIcon);

        // Draw black horses
        originalIcon = new ImageIcon(getClass().getResource("/img/black_horse.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[0][1].setIcon(scaledIcon);
        chessBoard[0][6].setIcon(scaledIcon);

        // Draw white horses
        originalIcon = new ImageIcon(getClass().getResource("/img/white_horse.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[7][6].setIcon(scaledIcon);
        chessBoard[7][1].setIcon(scaledIcon);

        // Draw black bishop
        originalIcon = new ImageIcon(getClass().getResource("/img/black_bishop.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[0][2].setIcon(scaledIcon);
        chessBoard[0][5].setIcon(scaledIcon);

        // Draw white bishop
        originalIcon = new ImageIcon(getClass().getResource("/img/white_bishop.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[7][5].setIcon(scaledIcon);
        chessBoard[7][2].setIcon(scaledIcon);

        // Draw black queen
        originalIcon = new ImageIcon(getClass().getResource("/img/black_queen.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[0][3].setIcon(scaledIcon);

        // Draw white queen
        originalIcon = new ImageIcon(getClass().getResource("/img/white_queen.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[7][3].setIcon(scaledIcon);

        // Draw black king
        originalIcon = new ImageIcon(getClass().getResource("/img/black_king.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[0][4].setIcon(scaledIcon);

        // Draw whtie king
        originalIcon = new ImageIcon(getClass().getResource("/img/white_king.png"));
        scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        scaledIcon = new ImageIcon(scaledImage);
        chessBoard[7][4].setIcon(scaledIcon);
    }

    // Show available movements per piece
    public void showMovements(ArrayList<Integer> movements) {
        showMovements = movements;
        if (!showMovements.isEmpty()) {
            for (int i = 0; i < showMovements.size(); i += 2) {
                int x = (int) showMovements.get(i);
                int y = (int) showMovements.get(i + 1);

                chessBoard[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 6));
            }
        }
    }

    // Hide last shown movements
    public void hideMovements() {
        for (int i = 0; i < showMovements.size(); i += 2) {
            int x = (int) showMovements.get(i);
            int y = (int) showMovements.get(i + 1);

            chessBoard[x][y].setBorder(BorderFactory.createLineBorder(Color.black, 1));
        }
    }

    // Move pieces
    public void moveInView(int[] movement) {
        JLabel temporally = chessBoard[movement[0]][movement[1]];
        chessBoard[movement[2]][movement[3]].setIcon(temporally.getIcon());
        chessBoard[movement[2]][movement[3]].setBorder(temporally.getBorder());

        temporally.setIcon(null);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
