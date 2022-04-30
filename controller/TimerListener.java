package controller;

import java.awt.event.ActionListener;
import java.util.LinkedList;

import model.Bullet;
import model.Shooter;
import model.decoratorPattern.BoombBullet;
import model.decoratorPattern.WaveBullet;
import model.stratagyPattern.NormalSpeed;
import view.GameView;

import java.awt.event.ActionEvent;

public class TimerListener implements ActionListener {

    public enum EventType {
        KEY_RIGHT, KEY_LEFT, KEY_SPACE, KEY_L, KEY_W
    }

    private LinkedList<EventType> eventQueue;
    public static int laserLimit = 0;
    public static int waveLimit = 0;
    private GameView gameBoard;
    private final int BOMB_DROP_FREQ = 20;
    private int frameCounter = 0;

    private int stratagyChangge = 0;

    public TimerListener(GameView gameBoard) {
        this.gameBoard = gameBoard;
        eventQueue = new LinkedList<>();
        laserLimit = 0;
        waveLimit = 0;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ++frameCounter;
        update();
        processEventQueue();
        processCollision();
        gameBoard.getCanvas().repaint();
    }

    private void processEventQueue() {
        while (!eventQueue.isEmpty()) {
            var e = eventQueue.getFirst();
            eventQueue.removeFirst();
            Shooter shooter = gameBoard.getShooter();
            if (shooter == null)
                return;

            switch (e) {
                case KEY_LEFT:
                    shooter.moveLeft();
                    break;
                case KEY_RIGHT:
                    shooter.moveRight();
                    break;
                case KEY_SPACE:
                    if (shooter.canFireMoreBullet())
                        shooter.getWeapons().add(new Bullet(shooter.x, shooter.y));
                    break;
                case KEY_L:
                    if (laserLimit < 3) {
                        shooter.getWeapons().add(
                                new BoombBullet(new Bullet(shooter.x, shooter.y), gameBoard.getShooter().getWeapons()));
                        laserLimit++;
                    }
                    break;

                case KEY_W:
                    if (waveLimit < 1) {
                        shooter.getWeapons().add(
                                new WaveBullet(new Bullet(shooter.x, shooter.y), gameBoard.getShooter().getWeapons()));
                        waveLimit++;
                    }
                    break;
            }
        }

        if (frameCounter == BOMB_DROP_FREQ) {
            gameBoard.getEnemyComposite().dropBombs();
            frameCounter = 0;
        }

        try {
            if (Class.forName("model.stratagyPattern.SlowSpeed")
                    .isInstance(gameBoard.getEnemyComposite().getEnemyStratagy())) {
                stratagyChangge++;

                if (stratagyChangge > 250) {
                    gameBoard.getEnemyComposite().setEnemyStratagy(new NormalSpeed(gameBoard.getEnemyComposite().getRows()));
                    stratagyChangge = 0;
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void processCollision() {
        var shooter = gameBoard.getShooter();
        var enemyComposite = gameBoard.getEnemyComposite();

        shooter.removeBulletsOutOfBound();
        enemyComposite.checkShoterHit(shooter);
        enemyComposite.removeBombsOutOfBound();
        enemyComposite.processCollision(shooter);
    }

    private void update() {
        for (var e : gameBoard.getCanvas().getGameElements()) {
            e.animate();
        }

    }

    public LinkedList<EventType> getEventQueue() {
        return eventQueue;
    }
}
