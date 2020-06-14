package miner.cell;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalButtonUI;

import miner.board.BoardModel;

public class CellController implements MouseListener{

    private	CellModel cell;
    private	BoardModel board;

    CellController(CellModel cell, BoardModel board) {
        this.cell = cell;
        this.board = board;
    }

    private void planting(int firstPress) {
        ArrayList<Integer> location = generateMinesLocation(board.getMines(), firstPress);
        for (int i : location) {
            getCell(i).setValue(-1);
        }
    }

    private ArrayList<Integer> generateMinesLocation(int mine, int firstPress) {
        ArrayList<Integer> location = new ArrayList<Integer>();

        for (int i = 0; i < mine; ) {
            int random = (int) (Math.random() * board.getSide() * board.getSide());
            if(random!= firstPress)
                if (!location.contains(random)) {
                    location.add(random);
                    i++;
                }
        }
        return location;
    }

    private void setCellValues() {
        for (int i = 0; i < board.getSide(); i++) {
            for (int j = 0; j < board.getSide(); j++) {
                if (board.getCells()[i][j].getValue() != -1) {
                    if (j >= 1 && board.getCells()[i][j - 1].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (j <= board.getLimit()&& board.getCells()[i][j + 1].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (i >= 1 && board.getCells()[i - 1][j].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (i <= board.getLimit() && board.getCells()[i + 1][j].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (i >= 1 && j >= 1 && board.getCells()[i - 1][j - 1].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (i <= board.getLimit() && j <= board.getLimit() && board.getCells()[i + 1][j + 1].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (i >= 1 && j <= board.getLimit() && board.getCells()[i - 1][j + 1].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                    if (i <= board.getLimit() && j >= 1 && board.getCells()[i + 1][j - 1].getValue() == -1)
                        board.getCells()[i][j].setValue(board.getCells()[i][j].getValue()+1);
                }
            }
        }
    }

    private void scanForEmptyCells() {
        for (int i = 0; i < board.getSide(); i++) {
            for (int j = 0; j < board.getSide(); j++) {
                if (!board.getCells()[i][j].isNotChecked()) {
                    if (j >= 1 && board.getCells()[i][j - 1].isEmpty())
                        checkCell(board.getCells()[i][j - 1]);
                    if (j <= board.getLimit() && board.getCells()[i][j + 1].isEmpty())
                        checkCell(board.getCells()[i][j + 1]);
                    if (i >= 1 && board.getCells()[i - 1][j].isEmpty())
                        checkCell(board.getCells()[i - 1][j]);
                    if (i <= board.getLimit() && board.getCells()[i + 1][j].isEmpty())
                        checkCell(board.getCells()[i + 1][j]);
                    if (i >= 1 && j >= 1 && board.getCells()[i - 1][j - 1].isEmpty())
                        checkCell(board.getCells()[i - 1][j - 1]);
                    if (i <= board.getLimit() && j <= board.getLimit() && board.getCells()[i + 1][j + 1].isEmpty())
                        checkCell(board.getCells()[i + 1][j + 1]);
                    if (i >= 1 && j <= board.getLimit() && board.getCells()[i - 1][j + 1].isEmpty())
                        checkCell(board.getCells()[i - 1][j + 1]);
                    if (i <= board.getLimit() && j >= 1 && board.getCells()[i + 1][j - 1].isEmpty())
                        checkCell(board.getCells()[i + 1][j - 1]);
                }
            }
        }
    }

    private void checkCell(CellModel cell){
        cell.getButton().setEnabled(false);
        displayValue(cell);
        cell.setNotChecked(false);
        win();
        if(cell.getValue() == 0)
            scanForEmptyCells();
        if(cell.getValue() == -1)
            fail();
    }

    private void displayValue(CellModel cell){
        if (cell.getValue()== -1) {
            cell.getButton().setText("\u2731");
            cell.getButton().setBackground(Color.RED);
        } else if (cell.getValue() != 0) {
            String variable = String.valueOf(cell.getValue());
            board.setCountOfRevealed(board.getCountOfRevealed()+1);

            cell.getButton().setText(variable);


            if (cell.getValue() == 1) cell.getButton().setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.cyan;
                }
            });

            if (cell.getValue() == 2) cell.getButton().setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.GREEN;
                }
            });

            if (cell.getValue() == 3) cell.getButton().setUI(new MetalButtonUI() {

                protected Color getDisabledTextColor() {
                    return Color.RED;
                }
            });

            if (cell.getValue() == 4) cell.getButton().setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.BLUE;
                }
            });

            if (cell.getValue() >= 5) cell.getButton().setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.BLACK;
                }
            });
        }
        else board.setCountOfRevealed(board.getCountOfRevealed()+1);
    }

    private CellModel getCell(int index) {
        for (CellModel[] x : board.getCells()) {
            for (CellModel y : x) {
                if (y.getIndex() == index) return y;
            }
        }
        return null;
    }

    private void win() {
        if (board.getCountOfRevealed() >= board.getCountOfNeedRevealed())
            gameWin();
    }

    private void reveal(CellModel cell){
        displayValue(cell);
        cell.getButton().setEnabled(false);
    }

    private void fail() {
        for (CellModel[] x : board.getCells()) {
            for (CellModel y : x) {
                reveal(y);
            }
        }
        gameLost();
    }

    private void gameLost() {
        String dialogMessage = "You are a loser";
        Object[] options = {"Yes", "Exit"};
        JFrame frame = board.getFrame();
        int n = JOptionPane.showOptionDialog( frame,
                dialogMessage + "\nDo you want to restart the game?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
        if (n == JOptionPane.YES_OPTION) {
            frame.dispose();
        } else if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    private void gameWin() {
        String dialogMessage = "You win";
        Object[] options = {"Yes", "Exit"};
        JFrame frame = board.getFrame();
        int n = JOptionPane.showOptionDialog(frame,
                dialogMessage + "\nDo you want to restart the game?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);
        if (n == JOptionPane.YES_OPTION) {
            frame.dispose();
        } else if (n == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if (cell.getButton().getIcon() == cell.getFlag()) {
                cell.getButton().setIcon(null);
            } else if (cell.getButton().getIcon() == null) {
                cell.getButton().setIcon(cell.getFlag());
            }
        } else if (SwingUtilities.isLeftMouseButton(e)) {
            cell.getButton().setIcon(null);
            if(board.isBeginGame()) {
                planting(cell.getIndex());
                setCellValues();
                board.setBeginGame(false);
            }
            if(cell.getButton().isEnabled())
                checkCell(cell);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
