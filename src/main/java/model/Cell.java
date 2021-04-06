package model;


import static org.lwjgl.opengl.GL11.*;

public class Cell
{
    private boolean mine;
    private boolean flag;
    private boolean open;
    private int cntAround;

    public Cell() {
        this.mine = false;
        this.flag = false;
        this.open = false;
        cntAround = 0;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getCntAround() {
        return cntAround;
    }

    public void setCntAround() {
        this.cntAround++;
    }

    public void showMine(){
        glBegin(GL_TRIANGLE_FAN);
        glColor3f(0, 0, 0);
        glVertex2f(0.3f, 0.3f);
        glVertex2f(0.7f, 0.3f);
        glVertex2f(0.7f, 0.7f);
        glVertex2f(0.3f, 0.7f);
        glEnd();
    }

    public void showFlag(){
        glBegin(GL_TRIANGLES);
        glColor3f(1, 0, 0);
        glVertex2f(0.25f, 0.75f);
        glVertex2f(0.85f, 0.5f);
        glVertex2f(0.25f, 0.25f);
        glEnd();
        glLineWidth(5);
        glBegin(GL_LINES);
        glColor3f(0,0,0);
        glVertex2f(0.25f, 0.75f);
        glVertex2f(0.25f, 0);
        glEnd();
    }

    public void showField(){
        glBegin(GL_TRIANGLE_STRIP);
        glColor3f(0.8f, 0.8f, 0.8f); glVertex2f(0f, 1f);
        glColor3f(0.7f, 0.7f, 0.7f); glVertex2f(1f, 1f); glVertex2f(0f, 0f);
        glColor3f(0.6f, 0.6f, 0.6f); glVertex2f(1f, 0f);
        glEnd();
    }

    public void showFieldOpen(){
        glBegin(GL_TRIANGLE_STRIP);
        glColor3f(0.3f, 0.7f, 0.3f); glVertex2f(0f, 1f);
        glColor3f(0.3f, 0.6f, 0.3f); glVertex2f(1f, 1f); glVertex2f(0f, 0f);
        glColor3f(0.3f, 0.5f, 0.3f); glVertex2f(1f, 0f);
        glEnd();
    }

    public void showCount(){
        int a = cntAround;

        glLineWidth(3);
        glColor3f(1, 1, 0);
        glBegin(GL_LINES);
            if((a != 1) && (a != 4)) line(0.3f, 0.85f, 0.7f, 0.85f);
            if((a != 0) && (a != 1) && (a != 7)) line(0.3f, 0.5f, 0.7f, 0.5f);
            if((a != 1) && (a != 4) && (a != 7)) line(0.3f, 0.15f, 0.7f, 0.15f);

            if((a != 5) && (a != 6)) line(0.7f, 0.5f, 0.7f, 0.85f);
            if((a != 2)) line(0.7f, 0.5f, 0.7f, 0.15f);

            if((a != 1) && (a != 2) && (a != 3) && (a != 7)) line(0.3f, 0.5f, 0.3f, 0.85f);
            if((a == 0) || (a == 2) || (a == 6) || (a == 8)) line(0.3f, 0.5f, 0.3f, 0.15f);
        glEnd();

    }



    private void line(float x1, float y1, float x2, float y2){
        glVertex2f(x1, y1);
        glVertex2f(x2, y2);
    }
}
