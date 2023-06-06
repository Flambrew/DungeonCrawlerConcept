package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Goblin extends Enemy {
    Config config;

    public Goblin(int row, int col) {
        super("gbl", row, col, false, SpriteSheet.getSprite(7));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Goblin", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Goblin", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Goblin", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Goblin", "damageVariation"));
    }
}