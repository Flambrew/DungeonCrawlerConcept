package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Slime extends Enemy {
    Config config;

    public Slime(int row, int col) {
        super("slm", row, col, false, SpriteSheet.getSprite(1));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Slime", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Slime", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Slime", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Slime", "damageVariation"));
    }
}