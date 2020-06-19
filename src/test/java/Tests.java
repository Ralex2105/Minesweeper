


import miner.model.BoardModel;
import miner.model.CellModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class Tests {

    @Test
    void firstPress() {
        BoardModel board = new BoardModel();
        board.setSide(5);
        board.setMines(1);
        board.setLimit(board.getSide() - 2);
        CellModel cells = new CellModel(board);
        BoardModel.generateMinesLocation(5, 14);
        cells.setIndex(14);
        assertEquals(0, cells.getValue());
        BoardModel.generateMinesLocation(5, 0);
        cells.setIndex(0);
        assertEquals(0, cells.getValue());
    }

    @Test
    void isNotCheckedOrChecked() {
        BoardModel board = new BoardModel();
        CellModel cells = new CellModel(board);
        board.setSide(5);
        board.setMines(1);
        board.setLimit(board.getSide() - 2);
        cells.setIndex(0);
        assertEquals(true, cells.isNotChecked());
        cells.setIndex(1);
        assertEquals(true, cells.isNotChecked());
        cells.setIndex(2);
        assertEquals(true, cells.isNotChecked());
        cells.setIndex(3);
        cells.setNotChecked(false);
        assertEquals(false, cells.isNotChecked());
        cells.setIndex(4);
        cells.setNotChecked(false);
        assertEquals(false, cells.isNotChecked());
        cells.setIndex(5);
        cells.setNotChecked(false);
        assertEquals(false, cells.isNotChecked());
    }

    @Test
    void valueWithoutBomb() {
        BoardModel board = new BoardModel();
        CellModel cell = new CellModel(board);
        board.setSide(2);
        board.setMines(0);
        BoardModel.planting(1);
        assertEquals(0, cell.getValue());
    }

    @Test
    void isWin() {
        BoardModel board = new BoardModel();
        CellModel cell = new CellModel(board);
        board.setSide(2);
        board.setMines(1);
        board.setLimit(board.getSide() - 2);
        board.setCountOfNeedRevealed(3);
        cell.setIndex(1);
        cell.setValue(0);
        CellModel.displayValue(cell);
        cell.setIndex(2);
        cell.setValue(0);
        CellModel.displayValue(cell);
        assertEquals(2, BoardModel.countOfRevealed);
        cell.setIndex(3);
        cell.setValue(0);
        CellModel.displayValue(cell);
        assertEquals(BoardModel.countOfNeedRevealed, BoardModel.countOfRevealed);
    }

    @Test
    void isFail() {
        BoardModel board = new BoardModel();
        CellModel cell = new CellModel(board);
        cell.setValue(-1);
        cell.setButton(CellModel.button);
        CellModel.displayValue(cell);
        assertEquals(Color.RED, cell.getButton().getBackground());
    }
}