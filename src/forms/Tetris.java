package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.*;
import model.Shape;

public class Tetris extends JFrame
{
    private final int PERIOD_INTERVAL = 500;

    private boolean isPaused;
    private int _score;
    private Timer timer;
    private IGridView _gridView;
    private Grid _grid;
    private Shape shape;

    private JLabel _lbGameOver;
    private JLabel _lbLegend;
    private JLabel _lbScore;

    public Tetris(IGridView gridView, JLabel lbGameOver, JLabel lbLegend, JLabel lbScore)
    {
        _gridView = gridView;
        _grid = new Grid(gridView.getSize());
        _lbGameOver = lbGameOver;
        _lbLegend = lbLegend;
        _lbScore = lbScore;

        isPaused = false;
        _score = 0;

        initialize();
        start();
    }

    private void initialize()
    {
        _lbGameOver.setText("GAME OVER");
        _lbScore.setText("Score: " + _score);
        _lbLegend.setText("<html> ← left<br/>"
                + "→ right<br/>"
                + "↑ rotate left<br/>"
                + "↓ rotate right<br/>"
                + "Space - drop down<br/>"
                + "Enter - restart<br/>"
                + "N - pause</html>");

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();

                switch (keycode)
                {
                    case KeyEvent.VK_ENTER -> restart();
                    case KeyEvent.VK_N -> isPaused = !isPaused;
                }

                if(isPaused == false)
                {
                    switch (keycode) {
                        case KeyEvent.VK_LEFT -> moveShape(Directions.Left);
                        case KeyEvent.VK_RIGHT -> moveShape(Directions.Right);
                        case KeyEvent.VK_DOWN -> rotateShape(Directions.Right);
                        case KeyEvent.VK_UP -> rotateShape(Directions.Left);
                        case KeyEvent.VK_SPACE -> dropShapeDown();
                    }
                }
            }
        });
    }

    private void start()
    {
        timer = new Timer(PERIOD_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });

        timer.start();
        createShape();
    }

    private void restart()
    {
        _score = 0;
        _lbScore.setText("Score: 0");
        _lbGameOver.setVisible(false);
        isPaused = false;
        _grid.clear();
        createShape();
    }

    private void update()
    {
        if(isPaused == false)
        {
            moveShape(Directions.Bottom);
        }
    }

    private boolean moveShape(Directions direction)
    {
        boolean result = false;

        shape.move(direction);

        var size = _gridView.getSize();
        if (shape.isCollisionByX(-1) || shape.isCollisionByX(size.width))
        {
            shape.move(direction.reverse());
        }
        else if((direction == Directions.Left || direction == Directions.Right) && shape.contains(_grid.getCellLocations()))
        {
            shape.move(direction.reverse());
        }
        else
        {
            if ((direction == Directions.Bottom && shape.contains(_grid.getCellLocations()))
                    || shape.isCollisionByY(size.height))
            {
                shape.move(direction.reverse());
                dropShape();
            }
            else
            {
                result = true;
            }

            draw();
        }

        return result;
    }

    private void rotateShape(Directions direction)
    {
        shape.rotate(direction);

        var size = _gridView.getSize();
        if (shape.isCollisionByX(-1) || shape.isCollisionByX(size.width)
                || shape.isCollisionByY(size.height) || shape.contains(_grid.getCellLocations()))
        {
            shape.rotate(direction.reverse());
        }
        else
        {
            draw();
        }
    }

    private void draw()
    {
        _gridView.clear();
        _gridView.setGrid(_grid);
        _gridView.setGrid(shape.getCells(), shape.getColor());
        _gridView.repaint();
    }

    private void dropShapeDown()
    {
        while(moveShape(Directions.Bottom));
    }

    private void createShape()
    {
        shape = new Shape();
        shape.setLocation(_gridView.getSize().width / 2, 0);
        draw();
        tryGameOver();
    }

    private void tryGameOver()
    {
        if(shape.contains(_grid.getCellLocations()))
        {
            isPaused = true;
            _lbGameOver.setVisible(true);
        }
    }

    private void dropShape()
    {
        _grid.fillCellsWithColor(shape.getCells(), shape.getColor());
        _score += _grid.deleteFilledRows();
        _lbScore.setText("Score: " + _score);
        createShape();
    }

    @Override
    public void dispose()
    {
        super.dispose();
        timer.stop();
    }
}