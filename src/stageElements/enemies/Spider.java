package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Spider extends Enemy {
    Config config;

    public Spider(int row, int col) {
        super("spr", row, col, false, SpriteSheet.getSprite(4));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Spider", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Spider", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Spider", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Spider", "damageVariation"));
    }
}