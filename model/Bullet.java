package model;

import java.awt.Graphics2D;
import java.awt.Color;

public class Bullet extends GameElement {

    public Bullet(int x,int y){
        super(x,y,Color.red,true,Constants.ELEMENT_SIZE,Constants.ELEMENT_SIZE+3);
    }
    public Bullet(int x,int y,int width,int height ){
        super(x,y,Color.green,true,width,height);
    }

    @Override
    public void render(Graphics2D g2) {
         g2.setColor(color);
         if(filled)
          g2.fillRect(x, y, width, height);
         else
          g2.drawRect(x, y, width, height);
        
    }
    @Override
    public void animate() {
        super.y -= Constants.UNIT_MOVE;
        
    }
    
    
}
