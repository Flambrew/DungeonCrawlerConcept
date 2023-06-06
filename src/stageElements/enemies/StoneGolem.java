package src.stageElements.enemies;

import src.gameComponents.Config;
import src.stageElements.Enemy;
import src.uiElements.SpriteSheet;

public class StoneGolem extends Enemy {
    Config config;

    public StoneGolem(int row, int col) {
        super("glm", row, col, false, SpriteSheet.getSprite(9));
        config = new Config("config\\config.json");
        this.setHealth(config.getValue("Enemy", "StoneGolem", "health")
                + ((int) (Math.random() * (config.getValue("Enemy", "StoneGolem", "healthVariation") + 1))));
        this.setAttackDamage(config.getValue("Enemy", "StoneGolem", "damage"));
        this.setDamageVariation(config.getValue("Enemy", "StoneGolem", "damageVariation"));
    }
}