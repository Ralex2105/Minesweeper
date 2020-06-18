package miner.view;


import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;

import miner.controller.CellController;
import miner.model.BoardModel;
import miner.model.CellModel;

public class CellView {
    public CellView(CellModel cell, BoardModel board){
        cell.setButton(new JButton());
        cell.getButton().setPreferredSize(new Dimension(10,10));
        cell.getButton().setMargin(new Insets(0,0,0,0));
        cell.setNotChecked(true);
        CellController controller = new CellController(cell, board);
        cell.getButton().addMouseListener(controller);
    }
}
