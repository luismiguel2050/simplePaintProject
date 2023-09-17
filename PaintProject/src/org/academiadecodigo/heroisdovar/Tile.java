package org.academiadecodigo.heroisdovar;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * The Tile class represents a rectangular tile in a grid.
 * These tiles can be used for various purposes, such as creating a grid-based game board or a drawing canvas.
 */
public class Tile {

    // Instance variables
    private Rectangle tile;           // The graphical representation of the tile
    private boolean painted;          // A flag to indicate whether the tile has been painted

    private Color tileColor;          // The color of the tile

    /**
     * Constructs a new Tile object at the specified position.
     *
     * @param x The x-coordinate of the top-left corner of the tile.
     * @param y The y-coordinate of the top-left corner of the tile.
     */
    public Tile(int x, int y) {
        tileColor = Color.BLACK;       // Default color is black
        tile = new Rectangle(x, y, Grid.CELLSIZE, Grid.CELLSIZE);
        tile.setColor(tileColor);
        tile.draw();
    }

    /**
     * Gets the graphical representation of the tile.
     *
     * @return The Rectangle object representing the tile.
     */
    public Rectangle getTile() {
        return tile;
    }

    /**
     * Gets the color of the tile.
     *
     * @return The color of the tile as a Color object.
     */
    public Color getTileColor() {
        return tileColor;
    }

    /**
     * Sets the color of the tile.
     *
     * @param tileColor The new color for the tile.
     */
    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }

    /**
     * Checks if the tile has been painted.
     *
     * @return true if the tile has been painted, false otherwise.
     */
    public boolean isPainted() {
        return painted;
    }

    /**
     * Sets whether the tile has been painted or not.
     *
     * @param painted true if the tile has been painted, false otherwise.
     */
    public void setPainted(boolean painted) {
        this.painted = painted;
    }
}

