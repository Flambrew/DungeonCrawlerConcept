package src.stageElements;

import java.awt.image.BufferedImage;

import src.gameComponents.Config;

public class Player extends StageElement {
    Config config;

    public Player(int row, int col, BufferedImage sprite) {
        super(row, col, sprite);
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Player", "health"));
        this.setAttackDamage(config.getValue("Player", "damage"));
        this.setDamageVariation(config.getValue("Player", "damageVariation"));
    }
}