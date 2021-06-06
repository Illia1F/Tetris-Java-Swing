package forms;

import model.Cell;
import model.Grid;
import model.IGridView;

import javax.swing.*;
import java.util.List;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class GridView extends JPanel implements IGridView
{
    private Dimension _griSize;
    private int _cellWidth;
    private int _cellHeight;

    private Grid _grid;

    public GridView(Dimension size, int rows, int columns)
    {
        _griSize = new Dimension(columns, rows);
        _cellWidth = size.width / _griSize.width;
        _cellHeight = size.height / _griSize.height;

        _grid = new Grid(_griSize);

        setSize(size.width, size.height);
    }

    public Dimension getSize()
    {
        return _griSize;
    }

    public void setGrid(Grid grid)
    {
        Cell[][] cells = grid.getGrid();

        for(int i = 0; i < cells.length; i++)
        {
            for(int j = 0; j < cells[i].length; j++)
            {
                Cell cell = cells[i][j];
                if(cell != null)
                {
                    _grid.fillCellWithColor(cell.getLocation(), cell.getColor());
                }
            }
        }
    }

    public void setGrid(List<Point> cells, Color color)
    {
        _grid.fillCellsWithColor(cells, color);
    }

    public void clear()
    {
        _grid.clear();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g)
    {
        Cell[][] gridCells = _grid.getGrid();

        for(int column = 0; column < gridCells.length; column++)
        {
            for(int row = 0; row < gridCells[column].length; row++)
            {
                Cell cell = gridCells[column][row];

                if(cell != null)
                {
                    g.setColor(cell.getColor());
                    Point location = cell.getLocation();
                    g.fillRect(location.x * _cellWidth, location.y * _cellHeight, _cellWidth, _cellHeight);
                }

                g.setColor(Color.BLACK);
                g.drawRect(column * _cellWidth, row * _cellHeight, _cellWidth, _cellHeight);
            }
        }
    }
}