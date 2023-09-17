package org.academiadecodigo.heroisdovar;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Grid {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int COLS = 40;
    public static final int ROWS = 40;

    public static final int CELLSIZE = 20;

    public static final int PADDING = 10;

    private Rectangle window;

    private Tile[][] grid;


    private String[][] txtFile;


    public Grid() {
        window = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
        window.draw();

        grid = new Tile[ROWS][COLS];

        txtFile = new String[ROWS][COLS];
        //int x= PADDING;
        //int y= PADDING;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = new Tile(j * CELLSIZE + PADDING, i * CELLSIZE + PADDING);
                /*if(grid[i][j].isPainted()){
                    txtFile[i][j]="1";
                } else txtFile[i][j]="0";*/
            }
        }





        /*for (int i= CELLSIZE+PADDING; i <= WIDTH; i+=CELLSIZE){
            line= new Line(i,PADDING,i,HEIGHT+PADDING);
            line.draw();
        }

        for (int i= CELLSIZE+PADDING; i <= HEIGHT; i+=CELLSIZE){
            line= new Line(PADDING,i,WIDTH+PADDING,i);
            line.draw();
        }*/
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public String[][] getTxtFile() {
        return txtFile;
    }
}
