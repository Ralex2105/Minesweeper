package miner.model;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.metal.MetalButtonUI;

import miner.view.CellView;

import java.awt.*;

public class CellModel {

    public static JButton button;
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
    public void setButton(JButton button) {
        CellModel.button = button;
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
    public static void displayValue(CellModel cell){
        if (cell.getValue()== -1) {
            cell.getButton().setText("\u2731");
            cell.getButton().setBackground(Color.RED);
        } else if (cell.getValue() != 0) {
            String variable = String.valueOf(cell.getValue());
            BoardModel.plus();
            cell.getButton().setText(variable);

            switch (cell.getValue()) {
                case (1): cell.getButton().setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.cyan;
                    }
                });
                    break;
                case (2): cell.getButton().setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.GREEN;
                    }
                });
                    break;
                case (3): cell.getButton().setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.RED;
                    }
                });
                    break;
                case (4): cell.getButton().setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.BLUE;
                    }
                });
                    break;
                default: cell.getButton().setUI(new MetalButtonUI() {
                    protected Color getDisabledTextColor() {
                        return Color.BLACK;
                    }
                });
                    break;
            }
        }
        else BoardModel.plus();
    }
}


