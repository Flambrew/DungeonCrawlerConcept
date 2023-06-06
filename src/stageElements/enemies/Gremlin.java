package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Gremlin extends Enemy {
    Config config;

    public Gremlin(int row, int col) {
        super("grm", row, col, false, SpriteSheet.getSprite(3));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Gremlin", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Gremlin", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Gremlin", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Gremlin", "damageVariation"));
    }
}