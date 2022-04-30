package model;

import java.awt.Color;
import java.awt.Graphics2D;


public class Bomb extends GameElement {

   

    public Bomb(int x,int y){
        super(x,y,Color.green,true,Constants.ELEMENT_SIZE,Constants.ELEMENT_SIZE+2);
    }

    @Override
    public void animate(){
        super.y +=Constants.UNIT_MOVE_BOMB;
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        if(filled){
            g2.fillOval(x, y, width, height);
        }
        else{
            g2.drawOval(x, y, width, height);
        }
         
    }
}
