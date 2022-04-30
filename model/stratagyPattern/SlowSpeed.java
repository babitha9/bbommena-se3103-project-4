package model.stratagyPattern;

import java.awt.Color;
import java.util.ArrayList;

import model.Constants;
import model.Enemy;
import model.EnemyComposite;
import model.GameElement;
import view.GameView;

public class SlowSpeed implements EnemyStratagy {
    private boolean movingToRight = true;
    ArrayList<ArrayList<GameElement>> rows;

    public SlowSpeed(ArrayList<ArrayList<GameElement>> rows) {
        EnemyComposite.frequency = 0.5F;

        this.rows = rows;
        for (var row : rows) {
            for (var e : row) {
                e.color = Color.RED;
            }
        }
    }
   public SlowSpeed(){

    }
    @Override
    public void speedAlgorithm() {

        int dx = 2;
        if (movingToRight) {
            if (EnemyComposite.rightEnd(rows) >= GameView.WIDTH) {
                verticaldropDown();
                dx = -dx;
                movingToRight = false;
            }
        } else {
            dx = -dx;
            if ( EnemyComposite.leftEnd(rows) <= 0) {
                verticaldropDown();
                dx = -dx;
                movingToRight = true;
            }
        }

        for (var row : rows) {
            for (var e : row) {
                e.x += dx;
            }
        }
    }

    private void verticaldropDown() {
        for (var row : rows) {
            for (var e : row) {
                e.y += 10;
           if(EnemyComposite.droped_to<e.y)
                EnemyComposite.droped_to = e.y;
            }
        }

        if(EnemyComposite.droped_to >= GameView.HEIGHT-20){
            EnemyComposite.bottontouch = true;
        }
    }

}
