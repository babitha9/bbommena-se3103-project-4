package model;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;

public class Shooter extends GameElement {

   

    private ArrayList<GameElement> components = new ArrayList<>();
    private ArrayList<GameElement> weapons = new ArrayList<>();



    public Shooter(int x, int y){
          super(x,y,0,0);
          var size = Constants.SIZE;
          var s1= new ShooterElement(x - size,y -  size,Color.white,false);
          var s2= new ShooterElement(x,y - size,Color.white,false);
          var s3= new ShooterElement(x - size,y,Color.white,false);
          var s4= new ShooterElement(x,y,Color.white,false);
         components.add(s1);
          components.add(s2);
          components.add(s3);
          components.add(s4);
         
    }


    public void moveRight(){
        super.x += Constants.UNIT_MOVE;
        for (var c: components){
            c.x += Constants.UNIT_MOVE;
        }
    }

    public void moveLeft(){
        super.x -= Constants.UNIT_MOVE;
        for (var c: components){
            c.x -= Constants.UNIT_MOVE;
        }
    }

    public boolean canFireMoreBullet(){
        return weapons.size() <Constants. MAX_BULLETS;
    }

    public void removeBulletsOutOfBound(){
        var remove = new ArrayList<GameElement>();
        for (var w:weapons) {
            if(w.y < 0 || w.y ==0) {remove.add(w);}else{
                System.out.println("y = "+w.y);
            }
        }
        weapons.removeAll(remove);
    }
    
    public ArrayList<GameElement> getWeapons(){
        return weapons;
    }
     @Override
    public void render(Graphics2D g2){
       for(var c: components){
           c.render(g2);
       }
       for(var w: weapons){
        w.render(g2);
    }
    }
    
    @Override
    public void animate(){
        for(var w: weapons){
            w.animate();
        }

    }

    public void remove(int index){
        components.remove(index);
    }


    public int hitIndex(GameElement another){
        for(int i=0;i<components.size();i++){
            ShooterElement se = (ShooterElement)components.get(i);
            if((se.getBoundry().contains(another.x,another.y))){
               
                if(components.size()==1){
                    return -2;
                }else{
                    return i;
                }
                
            }
    
        }
        return -1;
    }

}
