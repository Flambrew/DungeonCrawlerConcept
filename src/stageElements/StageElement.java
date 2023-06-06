package src.stageElements;

import java.awt.image.BufferedImage;

public class StageElement {
    private String name;
    private int row;
    private int col;
    private boolean isFlying;
    private int health;
    private int attackDamage;
    private int damageVariation;
    BufferedImage sprite;

    // Basic StageElement
    public StageElement() {
        this.name = "   ";
        this.row = 0;
        this.col = 0;
        this.isFlying = false;
        this.health = 0;
        this.attackDamage = 0;
        this.damageVariation = 0;
        this.sprite = null;
    }

    // Player StageElement
    public StageElement(int row, int col, BufferedImage sprite) {
        this.name = "plr";
        this.row = row;
        this.col = col;
        this.isFlying = false;
        this.sprite = sprite;
    }

    // Enemy StageElement
    public StageElement(String name, int row, int col, boolean isFlying, BufferedImage sprite) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.isFlying = isFlying;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getLocation(int index) {
        if (index == 0) {
            return row;
        } else if (index == 1) {
            return col;
        } else {
            System.out.println("BAD CALL TO GETLOCATION");
            return 0;
        }
    }

    public boolean isFlying() {
        return isFlying;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage + ((int)(Math.random() * (damageVariation + 1)));
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decrementHealth(int damageDealt) {
        this.health -= damageDealt;
    }

    public void setDamageVariation(int damageVariation) {
        this.damageVariation = damageVariation;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
}