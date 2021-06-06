package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shape
{
    private Tetrominoe _type;
    private Point _location;
    private List<Point> _offsets;

    public Shape()
    {
        _location = new Point();
        _type = Tetrominoe.getRndomTetrominoe();
        _offsets = _type.getOffsets();
    }

    public void move(Directions direction)
    {
        switch (direction)
        {
            case Up -> _location.setLocation(_location.x, _location.y - 1);
            case Left -> _location.setLocation(_location.x - 1, _location.y);
            case Right -> _location.setLocation(_location.x + 1, _location.y);
            case Bottom -> _location.setLocation(_location.x, _location.y + 1);
        }
    }

    public Color getColor()
    {
        return _type.getColor();
    }

    public List<Point> getCells()
    {
        var cells = new ArrayList<Point>();
        for(var offset : _offsets)
        {
            cells.add(new Point(_location.x + offset.x, _location.y + offset.y));
        }

        return cells;
    }

    public void setLocation(int x, int y)
    {
        _location.setLocation(x, y);
    }

    public Point getLocation()
    {
        return _location;
    }

    public boolean contains(List<Point> cells)
    {
        boolean result = false;

        for(var cell : getCells())
        {
            if(cells.contains(cell))
            {
                result = true;
                break;
            }
        }

        return result;
    }

    public boolean isCollisionByX(int x)
    {
        return getCells().stream().anyMatch(el -> el.x == x);
    }

    public boolean isCollisionByY(int y)
    {
        return getCells().stream().anyMatch(el -> el.y == y);
    }

    public void rotate(Directions direction)
    {
        if(direction == Directions.Up || direction == Directions.Bottom)
            return;

        if (_type == Tetrominoe.Square)
            return;

        switch (direction)
        {
            case Left -> rotateLeft();
            case Right -> rotateRight();
        }
    }

    private void rotateLeft()
    {
        for(var offset : _offsets)
        {
            offset.setLocation(offset.y, -offset.x);
        }
    }

    private void rotateRight()
    {
        for(var offset : _offsets)
        {
            offset.setLocation(-offset.y, offset.x);
        }
    }
}
