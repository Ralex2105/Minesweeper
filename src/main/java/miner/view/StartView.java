package miner.view;


import miner.controller.StartController;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class StartView {

    public StartView(){
        JTextField	side = new JTextField(3);
        side.setToolTipText("Side");
        JTextField  amountMines = new JTextField(3);
        amountMines.setToolTipText("Mines");

        StartController startController = new StartController();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton startEasyGame = new JButton("Start");
        panel.add(startEasyGame);

        startController.startGame(startEasyGame, side, amountMines);

        panel.add(side);
        panel.add(amountMines);

        JButton eGame = new JButton("Exit");
        panel.add(eGame);
        startController.endGame(eGame);

        JFrame startFrame = new JFrame("MineSweeper");
        startFrame.setContentPane(panel);
        startFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startFrame.setSize(400, 72);
        startFrame.setVisible(true);
    }
}
