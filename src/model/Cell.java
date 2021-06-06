package model;

import java.awt.Point;
import java.awt.Color;

public class Cell
{
    private Point _location;
    private Color _color;

    public Cell(Point location, Color color)
    {
        _location = location;
        _color = color;
    }

    public Color getColor()
    {
        return _color;
    }

    public Point getLocation()
    {
        return _location;
    }

    public void setLocation(int x, int y)
    {
        _location.setLocation(x, y);
    }
}
