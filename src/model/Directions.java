package model;

public enum Directions
{
    Left,
    Right,
    Up,
    Bottom;

    public Directions reverse()
    {
        Directions direction = this;

        switch (this)
        {
            case Up -> direction = Bottom;
            case Bottom -> direction = Up;
            case Right -> direction = Left;
            case Left -> direction = Right;
        }

        return direction;
    }
}
