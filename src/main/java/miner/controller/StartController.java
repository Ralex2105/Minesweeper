package miner.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import miner.model.BoardModel;
import miner.view.BoardView;

public class StartController {

    public void endGame(JButton endGame) {
        endGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public void startGame(JButton startGame, final JTextField side, final JTextField amountMines) {
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(side.getText().equals("") && amountMines.getText().equals(""))
                {
                    BoardModel board = new BoardModel();

                    board.setSide(8);
                    board.setLimit(6);
                    board.setMines(10);
                    board.setCountOfRevealed(10);
                    board.setCountOfNeedRevealed(64);

                    new BoardView(board);
                }
                else if(Integer.parseInt(side.getText())*Integer.parseInt(side.getText())
                        <= Integer.parseInt(amountMines.getText())
                    )
                        JOptionPane.showMessageDialog(null,
                                "Количество клеток должно быть больше количества мин");
                    else {
                        BoardModel board = new BoardModel();

                        board.setSide(Integer.parseInt(side.getText()));
                        board.setMines(Integer.parseInt(amountMines.getText()));
                        board.setLimit(Integer.parseInt(side.getText()) - 2);
                        board.setCountOfNeedRevealed((Integer.parseInt(side.getText()))
                                * (Integer.parseInt(side.getText())));
                        board.setCountOfRevealed(Integer.parseInt(amountMines.getText()));

                        new BoardView(board);
                    }
            }
        });
    }
}

