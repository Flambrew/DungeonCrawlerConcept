package src.gameComponents;

import src.uiElements.Renderer;

/**
 * Main.java
 * This class contains the main loop
 * @author Andrew Matherne
 */
public class Main {
    public static void main(String[] args) {
        // TEST CODE:

        // GAME CODE
        Stage stage = new Stage();
        Renderer screen = new Renderer(stage);
        screen.setup();
        stage.createPlayer();
        stage.placeEnemies(0);
        System.out.println(stage);
        screen.repaint();
        sleep(500);

        // GAMELOOP
        while (true) {
            while (true) {
                int playerAction;
                boolean moveWasMade = false;
                while (true) {
                    playerAction = screen.getActionCode();
                    sleep(1);
                    if (playerAction != 7) {
                        break;
                    }
                }
                if (playerAction < 4) {
                    moveWasMade = stage.movePlayer(playerAction);
                } else if (playerAction < 5) {

                } else if (playerAction < 6) {

                } else {

                }

                if (moveWasMade) {
                    break;
                }

            }
            stage.enemyCycle();
            screen.repaint();

            if (stage.getStageContents(stage.getPlayerRow(), stage.getPlayerCol()).getHealth() <= 0) {
                break;
            }
            sleep(249);
        }

        // TEST CODE:
        while (true) {
            System.out.println(screen.getActionCode());
            sleep(100);
        }
    }

    /**
     * Method used to halt the main loop for s milliseconds
     * @param s Milliseconds to halt
     * @author Andrew Matherne
     */
    public static void sleep(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
        }
    }
}