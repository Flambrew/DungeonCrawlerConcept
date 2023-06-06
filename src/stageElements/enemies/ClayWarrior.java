package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class ClayWarrior extends Enemy {
    Config config;

    public ClayWarrior(int row, int col) {
        super("csr", row, col, false, SpriteSheet.getSprite(5));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "ClayWarrior", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "ClayWarrior", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "ClayWarrior", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "ClayWarrior", "damageVariation"));
    }
}