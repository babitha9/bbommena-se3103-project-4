package model.decoratorPattern;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.Bullet;
import model.GameElement;

public class WaveBullet extends BoombDecorator {

    public WaveBullet(GameElement gameElement, ArrayList<GameElement> weapons) {
        super(gameElement);
        LongBullets(weapons);
    }

    public WaveBullet(GameElement gameElement) {
        super(gameElement);
    }

    public WaveBullet() {
        super(null);
    }

    private void LongBullets(ArrayList<GameElement> weapons) {
        weapons.add(new Bullet(0, gameElement.y, 600, 5));
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
