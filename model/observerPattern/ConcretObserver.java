package model.observerPattern;

import java.awt.Color;
import java.util.ArrayList;

import model.GameElement;
import view.GameView;
import view.TextDraw;

public class ConcretObserver implements Observer {

    GameView gb;
    private String BodderCrossed = "Game Over Enemy Touch down";
    private String sooterDown = "Game Over Shotter Down";
    private String youLose = "You Lose";
    private String yourScore = "Your score : ";
    ArrayList<GameElement> gElement ;
    public ConcretObserver(GameView gb) {
        this.gb = gb;
        gElement = this.gb.getCanvas().getGameElements();
    }

    @Override
    public void score() {
       gb.setScore(gb.getScore()+10);
    }


    @Override
    public void shooterDown() {
        stopGame();
        gElement.add(new TextDraw(sooterDown, 80, 250, Color.RED, 30));
        gElement.add(new TextDraw(youLose, 210, 300, Color.yellow, 25));
        gElement.add(new TextDraw(yourScore.concat(""+gb.getScore()), 180, 350, Color.GREEN, 25));
        repaint();
    }


    @Override
    public void enemyTouchDown() {
        stopGame();
        gElement.add(new TextDraw(BodderCrossed, 100, 250, Color.RED, 30));
        gElement.add(new TextDraw( youLose, 230, 300, Color.RED, 25));
        gElement.add(new TextDraw(yourScore.concat(""+gb.getScore()), 180, 350, Color.GREEN, 25));
        repaint();
    }


    @Override
    public void BattelWin() {
        stopGame();
        gElement.add(new TextDraw("Game Over", 180, 250, Color.green, 30));
        gElement.add(new TextDraw("You Win", 210 ,300, Color.yellow, 25));
        gElement.add(new TextDraw("Your score : \""+gb.getScore()+"\"", 160, 350, Color.WHITE, 25));
        repaint();
    }


    private void repaint(){
     gb.getCanvas().repaint();
    }


    private void stopGame(){
        gb.getTimer().stop();
        gb.getCanvas().getGameElements().clear();
    }
    
    
}
