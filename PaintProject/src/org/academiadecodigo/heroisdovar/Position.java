package org.academiadecodigo.heroisdovar;

/**
 * The Position class represents a position in a grid or on a canvas, specified by its x and y coordinates.
 */
public class Position {

    // Instance variables
    private int x;        // The x-coordinate of the position
    private int col;      // The column index associated with the position
    private int y;        // The y-coordinate of the position
    private int row;      // The row index associated with the position

    /**
     * Constructs a new Position object with the specified x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.col = x;
        this.row = y;
        this.x = x; // Optionally, you can include comments like this to provide context if needed.
        this.y = y; // Optionally, you can include comments like this to provide context if needed.
    }

    /**
     * Gets the x-coordinate of the position.
     *
     * @return The x-coordinate as an integer.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the column index associated with the position.
     *
     * @return The column index as an integer.
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the y-coordinate of the position.
     *
     * @return The y-coordinate as an integer.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the row index associated with the position.
     *
     * @return The row index as an integer.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x The new x-coordinate value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y The new y-coordinate value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Compares this position to another position for equality.
     *
     * @param position The position to compare with.
     * @return true if the positions have the same x and y coordinates, false otherwise.
     */
    public boolean equals(Position position) {
        return x == position.getX() && y == position.getY();
    }
}
