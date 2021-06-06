package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Grid
{
    private Dimension _size;
    private Cell _grid[][];

    public Grid(Dimension size)
    {
        _size = size;
        _grid = new Cell[size.width][size.height];
    }

    public Cell[][] getGrid()
    {
        return _grid;
    }

    public List<Point> getCellLocations()
    {
        List<Point> points = new ArrayList();

        for(int i = 0; i < _size.width; i++)
        {
            for(int j = 0; j < _size.height; j++)
            {
                if(_grid[i][j] != null)
                {
                    points.add(_grid[i][j].getLocation());
                }
            }
        }

        return points;
    }

    public void fillCellsWithColor(List<Point> cells, Color color)
    {
        for(var cell : cells)
            fillCellWithColor(cell, color);
    }

    public void fillCellWithColor(Point cell, Color color)
    {
        if(cell.x >= 0 && cell.x < _size.width
                && cell.y >= 0 && cell.y < _size.height)
        {
            _grid[cell.x][cell.y] = new Cell(cell, color);
        }
    }

    public void clear()
    {
        _grid = new Cell[_size.width][_size.height];
    }

    private List<Integer> findFilledRows()
    {
        List<Integer> rows = new ArrayList();

        for(int i = 0; i < _size.height; i++)
        {
            int filledCells = 0;
            for(int j = 0; j < _size.width; j++)
            {
                if(_grid[j][i] != null)
                {
                    filledCells++;
                }
            }

            if(filledCells == _size.width)
            {
                rows.add(i);
            }
        }

        return rows;
    }

    public int deleteFilledRows()
    {
        var filledRows = findFilledRows();

        if(filledRows.size() > 0)
        {
            Cell[][] newGrid = new Cell[_size.width][_size.height];

            int newRow = _size.height - 1;
            for (int row1 = _size.height - 1; row1 >= 0; row1--)
            {
                if(filledRows.contains(row1) == false)
                {
                    for (int column1 = 0; column1 < _size.width; column1++)
                    {
                        Cell cell = _grid[column1][row1];
                        if (cell != null)
                        {
                            cell.setLocation(column1, newRow);
                            newGrid[column1][newRow] = cell;
                        }
                    }

                    newRow--;
                }
            }

            _grid = newGrid;
        }

        return filledRows.size();
    }
}
