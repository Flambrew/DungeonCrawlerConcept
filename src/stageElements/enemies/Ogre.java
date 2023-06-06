package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class Ogre extends Enemy {
    Config config;

    public Ogre(int row, int col) {
        super("ogr", row, col, false, SpriteSheet.getSprite(10));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "Ogre", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "Ogre", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "Ogre", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "Ogre", "damageVariation"));
    }
}