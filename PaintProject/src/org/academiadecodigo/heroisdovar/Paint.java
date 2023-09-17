package org.academiadecodigo.heroisdovar;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;

public class Paint {

    private final Rectangle cursor;

    private final Grid grid;

    private double cursorSize;

    private boolean pressed;

    private Tile tile;

    private Color color;

    private boolean painting;

    private boolean erasing;


    public Paint() {
        cursorSize = Grid.CELLSIZE;
        color = Color.BLACK;
        grid = new Grid();
        cursor = new Rectangle(Grid.PADDING, Grid.PADDING, Grid.CELLSIZE, Grid.CELLSIZE);
        InputHandler inputHandler = new InputHandler();
        cursor.setColor(Color.DARK_GRAY);
        cursor.fill();
    }

    public Rectangle getCursor() {
        return cursor;
    }

    public void growThatShit(double num1, double num2) {

        cursor.grow(num1, num2);
    }


    public void paint() {
        setPainting(true);
        for (int i = 0; i < cursorSize; i += Grid.CELLSIZE) {
            for (int j = 0; j < cursorSize; j += Grid.CELLSIZE) {
                tile = grid.getGrid()[((cursor.getY() + i) / Grid.CELLSIZE)][((cursor.getX() + j) / Grid.CELLSIZE)];

                if (!tile.isPainted() || tile.getTileColor() != color) {
                    tile.setTileColor(color);
                    tile.getTile().setColor(color);

                    tile.getTile().fill();
                    tile.setPainted(true);
                }
            }
        }
    }

    public void erase() {
        setErasing(true);
        setPainting(false);
        for (int i = 0; i < cursorSize; i += Grid.CELLSIZE) {
            for (int j = 0; j < cursorSize; j += Grid.CELLSIZE) {
                tile = grid.getGrid()[((cursor.getY() + i) / Grid.CELLSIZE)][((cursor.getX() + j) / Grid.CELLSIZE)];


                if (tile.isPainted()) {
                    tile.getTile().setColor(Color.BLACK);
                    tile.getTile().draw();
                    tile.setPainted(false);

                }
            }
        }


    }

    public void saveFile() throws IOException {

        FileWriter writer = new FileWriter("resources/save.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int i = 0; i < Grid.ROWS; i++) {
            for (int j = 0; j < Grid.COLS; j++) {

                if (!grid.getGrid()[i][j].isPainted()) {
                    grid.getTxtFile()[i][j] = "0";
                } else if (grid.getGrid()[i][j].getTileColor() == Color.BLACK) {
                    grid.getTxtFile()[i][j] = "1";
                } else if (grid.getGrid()[i][j].getTileColor() == Color.RED) {
                    grid.getTxtFile()[i][j] = "2";
                } else if (grid.getGrid()[i][j].getTileColor() == Color.GREEN) {
                    grid.getTxtFile()[i][j] = "3";
                } else if (grid.getGrid()[i][j].getTileColor() == Color.BLUE) {
                    grid.getTxtFile()[i][j] = "4";
                }
                bufferedWriter.write(grid.getTxtFile()[i][j]);

            }
            bufferedWriter.write("\n");
            bufferedWriter.flush();

        }
        bufferedWriter.close();
    }

    public void loadFile() throws IOException {
        System.out.println("loading...");
        FileReader reader = new FileReader("resources/save.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String[] lineArray;
        String line;
        int cols = 0;
        int rows = 0;

        while (rows < Grid.ROWS) {
            line = bufferedReader.readLine();
            lineArray = line.split("");
            while (cols < Grid.COLS) {
                grid.getTxtFile()[rows][cols] = lineArray[cols];
                System.out.println();
                cols++;
            }
            cols = 0;
            rows++;
        }
        for (int i = 0; i < Grid.ROWS; i++) {
            for (int j = 0; j < Grid.COLS; j++) {

                if (grid.getTxtFile()[i][j].equals("1")) {
                    grid.getGrid()[i][j].getTile().setColor(Color.BLACK);
                    grid.getGrid()[i][j].getTile().fill();
                    grid.getGrid()[i][j].setPainted(true);
                } else if (grid.getTxtFile()[i][j].equals("2")) {
                    grid.getGrid()[i][j].getTile().setColor(Color.RED);
                    grid.getGrid()[i][j].getTile().fill();
                    grid.getGrid()[i][j].setPainted(true);
                } else if (grid.getTxtFile()[i][j].equals("3")) {
                    grid.getGrid()[i][j].getTile().setColor(Color.GREEN);
                    grid.getGrid()[i][j].getTile().fill();
                    grid.getGrid()[i][j].setPainted(true);
                } else if (grid.getTxtFile()[i][j].equals("4")) {
                    grid.getGrid()[i][j].getTile().setColor(Color.BLUE);
                    grid.getGrid()[i][j].getTile().fill();
                    grid.getGrid()[i][j].setPainted(true);
                }


            }
        }


    }

    public void clearScreen() {
        for (int i = 0; i < Grid.ROWS; i++) {
            for (int j = 0; j < Grid.COLS; j++) {
                grid.getGrid()[i][j].getTile().setColor(Color.BLACK);
                grid.getGrid()[i][j].setTileColor(Color.BLACK);
                grid.getGrid()[i][j].getTile().draw();


            }
        }
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isErasing() {
        return erasing;
    }

    public void setErasing(boolean erasing) {
        this.erasing = erasing;
    }

    public boolean isPainting() {
        return painting;
    }

    public void setPainting(boolean painting) {
        this.painting = painting;
    }

    public class InputHandler implements KeyboardHandler {

        private final Keyboard keyboard;
        private final KeyboardEvent[] events;

        private KeyboardEvent releaseSpace;


        public InputHandler() {
            keyboard = new Keyboard(this);
            events = new KeyboardEvent[20];
            createEvents();


        }

        private void createEvents() {
            releaseSpace = new KeyboardEvent();
            releaseSpace.setKey(KeyboardEvent.KEY_SPACE);
            releaseSpace.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
            keyboard.addEventListener(releaseSpace);
            for (int i = 0; i < events.length; i++) {
                events[i] = new KeyboardEvent();
            }

            events[0].setKey(KeyboardEvent.KEY_DOWN);
            events[1].setKey(KeyboardEvent.KEY_RIGHT);
            events[2].setKey(KeyboardEvent.KEY_LEFT);
            events[3].setKey(KeyboardEvent.KEY_UP);
            events[4].setKey(KeyboardEvent.KEY_SPACE);
            events[5].setKey(KeyboardEvent.KEY_S);
            events[6].setKey(KeyboardEvent.KEY_L);
            events[7].setKey(KeyboardEvent.KEY_C);
            events[8].setKey(KeyboardEvent.KEY_1);
            events[9].setKey(KeyboardEvent.KEY_2);
            events[10].setKey(KeyboardEvent.KEY_3);
            events[11].setKey(KeyboardEvent.KEY_4);
            events[12].setKey(KeyboardEvent.KEY_Q);
            events[13].setKey(KeyboardEvent.KEY_W);

            for (KeyboardEvent event : events) {
                event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
                keyboard.addEventListener(event);
            }
        }


        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_Q:
                    if (cursor.getY() > Grid.PADDING && cursor.getY() + cursorSize < Grid.HEIGHT && cursor.getX() > Grid.PADDING && cursor.getX() + cursorSize < Grid.WIDTH)
                        growThatShit(Grid.CELLSIZE, Grid.CELLSIZE);
                    cursorSize = cursor.getHeight();
                    break;
               case KeyboardEvent.KEY_W:
                    if(cursor.getHeight()>Grid.CELLSIZE) {
                        growThatShit(-Grid.CELLSIZE, -Grid.CELLSIZE);
                        cursorSize = cursor.getHeight();
                    }
                //break;
                case KeyboardEvent.KEY_1:
                    color = Color.BLACK;

                    break;
                case KeyboardEvent.KEY_2:
                    color = Color.RED;
                    break;
                case KeyboardEvent.KEY_3:
                    color = Color.GREEN;
                    break;
                case KeyboardEvent.KEY_4:
                    color = Color.BLUE;
                    break;
                case KeyboardEvent.KEY_UP:
                    System.out.println(cursor.getY());
                    if (cursor.getY() > Grid.PADDING) {

                        cursor.translate(0, -Grid.CELLSIZE);
                        if (pressed && painting) paint();
                        else if (pressed && erasing) erase();
                    }
                    break;
                case KeyboardEvent.KEY_DOWN:
                    if (cursor.getY() + cursorSize < Grid.HEIGHT) {

                        cursor.translate(0, Grid.CELLSIZE);
                        if (pressed && painting) {
                            System.out.println("down");
                            paint();
                        } else if (pressed && erasing) {
                            erase();
                        }

                    }
                    break;
                case KeyboardEvent.KEY_LEFT:
                    if (cursor.getX() > Grid.PADDING) {

                        cursor.translate(-Grid.CELLSIZE, 0);
                        if (pressed && painting) paint();
                        else if (pressed && erasing) erase();
                    }
                    break;
                case KeyboardEvent.KEY_RIGHT:
                    if (cursor.getX() + cursorSize < Grid.WIDTH) {

                        cursor.translate(Grid.CELLSIZE, 0);
                        if (pressed && painting) paint();
                        else if (pressed && erasing) erase();
                    }
                    break;
                case KeyboardEvent.KEY_SPACE:
                    System.out.println("pressed " + isPressed());
                    System.out.println("paint " + isPainting());
                    System.out.println(isErasing());

                    setPressed(true);
                    tile = grid.getGrid()[((cursor.getY()) / Grid.CELLSIZE)][((cursor.getX()) / Grid.CELLSIZE)];
                    if (tile.isPainted()) {
                        setErasing(true);
                    } else {
                        setPainting(true);
                    }
                    for (int i = 0; i < cursorSize; i += Grid.CELLSIZE) {
                        for (int j = 0; j < cursorSize; j += Grid.CELLSIZE) {
                            tile = grid.getGrid()[((cursor.getY() + i) / Grid.CELLSIZE)][((cursor.getX() + j) / Grid.CELLSIZE)];

                            if (tile.isPainted()) {
                                setErasing(true);
                                erase();
                            } else {
                                setPainting(true);
                                paint();
                            }
                        }
                    }


                    break;
                case KeyboardEvent.KEY_S:
                    try {
                        saveFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case KeyboardEvent.KEY_L:
                    try {
                        loadFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case KeyboardEvent.KEY_C:
                    clearScreen();
                    break;


            }
        }


        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

            setPressed(false);
            setPainting(false);
            setErasing(false);


        }


    }

}
