package src.uiElements;

import javax.swing.JFrame;

import src.gameComponents.InputListener;
import src.gameComponents.Stage;

import javax.swing.JComponent;

import java.awt.Container;
import java.awt.Graphics;

public class Renderer extends JComponent {
    private Stage stage;
    private JFrame frame;
    private Container content;
    private final int VISIBLEWIDTH;
    private final int VISIBLEHEIGHT;

    public Renderer(Stage stage) {
        this.stage = stage;
        this.frame = new JFrame();
        this.content = frame.getContentPane();
        frame.addKeyListener(new InputListener());
        VISIBLEHEIGHT = 16;
        VISIBLEWIDTH = 9;
    }

    public int getActionCode() { // up 38 right 39 down 40 left 37
        int actionCode = 7;
        switch (InputListener.getActionCode()) {
            case 38:
            actionCode = 0;
            break;
            case 39:
            actionCode = 1;
            break;
            case 40:
            actionCode = 2;
            break;
            case 37:
            actionCode = 3;
            break;
        }
        return actionCode;
    }

    public void setup() {
        content.add(this);
        frame.setSize(544, 999);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(GameUI.getUI(stage), 0, 0, null);
        for (int i = 0; i < VISIBLEHEIGHT; i++) {
            for (int k = 0; k < VISIBLEWIDTH; k++) {
                try {
                    g.drawImage(stage.getStageContents(stage.getPlayerRow() + i - 3, k).getSprite(), 48 * (k + 1),
                            48 * (VISIBLEHEIGHT - i), null);
                } catch (Exception e) {
                }
            }
        }
    }
}