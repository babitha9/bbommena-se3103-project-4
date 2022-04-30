package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Graphics;

import javax.swing.JPanel;
import model.GameElement;

public class GameCanvas extends JPanel{

    private GameView gameBoard;
    private ArrayList<GameElement> gameElements = new ArrayList<>();

    public GameCanvas(GameView gameBoard,int width,int height){
        this.gameBoard=gameBoard;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width,height));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        gameRender(g2);
    }

    private void gameRender(Graphics2D g2){
        for (int i = 0; i < gameElements.size(); i++) {
            gameElements.get(i).render(g2);
        }
    }
    
    public ArrayList<GameElement> getGameElements(){
        return gameElements;
    }

    
}

