package miner.model;


import miner.controller.CellController;
import miner.view.BoardView;

import javax.swing.JFrame;

public class BoardModel {

    private int side;
    private int mines;
    private int limit;
    private int cellIndex;
    private int sideLength;
    private int countOfRevealed;
    private int countOfNeedRevealed;
    private  CellModel[][] cells;
    private boolean beginGame;
    private JFrame frame;


    public int getSide() {
        return side;
    }
    public void setSide(int side) {
        this.side = side;
    }
    public int getMines() {
        return mines;
    }
    public void setMines(int mines) {
        this.mines = mines;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getCellIndex() {
        return cellIndex;
    }
    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }
    public int getSideLength() {
        return sideLength;
    }
    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
    public int getCountOfRevealed() {
        return countOfRevealed;
    }
    public void setCountOfRevealed(int countOfRevealed) {
        this.countOfRevealed = countOfRevealed;
    }
    public int getCountOfNeedRevealed() {
        return countOfNeedRevealed;
    }
    public void setCountOfNeedRevealed(int countOfNeedRevealed) {
        this.countOfNeedRevealed = countOfNeedRevealed;
    }
    public CellModel[][] getCells() {
        return cells;
    }
    public void setCells(CellModel[][] cellModels) {
        this.cells = cellModels;
    }
    public boolean isBeginGame() {
        return beginGame;
    }
    public void setBeginGame(boolean beginGame) {
        this.beginGame = beginGame;
    }
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}

