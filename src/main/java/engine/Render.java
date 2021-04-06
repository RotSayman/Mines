package engine;

import input.Input;
import model.Cell;
import model.MapCell;
import org.lwjgl.opengl.GL;

import javax.swing.*;
import java.util.Date;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46C.*;

public class Render
{

    private static Cell[][] map;
    private static final int MINES = 10;
    private static int closedCell;
    private static boolean failed = false;
    private static final Random random = new Random();

    public static void init(long window){
        Input.initInput(window);
        newGame();
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public static void loop(long window){
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            if(MINES == closedCell){
                JOptionPane.showMessageDialog(null, "Победа");
                newGame();
            }

            // Input
            inputGame();

            // Draw
            MapCell.showMap();



            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private static void newGame(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closedCell = MapCell.MAP_HEIGHT * MapCell.MAP_WIDTH;
        map = MapCell.getMapCell();
        failed = false;
        random.setSeed(new Date().getTime());
        for (int i = 0; i < MINES; i++) {
            int x = (int) (random.nextFloat() * 10) % MapCell.MAP_WIDTH;
            int y = (int) (random.nextFloat() * 10) % MapCell.MAP_HEIGHT;
            if (map[x][y].isMine()) i--;
            else{
                map[x][y].setMine(true);

                for (int dx = -1; dx < 2; dx++) {
                    for (int dy = -1; dy < 2; dy++) {
                        if(isCellIsMap(x + dx, y + dy)){
                            map[x + dx][y+dy].setCntAround();
                        }
                    }
                }
            }
        }
    }

    private static boolean isCellIsMap(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < MapCell.MAP_WIDTH) && (y < MapCell.MAP_HEIGHT);
    }

    private static void inputGame(){
        if(Input.isLeftButtonPressed()){

            if(failed){
                newGame();
            }else{
                int x = (int)((Input.getCurrentPos().x * 2) / 100);
                int y = (int)(MapCell.MAP_HEIGHT - (Input.getCurrentPos().y * 2) / 100);
                if(isCellIsMap(x, y) && !map[x][y].isFlag())
                    openFields(x, y);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }else if(Input.isRightButtonPressed()){
            int x = (int)((Input.getCurrentPos().x * 2) / 100);
            int y = (int)(MapCell.MAP_HEIGHT - (Input.getCurrentPos().y * 2) / 100);
            map[x][y].setFlag(!map[x][y].isFlag());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void openFields(int x, int y){
        if(!isCellIsMap(x, y) || map[x][y].isOpen()) return;
        map[x][y].setOpen(true);
        closedCell--;
        if(map[x][y].getCntAround() == 0){
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    openFields(x + i, y + j);
                }
            }
        }
        if(map[x][y].isMine()){
            failed = true;
            for (int i = 0; i < MapCell.MAP_HEIGHT; i++) {
                for (int j = 0; j < MapCell.MAP_WIDTH; j++) {
                    map[i][j].setOpen(true);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
