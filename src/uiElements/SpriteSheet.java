package src.uiElements;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;

public class SpriteSheet {
    public static BufferedImage getSprite(int index) {
        final int SPRITERESOLUTION = 48;
        BufferedImage spriteSheet;
        while (true) {
            try {
                spriteSheet = ImageIO.read(new File("assets\\sprites.png"));
                break;
            } catch (IOException e) {
                System.out.println("Sprites could not be found");
            }
        }
        return spriteSheet.getSubimage((index % (spriteSheet.getWidth() / SPRITERESOLUTION)) * SPRITERESOLUTION,
                (index / (spriteSheet.getWidth() / SPRITERESOLUTION)) * SPRITERESOLUTION,
                SPRITERESOLUTION, SPRITERESOLUTION);
    }
}