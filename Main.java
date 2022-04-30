
import javax.swing.JFrame;

import view.GameView;

public class Main{

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Space Invader");
        window.setLocation(400, 50);
        var game = new GameView(window);
        game.init();
        window.setResizable(true);
        window.pack();
        window.setVisible(true);
    }
}