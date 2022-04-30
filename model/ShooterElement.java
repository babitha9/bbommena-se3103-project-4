package model;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;


public class ShooterElement extends GameElement{


  

    public ShooterElement(int x,int y,Color color,boolean filled){
        super(x,y,color,filled,Constants.SIZE,Constants.SIZE);
    }
    @Override
    public void render(Graphics2D g2){
        g2.setColor(color);
        if(super.filled){
            g2.fillRect(x,y,width,height);

        }else{
            g2.drawRect(x, y, width, height);
        }

    }
    
    @Override
    public void animate(){

    }

    public Rectangle getBoundry() {
        return new Rectangle(x,y,width,height);
    }
}
