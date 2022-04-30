package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GameView;

public class KeyController implements KeyListener {

    private GameView gameBoard;

    public KeyController(GameView gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        var eventQueue = gameBoard.getTimerListener().getEventQueue();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                eventQueue.add(TimerListener.EventType.KEY_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                eventQueue.add(TimerListener.EventType.KEY_RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                eventQueue.add(TimerListener.EventType.KEY_SPACE);
                break;
            case KeyEvent.VK_L:
                eventQueue.add(TimerListener.EventType.KEY_L);
                break;

                case KeyEvent.VK_W:
                eventQueue.add(TimerListener.EventType.KEY_W);
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
