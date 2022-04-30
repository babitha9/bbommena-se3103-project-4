package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.GameView;
import view.GameCanvas;
import model.Constants;
import model.EnemyComposite;
import model.Shooter;
import model.ShooterElement;


public class ButtonListionr implements ActionListener { 
    GameView gb;
    public ButtonListionr(GameView gb){
        this.gb = gb;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonString = ((JButton) e.getSource()).getText();

        if(buttonString.equals("Start")){
            GameCanvas canvas = gb.getCanvas();
            gb.setEnemyui();
            TimerListener.laserLimit = 0;
            TimerListener.waveLimit = 0;
            gb.getTimerListener().getEventQueue().clear();
            canvas.getGameElements().clear();
            gb.setShooter(new Shooter(GameView.WIDTH/2,GameView.HEIGHT-Constants.SIZE));
            canvas.getGameElements().add(gb.getShooter());
            canvas.getGameElements().add(gb.getEnemyComposite());   
            gb.getScoreView().setText("0");
            EnemyComposite.droped_to =0;
            gb.getTimer().start();
            gb.setScore(0);
} else{
    System.exit(0);
} 

    }


}
