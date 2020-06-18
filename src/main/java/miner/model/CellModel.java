package miner.model;


import javax.swing.ImageIcon;
import javax.swing.JButton;

import miner.view.CellView;

public class CellModel {

    private JButton button;
    private int value;
    private int index;
    private boolean notChecked;
    private ImageIcon flag = new ImageIcon(this.getClass().getResource("/2202.png"));

    public CellModel(BoardModel board) {
        new CellView(this, board);
    }

    public boolean isEmpty(){
        return isNotChecked() && getValue() == 0;
    }

    public JButton getButton() {
        return button;
    }
    public void setButton(JButton button) {
        this.button = button;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public boolean isNotChecked() {
        return notChecked;
    }
    public void setNotChecked(boolean notChecked) {
        this.notChecked = notChecked;
    }

    public ImageIcon getFlag() {
        return flag;
    }

}


