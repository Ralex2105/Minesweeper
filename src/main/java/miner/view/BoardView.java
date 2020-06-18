package miner.view;


import java.awt.GridLayout;

import javax.swing.*;

import miner.model.CellModel;
import miner.model.BoardModel;

public class BoardView {

    private BoardModel board;
    private static JFrame frame;

    private static JFrame getFrame() {
        return frame;
    }
    private void setFrame(JFrame frame) {
        BoardView.frame = frame;
    }
    public BoardView(BoardModel board) {
        JFrame frame = new JFrame();
        this.board = board;
        board.setBeginGame(true);
        setFrame(frame);

        frame.add(addCells());

        if (board.getSide() <= 8)
            board.setSideLength(175);
        if (board.getSide() <= 16)
            board.setSideLength(350);
        if (board.getSide() <= 64)
            board.setSideLength(700);

        board.setCellIndex(0);

        frame.setSize(board.getSideLength(), board.getSideLength());
        frame.setTitle("mineSweeper");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    private JPanel addCells() {
        JPanel panel = new JPanel(new GridLayout(board.getSide(), board.getSide()));
        board.setCells(new CellModel[board.getSide()][board.getSide()]);
        for (int i = 0; i < board.getSide(); i++) {
            for (int j = 0; j < board.getSide(); j++) {
                board.getCells()[i][j] = new CellModel(board);
                board.getCells()[i][j].setIndex(BoardModel.getIndex());
                panel.add(board.getCells()[i][j].getButton());
            }
        }
        return panel;
    }
    public static void gameWinOrLose(int game) {
        String dialogMessage = "You win";
        if (game == 0) dialogMessage = "You lose";
        Object[] options = {"Yes", "Exit"};
        JFrame frame = getFrame();
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
}
