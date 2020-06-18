package miner.view;


import java.awt.GridLayout;

import javax.swing.*;

import miner.model.CellModel;
import miner.model.BoardModel;

public class BoardView {
    private BoardModel board;

    public BoardView(BoardModel board) {
        JFrame frame = new JFrame();
        this.board = board;
        board.setBeginGame(true);
        board.setFrame(frame);

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
                board.getCells()[i][j].setIndex(getIndex());
                panel.add(board.getCells()[i][j].getButton());
            }
        }
        return panel;
    }

    private int getIndex() {
        int index = board.getCellIndex();
        board.setCellIndex(board.getCellIndex() + 1);
        return index;
    }
}
