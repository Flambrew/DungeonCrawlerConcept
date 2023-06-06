package src.gameComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * InputListener.java
 * This class reads input from the keyboard and stores it in InputListener.actioncode
 * @author Andrew Matherne
 */
public class InputListener implements KeyListener {

    static int actionCode = 0;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        actionCode = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == actionCode) {
            actionCode = 0;
        }
    }

    public static int getActionCode() {
        return actionCode;
    }
}
