package miner.cell;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import miner.board.BoardModel;

public class CellModel {

    private JButton button;
    private int value;
    private int index;
    private boolean notChecked;
    private ImageIcon flag = new ImageIcon(this.getClass().getResource("/2202.png"));

    public CellModel(BoardModel board) {
        new CellView(this, board);
    }

    boolean isEmpty(){
        return isNotChecked() && getValue() == 0;
    }

    public JButton getButton() {
        return button;
    }
    void setButton(JButton button) {
        this.button = button;
    }

    int getValue() {
        return value;
    }
    void setValue(int value) {
        this.value = value;
    }
    int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    boolean isNotChecked() {
        return notChecked;
    }
    void setNotChecked(boolean notChecked) {
        this.notChecked = notChecked;
    }

    ImageIcon getFlag() {
        return flag;
    }

}


