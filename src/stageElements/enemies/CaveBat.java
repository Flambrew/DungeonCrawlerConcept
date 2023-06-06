package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class CaveBat extends Enemy {
    Config config;

    public CaveBat(int row, int col) {
        super("bat", row, col, false, SpriteSheet.getSprite(0));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "CaveBat", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "CaveBat", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "CaveBat", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "CaveBat", "damageVariation"));
    }
}