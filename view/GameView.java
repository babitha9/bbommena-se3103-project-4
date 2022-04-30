package view;

import java.awt.Container;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.ShooterElement;
import model.observerPattern.ConcretObserver;
import controller.KeyController;
import controller.TimerListener;
import model.Constants;
import model.EnemyComposite;
import model.Shooter;

import controller.ButtonListionr;

public class GameView {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

    public static final int FPS = 20;
    public static final int DELAY = 1000 / FPS;
    private final String startGame = "click <start> to Play";
    private JFrame window;
    JButton startButton = new JButton("Start");
    JButton quitButton = new JButton("Quit");
    private GameCanvas canvas;
    private Shooter shooter;
    private EnemyComposite enemyComposite;
    private Timer timer;
    private TimerListener timerListener;
    private int score = 0;
    private JLabel scoreView = new JLabel("0");

    public GameView(JFrame window) {
        this.window = window;
    }

    public void init() {

        Container cp = window.getContentPane();
        canvas = new GameCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);
        canvas.addKeyListener(new KeyController(this));
        canvas.requestFocusInWindow();
        canvas.setFocusable(true);

        cp.add(BorderLayout.SOUTH, setSouthPannel(cp));

        JPanel northPannel = new JPanel();
        JLabel scoreLable = new JLabel("Score");
        northPannel.add(scoreLable);
        northPannel.add(scoreView);
        cp.add(BorderLayout.NORTH, northPannel);

        scoreLable.setFocusable(false);
        scoreView.setFocusable(false);

        canvas.getGameElements().add(new TextDraw(startGame, 100, 100, Color.yellow, 30));
        shooter = new Shooter(GameView.WIDTH / 2, GameView.HEIGHT - Constants.SIZE);
        // shooter = new ThreeStageShooter(GameBoard.WIDTH/2, GameBoard.HEIGHT -
        // ShooterElement.SIZE);

        timerListener = new TimerListener(this);
        timer = new Timer(DELAY, timerListener);

        ButtonListionr buttonlist = new ButtonListionr(this);

        startButton.addActionListener(buttonlist);
        quitButton.addActionListener(buttonlist);

    }

    private JPanel setSouthPannel(Container cp) {
        JPanel southPanel = new JPanel();

        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        startButton.setFocusable(false);
        quitButton.setFocusable(false);
        southPanel.add(startButton);
        southPanel.add(quitButton);
        return southPanel;

    }

    public void setEnemyui() {
        enemyComposite = new EnemyComposite(this);
        ConcretObserver actionObserver = new ConcretObserver(this);
        enemyComposite.addListinor(actionObserver);
    }

    public void setScore(int score) {
        this.score = score;
        scoreView.setText("" + score);
    }

    public int getScore() {
        return score;
    }

    public JLabel getScoreView() {
        return scoreView;
    }

    public void setEnemyComposite(EnemyComposite enemyComposite) {
        this.enemyComposite = enemyComposite;
    }

    public GameCanvas getCanvas() {
        return canvas;
    }

    public Timer getTimer() {
        return timer;
    }

    public TimerListener getTimerListener() {
        return timerListener;
    }

    public void setShooter(Shooter shooter) {
        this.shooter = shooter;
    }

    public Shooter getShooter() {
        return shooter;
    }

    public EnemyComposite getEnemyComposite() {
        return enemyComposite;
    }


}
