package model.decoratorPattern;

import java.awt.Graphics2D;

import model.GameElement;

public abstract class BoombDecorator extends GameElement {

    GameElement gameElement;

    public BoombDecorator(GameElement gameElement) {
        this.gameElement = gameElement;
    }

    public void render(Graphics2D g2) {
        gameElement.render(g2);
    }

    public void animate() {
        gameElement.animate();
    }
}
