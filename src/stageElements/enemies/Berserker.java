package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Berserker extends Enemy {
    Config config;

    public Berserker(int row, int col) {
        super("ber", row, col, false, SpriteSheet.getSprite(8));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Berserker", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Berserker", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Berserker", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Berserker", "damageVariation"));
    }
}