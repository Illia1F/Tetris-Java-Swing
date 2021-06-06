package app;

import forms.GridView;
import forms.Tetris;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App
{
    private static final int width = 540;
    private static final int height = 540;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                showTetris();
            }
        });
    }

    private static void showTetris()
    {
        final int gridWidth = 280;
        final int gridHeight = 420;
        final int gridRows = 12;
        final int gridColumns = 8;

        GridView grid = new GridView(new Dimension(gridWidth, gridHeight), gridRows, gridColumns);
        grid.setBounds(20, 60, gridWidth, gridHeight);
        grid.setBackground(Color.WHITE);
        grid.setBorder(new LineBorder(Color.BLACK));

        JLabel lbGameOver = new JLabel();
        lbGameOver.setName("_lbGameOver");
        lbGameOver.setBounds(70, 60, gridWidth, gridHeight / 2);
        lbGameOver.setFont(new Font(lbGameOver.getName(), Font.BOLD, 36));
        lbGameOver.setVisible(false);

        JLabel lbLegend = new JLabel();
        lbLegend.setName("_lbLegend");
        lbLegend.setBounds(320, 60, 200, gridHeight / 2);
        lbLegend.setFont(new Font(lbLegend.getName(), Font.BOLD, 20));
        lbLegend.setVisible(true);

        JLabel lbScore = new JLabel();
        lbScore.setName("_lbScore");
        lbScore.setBounds(20, 10, 100, 50);
        lbScore.setFont(new Font(lbScore.getName(), Font.BOLD, 20));
        lbScore.setVisible(true);

        Tetris tetris = new Tetris(grid, lbGameOver, lbLegend, lbScore);
        tetris.add(lbGameOver);
        tetris.add(lbLegend);
        tetris.add(lbScore);
        tetris.add(grid);

        tetris.setTitle("Tetris");
        tetris.setResizable(false);
        tetris.getContentPane().setLayout(null);
        tetris.setSize(width, height);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        tetris.setLocation(dim.width/2 - width/2, dim.height/2 - height/2);
        tetris.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                tetris.dispose();
                System.exit(0);
            }
        });
        tetris.setVisible(true);
    }
}
