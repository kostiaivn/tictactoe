package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame {

    JButton[][] button = new JButton[3][3];
    Dimension dimension;
    JPanel panel;
    JFrame frame;

    public Board() {

        frame = new JFrame("TicTacToe");
        JPanel windowContent = new JPanel();
        panel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 3);
        panel.setLayout(gridLayout);
        windowContent.add("Center", panel);

        frame.pack();
        frame.setSize(330, 350);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setContentPane(windowContent);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        dimension = new Dimension();
        dimension.width = 100;
        dimension.height = 100;

        initField();
    }

    public void initField() {

        BoardEngine engine = new BoardEngine(button);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j] = new JButton();
                frame.getContentPane().add(button[i][j]);
                button[i][j].setPreferredSize(dimension);
                button[i][j].addActionListener(engine);
                panel.add(button[i][j]);
            }
        }
    }

    public class BoardEngine implements ActionListener {
        public boolean turn;
        private JButton[][] button;
        public int moves;

        public BoardEngine(JButton[][] button) {
            turn = true;
            this.button = button;
            moves = 0;
        }

        public void actionPerformed(ActionEvent e) {
            JButton currentButton = (JButton) e.getSource();
            currentButton.setText(turn ? "X" : "O");
            currentButton.setEnabled(false);
            moves++;
            if (checkWin()) {
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++)
                        button[i][j].setEnabled(false);
            }
            turn = !turn;
        }

        public boolean checkWin() {
            String player = (turn ? "X" : "O");
            return (button[0][0].getText() == player && button[0][1].getText() == player && button[0][2].getText() == player)
                    || (button[1][0].getText() == player && button[1][1].getText() == player && button[1][2].getText() == player)
                    || (button[2][0].getText() == player && button[2][1].getText() == player && button[2][2].getText() == player)
                    || (button[0][0].getText() == player && button[1][0].getText() == player && button[2][0].getText() == player)
                    || (button[0][1].getText() == player && button[1][1].getText() == player && button[2][1].getText() == player)
                    || (button[0][2].getText() == player && button[1][2].getText() == player && button[2][2].getText() == player)
                    || (button[0][0].getText() == player && button[1][1].getText() == player && button[2][2].getText() == player)
                    || (button[2][0].getText() == player && button[1][1].getText() == player && button[0][2].getText() == player);
        }

        public boolean checkDraw() {
            return (moves == 9);
        }
    }

    public static void main(String[] args) {
        Board boardSample = new Board();
    }
}