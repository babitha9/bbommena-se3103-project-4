package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import model.observerPattern.Observer;
import java.util.Random;

import model.observerPattern.Subject;
import model.stratagyPattern.EnemyStratagy;
import model.stratagyPattern.NormalSpeed;
import model.stratagyPattern.SlowSpeed;
import view.GameView;
import view.TextDraw;

public class EnemyComposite extends GameElement implements Subject {
    private boolean movingToRight = true;
    public static final int NROWS = 2;
    public static final int NCOLS = 10;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private EnemyStratagy enemyStratagy;

    // public static int UNIT_MOVE=5;
    public static float frequency = 0.2f;
    private ArrayList<ArrayList<GameElement>> rows;
    private ArrayList<GameElement> bombs;

    private Random random = new Random();
    public static Boolean bottontouch = false;

    public static int droped_to = 0;
    GameView gb;

    public enum Event {
        EnemyFinish, EnemyTouchDown, SooterHit, IncreaseScore
    }

    public EnemyComposite(GameView gb) {
        this.gb = gb;
        rows = new ArrayList<>();
        bombs = new ArrayList<>();
        droped_to = 0;
         enemyStratagy = new NormalSpeed(rows);
        // enemyStratagy = new SlowSpeed(rows);
        bottontouch = false;
        for (int r = 0; r < NROWS; r++) {
            var oneRow = new ArrayList<GameElement>();
            rows.add(oneRow);
            for (int c = 0; c < NCOLS; c++) {
                oneRow.add(new Enemy(
                        c * Constants.ENEMY_SIZE * 2, r * Constants.ENEMY_SIZE * 2, Constants.ENEMY_SIZE, Color.yellow,
                        true));

            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        for (var r : rows) {
            for (var e : r) {
                e.render(g2);
            }
        }
        for (var b : bombs) {
            b.render(g2);
        }
    }

    public void setEnemyStratagy(EnemyStratagy enemyStratagy) {
        this.enemyStratagy = enemyStratagy;
    }

    public EnemyStratagy getEnemyStratagy() {
        return enemyStratagy;
    }

    @Override
    public void animate() {

        enemyStratagy.speedAlgorithm();

        for (var b : bombs) {
            b.animate();
        }

    }

    public static int rightEnd(ArrayList<ArrayList<GameElement>> rows) {
        int xEnd = -100;
        for (var row : rows) {
            if (row.size() == 0)
                continue;
            int x = row.get(row.size() - 1).x + Constants.ENEMY_SIZE;
            if (x > xEnd)
                xEnd = x;
        }
        return xEnd;
    }

    public static int leftEnd(ArrayList<ArrayList<GameElement>> rows) {
        int xEnd = 9000;
        for (var row : rows) {
            if (row.size() == 0)
                continue;
            int x = row.get(0).x;
            if (x < xEnd)
                xEnd = x;
        }
        return xEnd;
    }

    public void checkShoterHit(Shooter shooter) {
        var remove = new ArrayList<GameElement>();
        for (var b : bombs) {
            int index = shooter.hitIndex(b);
            if (index == -2) {
                notifyListners(Event.SooterHit);
            } else {
                if (index >= 0) {
                    remove.add(b);
                    shooter.remove(index);
                }
            }

        }
        bombs.removeAll(remove);
    }

    public void dropBombs() {
        for (var row : rows) {
            for (var e : row) {
                if (random.nextFloat() < frequency) {
                    bombs.add(new Bomb(e.x, e.y));
                }
            }
        }

    }

    public void removeBombsOutOfBound() {
        var remove = new ArrayList<GameElement>();
        for (var b : bombs) {
            if (b.y >= GameView.HEIGHT) {
                remove.add(b);
            }

        }
        bombs.removeAll(remove);
    }

    public void processCollision(Shooter shooter) {
        var removeBullets = new ArrayList<GameElement>();

        for (var row : rows) {
            var removeEnemies = new ArrayList<GameElement>();
            for (var enemy : row) {
                for (var bullet : shooter.getWeapons()) {

                    if (enemy.collideWith(bullet)) {
                        if (bullet.width > 100) {
                           setEnemyStratagy(new SlowSpeed(rows));
                           continue;
                        }
                        removeBullets.add(bullet);
                        removeEnemies.add(enemy);
                        // gb.setScore(gb.getScore()+10);
                        notifyListners(Event.IncreaseScore);
                    }
                }
            }
            row.removeAll(removeEnemies);

        }

        boolean hasEnemys = false;
        for (var row : rows) {
            for (var enemy : row) {
                hasEnemys = true;
            }
        }

        if (!hasEnemys) {
            notifyListners(Event.EnemyFinish);
        }
        shooter.getWeapons().removeAll(removeBullets);

        if (bottontouch) {
            notifyListners(Event.EnemyTouchDown);
        }

        var removeBombs = new ArrayList<GameElement>();
        removeBullets.clear();

        for (var b : bombs) {   
            for (var bullet : shooter.getWeapons()) {

                if (bullet.height > 10) {
                    System.out.println("true");
                    continue;
                }

                if (b.collideWith(bullet)) {
                    removeBombs.add(b);
                    if (bullet.width > 10) {
                        System.out.println("true");
                        continue;
                    }
                    removeBullets.add(bullet);
                }
            }
        }

        shooter.getWeapons().removeAll(removeBullets);
        bombs.removeAll(removeBombs);
    }

public ArrayList<ArrayList<GameElement>> getRows() {
    return rows;
}

    @Override
    public void addListinor(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeListnor(Observer o) {
        observers.remove(o);

    }

    @Override
    public void notifyListners(Event event) {

        switch (event) {

            case IncreaseScore:
                for (var o : observers) {
                    o.score();
                }
                break;

            case EnemyFinish:

                for (var o : observers) {
                    o.BattelWin();
                }
                break;

            case SooterHit:
                for (var o : observers) {
                    o.shooterDown();
                }
                break;

            case EnemyTouchDown:
                for (var o : observers) {
                    o.enemyTouchDown();
                }
                break;
        }
    }

}
