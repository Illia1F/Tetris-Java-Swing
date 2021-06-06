package model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;
import java.util.List;

public interface IGridView
{
    Dimension getSize();
    void setGrid(Grid grid);
    void setGrid(List<Point> cells, Color color);
    void clear();
    void repaint();
}
