package model.stratagyPattern;

import java.awt.Color;
import java.util.ArrayList;
import model.EnemyComposite;
import model.GameElement;
import view.GameView;

public class NormalSpeed implements EnemyStratagy {
    private boolean movingToRight = true;
    ArrayList<ArrayList<GameElement>> rows;
    ArrayList<GameElement> normal = new ArrayList<GameElement>();

    public NormalSpeed(ArrayList<ArrayList<GameElement>> rows) {
        EnemyComposite.frequency = 0.1F;
        this.rows = rows;
        this.rows = rows;
        for (var row : rows) {
            for (var e : row) {
                e.color = Color.YELLOW;
            }
        }
    }

    @Override
    public void speedAlgorithm() {

        int dx = 5;
        if (movingToRight) {
            if (EnemyComposite.rightEnd(rows) >= GameView.WIDTH) {
                verticaldropDown();
                dx = -dx;
                movingToRight = false;
            }
        } else {
            dx = -dx;
            if (EnemyComposite.leftEnd(rows) <= 0) {
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
                e.y += 20;
                EnemyComposite.droped_to = e.y;
            }
        }

        if (EnemyComposite.droped_to >= GameView.HEIGHT - 20) {
            EnemyComposite.bottontouch = true;
        }

    }

}
