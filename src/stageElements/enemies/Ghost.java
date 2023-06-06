package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Ghost extends Enemy {
    Config config;

    public Ghost(int row, int col) {
        super("gst", row, col, true, SpriteSheet.getSprite(2));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Ghost", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Ghost", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Ghost", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Ghost", "damageVariation"));
    }
}