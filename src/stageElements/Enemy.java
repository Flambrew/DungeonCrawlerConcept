package src.stageElements;

import java.awt.image.BufferedImage;

public class Enemy extends StageElement {
    public Enemy(String name, int row, int col, boolean isFlying,
            BufferedImage sprite) {
        super(name, row, col, isFlying, sprite);
    }
}