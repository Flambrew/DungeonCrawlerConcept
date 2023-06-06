package src.uiElements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

import src.gameComponents.Stage;

public class GameUI {
    public static BufferedImage getUI(Stage stage) {
        BufferedImage ui;
        while (true) {
            try {
                ui = ImageIO.read(new File("assets\\ui.png"));
                break;
            } catch (IOException e) {
                System.out.println("UI could not be found");
            }
        }
        return ui.getSubimage((stage.getPlayerRow() % 6) * 528, 0, 528, 960);
    }
}