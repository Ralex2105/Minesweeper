package miner.model;


import miner.view.BoardView;

import java.util.ArrayList;

public class BoardModel {

    private static int side;
    private static int mines;
    private static int limit;
    private static int cellIndex;
    private int sideLength;
    public static int countOfRevealed;
    public static int countOfNeedRevealed;
    private  static CellModel[][] cells;
    private boolean beginGame;

    public int getSide() {
        return side;
    }
    public void setSide(int side) {
        BoardModel.side = side;
    }
    public void setMines(int mines) {
        BoardModel.mines = mines;
    }
    public void setLimit(int limit) {
        BoardModel.limit = limit;
    }
    public void setCellIndex(int cellIndex) {
        BoardModel.cellIndex = cellIndex;
    }
    public int getSideLength() {
        return sideLength;
    }
    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
    public  void setCountOfRevealed(int countOfRevealed) {
        BoardModel.countOfRevealed = countOfRevealed;
    }
    public void setCountOfNeedRevealed(int countOfNeedRevealed) {
        BoardModel.countOfNeedRevealed = countOfNeedRevealed;
    }
    public CellModel[][] getCells() {
        return cells;
    }
    public void setCells(CellModel[][] cellModels) {
        BoardModel.cells = cellModels;
    }
    public boolean isBeginGame() {
        return beginGame;
    }
    public void setBeginGame(boolean beginGame) {
        this.beginGame = beginGame;
    }
    public static int getIndex() {
        int index = cellIndex;
        cellIndex++;
        return index;
    }
    public static void setCellValues() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if (cells[i][j].getValue() != -1) {
                    if (j >= 1 && cells[i][j - 1].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (j <= limit && cells[i][j + 1].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (i >= 1 && cells[i - 1][j].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (i <= limit && cells[i + 1][j].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (i >= 1 && j >= 1 && cells[i - 1][j - 1].getValue() == - 1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (i <= limit && j <= limit && cells[i + 1][j + 1].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (i >= 1 && j <= limit && cells[i - 1][j + 1].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                    if (i <= limit && j >= 1 && cells[i + 1][j - 1].getValue() == -1)
                        cells[i][j].setValue(cells[i][j].getValue() + 1);
                }
            }
        }
    }
    private static void scanForEmptyCells() {
        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                if (!cells[i][j].isNotChecked()) {
                    if (j >= 1 && cells[i][j - 1].isEmpty())
                        checkCell(cells[i][j - 1]);
                    if (j <= limit && cells[i][j + 1].isEmpty())
                        checkCell(cells[i][j + 1]);
                    if (i >= 1 && cells[i - 1][j].isEmpty())
                        checkCell(cells[i - 1][j]);
                    if (i <= limit && cells[i + 1][j].isEmpty())
                        checkCell(cells[i + 1][j]);
                    if (i >= 1 && j >= 1 && cells[i - 1][j - 1].isEmpty())
                        checkCell(cells[i - 1][j - 1]);
                    if (i <= limit && j <= limit && cells[i + 1][j + 1].isEmpty())
                        checkCell(cells[i + 1][j + 1]);
                    if (i >= 1 && j <= limit && cells[i - 1][j + 1].isEmpty())
                        checkCell(cells[i - 1][j + 1]);
                    if (i <= limit && j >= 1 && cells[i + 1][j - 1].isEmpty())
                        checkCell(cells[i + 1][j - 1]);
                }
            }
        }
    }
    public static void checkCell(CellModel cell){
        cell.getButton().setEnabled(false);
        CellModel.displayValue(cell);
        cell.setNotChecked(false);
        BoardModel.win();
        if(cell.getValue() == 0)
            BoardModel.scanForEmptyCells();
        if(cell.getValue() == -1)
            BoardModel.fail();
    }
    static void plus() {
        countOfRevealed++;
    }
    private static void win() {
        if (countOfRevealed >= countOfNeedRevealed)
            BoardView.gameWinOrLose(1);
    }
    private static void fail() {
        for (CellModel[] x : cells) {
            for (CellModel y : x) {
                reveal(y);
            }
        }
        BoardView.gameWinOrLose(0);
    }
    private static CellModel getCell(int index) {
        for (CellModel[] x : cells) {
            for (CellModel y : x) {
                if (y.getIndex() == index) return y;
            }
        }
        return null;
    }
    public static ArrayList<Integer> generateMinesLocation(int mine, int firstPress) {
        ArrayList<Integer> location = new ArrayList<Integer>();
        for (int i = 0; i < mine; ) {
            int random = (int) (Math.random() * side * side);
            if(random!= firstPress)
                if (!location.contains(random)) {
                    location.add(random);
                    i++;
                }
        }
        return location;
    }
    public static void planting(int firstPress) {
        ArrayList<Integer> location = BoardModel.generateMinesLocation(mines, firstPress);
        for (int i : location) {
            BoardModel.getCell(i).setValue(-1);
        }
    }
    private static void reveal(CellModel cell){
        CellModel.displayValue(cell);
        cell.getButton().setEnabled(false);
    }
}
