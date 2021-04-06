package model;

import static org.lwjgl.opengl.GL11.*;

public class MapCell
{
    public static final int MAP_WIDTH = 10;
    public static final int MAP_HEIGHT = 10;

    private static Cell[][] map;

    public static Cell[][] getMapCell(){
        map = new Cell[MAP_HEIGHT][MAP_WIDTH];
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                map[i][j] = new Cell();
            }
        }
        return map;
    }



    public static void showMap(){
        glLoadIdentity();
        glScalef(2f/ MapCell.MAP_WIDTH, 2f/ MapCell.MAP_HEIGHT, 1);
        glTranslatef(-MapCell.MAP_WIDTH * 0.5f, -MapCell.MAP_HEIGHT * 0.5f, 0);
        for (int i = 0; i < MAP_HEIGHT; i++) {
            for (int j = 0; j < MAP_WIDTH; j++) {
                glPushMatrix();
                glTranslatef(i, j, 0);
                if(map[i][j].isOpen()){
                    map[i][j].showFieldOpen();
                    if(map[i][j].isMine()) map[i][j].showMine();
                    else if(map[i][j].getCntAround() > 0)
                        map[i][j].showCount();

                }else {
                    map[i][j].showField();
                    if(map[i][j].isFlag()) map[i][j].showFlag();
                }
                glPopMatrix();
            }
        }
    }

}
