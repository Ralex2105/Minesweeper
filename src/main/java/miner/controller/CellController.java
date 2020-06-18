package miner.controller;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import miner.model.CellModel;
import miner.model.BoardModel;

public class CellController implements MouseListener{

    private CellModel cell;
    private	BoardModel board;

    public CellController(CellModel cell, BoardModel board) {
        this.cell = cell;
        this.board = board;
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
                BoardModel.planting(cell.getIndex());
                BoardModel.setCellValues();
                board.setBeginGame(false);
            }
            if(cell.getButton().isEnabled())
                BoardModel.checkCell(cell);
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
