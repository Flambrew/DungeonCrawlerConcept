package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Floater extends Enemy {
    Config config;

    public Floater(int row, int col) {
        super("flt", row, col, false, SpriteSheet.getSprite(6));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Floater", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Floater", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Floater", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Floater", "damageVariation"));
    }
}