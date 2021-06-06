package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Tetrominoe
{
    None(new int[][] { { 0, 0 }, { 0, 0 }, { 0, 0 }, { 0, 0 } }, Color.BLACK),
    Straight(new int[][] { { 0, -1 }, { 0, 0 }, { 0, 1 }, { 0, 2 } }, new Color(102, 102, 204)),
    Square(new int[][] { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } }, new Color(204, 102, 204)),
    TShape(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 0, 1 } }, new Color(204, 204, 102)),

    LShape(new int[][] { { 1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, new Color(218, 170, 0)),
    JShape(new int[][] { { -1, -1 }, { 0, -1 }, { 0, 0 }, { 0, 1 } }, new Color(102, 204, 204)),

    SShape(new int[][] { { 0, -1 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, new Color(102, 204, 102)),
    ZShape(new int[][] { { 0, -1 }, { 0, 0 }, { -1, 0 }, { -1, 1 } }, new Color(204, 102, 102));

    private int[][] _offsets;
    private Color _color;

    private Tetrominoe(int[][] locations, Color color)
    {
        _offsets = locations;
        _color = color;
    }

    public Color getColor()
    {
        return _color;
    }

    public List<Point> getOffsets()
    {
        List<Point> newOffsets = new ArrayList();

        for(int i = 0; i < _offsets.length; i++)
        {
            newOffsets.add(new Point(_offsets[i][0], _offsets[i][1]));
        }

        return newOffsets;
    }

    public static Tetrominoe getRndomTetrominoe()
    {
        var rand = new Random();
        int x = Math.abs(rand.nextInt()) % (Tetrominoe.values().length - 1) + 1;
        return Tetrominoe.values()[x];
    }
}
