package model.decoratorPattern;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.Bullet;
import model.GameElement;

public class BoombBullet extends BoombDecorator {


    public BoombBullet(GameElement gameElement, ArrayList<GameElement> weapons) {
        super(gameElement);
        LongBullets(weapons);
    }

    public BoombBullet(GameElement gameElement){
        super(gameElement);
    }

    public BoombBullet(){
        super(null);
    }
    private void LongBullets(ArrayList<GameElement> weapons) {
        weapons.add(new Bullet(gameElement.x - 60, gameElement.y, 10, 40));
        weapons.add(new Bullet(gameElement.x , gameElement.y, 10, 40));
        weapons.add(new Bullet(gameElement.x + 60, gameElement.y, 10, 40));
    }

    @Override
    public void render(Graphics2D g2) {
        super.render(g2);

    }

    @Override
    public void animate() {
        super.animate();
    }

}
