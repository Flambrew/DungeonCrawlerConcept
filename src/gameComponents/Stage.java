package src.gameComponents;

import java.util.ArrayList;

import src.stageElements.*;
import src.stageElements.enemies.*;
import src.uiElements.SpriteSheet;

/**
 * Stage.java
 * This class stores StageElement positions, handles Player and Enemy movement and combat
 * @author Andrew Matherne
 */
public class Stage {
    Config input;
    private final int STAGEWIDTH = 9;
    private final int STAGEHEIGHT = 100;
    private StageElement[][] stageContents;
    private int playerRow;
    private int playerCol;
    private int baseEnemyCount;
    private int enemyCountScaler;
    private int percentChanceToMoveEnemies;
    private int recognitionDistance;

    /**
     * Constructor for Stage.java
     * Initializes enemy count, scaling, speed, and recognition distance
     * @author Andrew Matherne
     */
    public Stage() {
        this.stageContents = new StageElement[STAGEHEIGHT][STAGEWIDTH];

        input = new Config("config\\config.json");

        baseEnemyCount = Math.abs(input.getValue("Stage", "baseEnemyCount"));
        if (baseEnemyCount > 100) {
            baseEnemyCount = 100;
        }
        enemyCountScaler = Math.abs(input.getValue("Stage", "enemyCountScaler"));
        if (enemyCountScaler > 10) {
            enemyCountScaler = 10;
        }
        percentChanceToMoveEnemies = Math.abs(input.getValue("Stage", "percentChanceToMoveEnemies"));
        if (percentChanceToMoveEnemies > 100) {
            percentChanceToMoveEnemies = 100;
        }
        recognitionDistance = Math.abs(input.getValue("Stage", "recognitionDistance"));
        if (recognitionDistance > 4) {
            recognitionDistance = 4;
        }
    }

    /**
     * 
     * @param row
     * @param col
     * @return
     * @author Andrew Matherne  
     */
    public StageElement getStageContents(int row, int col) {
        return stageContents[row][col];
    }

    public int getSTAGEHEIGHT() {
        return STAGEHEIGHT;
    }

    public int getSTAGEWIDTH() {
        return STAGEWIDTH;
    }

    public int getPlayerRow() {
        return playerRow;
    }

    public int getPlayerCol() {
        return playerCol;
    }

    public void createPlayer() {
        playerRow = 0;
        playerCol = STAGEWIDTH / 2;
        int[] location = { playerRow, playerCol };
        stageContents[location[0]][location[1]] = new Player(location[0], location[1], SpriteSheet.getSprite(48));
    }

    public boolean movePlayer(int direction) {
        StageElement player = new StageElement();
        for (int i = 0; i < STAGEHEIGHT; i++) { // for every row
            for (int k = 0; k < STAGEWIDTH; k++) { // and every space in those rows
                if (stageContents[i][k] instanceof Player) { // if the occupant is an enemy
                    player = stageContents[i][k];
                }
            }
        }
        int row = player.getLocation(0);
        int col = player.getLocation(1);
        boolean moveWasMade = false;
        if (moveIsLegal(direction) == 2) {
            switch (direction) {
                case 0:
                    stageContents[row + 1][col].decrementHealth(stageContents[row][col].getAttackDamage());
                    moveWasMade = true;
                    break;
                case 1:
                    stageContents[row][col + 1].decrementHealth(stageContents[row][col].getAttackDamage());
                    moveWasMade = true;
                    break;
                case 2:
                    stageContents[row - 1][col].decrementHealth(stageContents[row][col].getAttackDamage());
                    moveWasMade = true;
                    break;
                case 3:
                    stageContents[row][col - 1].decrementHealth(stageContents[row][col].getAttackDamage());
                    moveWasMade = true;
                    break;
            }
        } else if (moveIsLegal(direction) == 1) {
            switch (direction) {
                case 0: // up
                    stageContents[row][col].setRow(stageContents[row][col].getRow() + 1);
                    stageContents[row + 1][col] = stageContents[row][col];
                    stageContents[row][col] = null;
                    moveWasMade = true;
                    playerRow++;
                    break;
                case 1: // right
                    stageContents[row][col].setCol(stageContents[row][col].getCol() + 1);
                    stageContents[row][col + 1] = stageContents[row][col];
                    stageContents[row][col] = null;
                    moveWasMade = true;
                    playerCol++;
                    break;
                case 2: // down
                    stageContents[row][col].setRow(stageContents[row][col].getRow() - 1);
                    stageContents[row - 1][col] = stageContents[row][col];
                    stageContents[row][col] = null;
                    moveWasMade = true;
                    playerRow--;
                    break;
                case 3: // left
                    stageContents[row][col].setCol(stageContents[row][col].getCol() - 1);
                    stageContents[row][col - 1] = stageContents[row][col];
                    stageContents[row][col] = null;
                    moveWasMade = true;
                    playerCol--;
                    break;
            }
        }
        return moveWasMade;
    }

    private int moveIsLegal(int direction) {
        StageElement player = new StageElement();
        for (int i = 0; i < STAGEHEIGHT; i++) { // for every row
            for (int k = 0; k < STAGEWIDTH; k++) { // and every space in those rows
                if (stageContents[i][k] instanceof Player) { // if the occupant is an enemy
                    player = stageContents[i][k];
                }
            }
        }
        switch (direction) {
            case 0: // up
                try {
                    if (stageContents[player.getLocation(0) + 1][player.getLocation(1)] == null) {
                        return 1;
                    }
                    if (stageContents[player.getLocation(0) + 1][player.getLocation(1)] instanceof Enemy) {
                        return 2;
                    }
                } catch (Exception e) {
                }
                break;
            case 1: // right
                try {
                    if (stageContents[player.getLocation(0)][player.getLocation(1) + 1] == null) {
                        return 1;
                    }
                    if (stageContents[player.getLocation(0)][player.getLocation(1) + 1] instanceof Enemy) {
                        return 2;
                    }
                } catch (Exception e) {
                }
                break;
            case 2: // down
                try {
                    if (stageContents[player.getLocation(0) - 1][player.getLocation(1)] == null) {
                        return 1;
                    }
                    if (stageContents[player.getLocation(0) - 1][player.getLocation(1)] instanceof Enemy) {
                        return 2;
                    }
                } catch (Exception e) {
                }
                break;
            case 3: // left
                try {
                    if (stageContents[player.getLocation(0)][player.getLocation(1) - 1] == null) {
                        return 1;
                    }
                    if (stageContents[player.getLocation(0)][player.getLocation(1) - 1] instanceof Enemy) {
                        return 2;
                    }
                } catch (Exception e) {
                }
                break;
        }
        return 0;
    }

    public void placeEnemies(int difficulty) {
        int quantity = baseEnemyCount + ((difficulty % 5) * enemyCountScaler);
        for (int i = 0; i < quantity; i++) {
            int chance = (int) (Math.random() * 20);
            int[] location = new int[2];
            while (true) {
                location[0] = (int) (Math.random() * STAGEHEIGHT);
                location[1] = (int) (Math.random() * STAGEWIDTH);
                if (!(stageContents[location[0]][location[1]] instanceof StageElement)) {
                    break;
                }
            }
            switch (difficulty % 5) { // see EnemyRules.txt for difficulty specifications
                case 0:
                    if (chance < 15) {
                        placeEnemyOfTier(0, location);
                    } else if (chance < 19) {
                        placeEnemyOfTier(1, location);
                    } else if (chance < 20) {
                        placeEnemyOfTier(2, location);
                    }
                    break;
                case 1:
                    if (chance < 4) {
                        placeEnemyOfTier(0, location);
                    } else if (chance < 16) {
                        placeEnemyOfTier(1, location);
                    } else if (chance < 19) {
                        placeEnemyOfTier(2, location);
                    } else if (chance < 20) {
                        placeEnemyOfTier(3, location);
                    }
                    break;
                case 2:
                    if (chance < 4) {
                        placeEnemyOfTier(1, location);
                    } else if (chance < 16) {
                        placeEnemyOfTier(2, location);
                    } else if (chance < 19) {
                        placeEnemyOfTier(3, location);
                    } else if (chance < 20) {
                        placeEnemyOfTier(4, location);
                    }
                    break;
                case 3:
                    if (chance < 4) {
                        placeEnemyOfTier(2, location);
                    } else if (chance < 14) {
                        placeEnemyOfTier(3, location);
                    } else if (chance < 17) {
                        placeEnemyOfTier(4, location);
                    } else if (chance < 19) {
                        placeEnemyOfTier(5, location);
                    } else if (chance < 20) {
                        placeEnemyOfTier(6, location);
                    }
                    break;
                case 4:
                    if (chance < 4) {
                        placeEnemyOfTier(3, location);
                    } else if (chance < 16) {
                        placeEnemyOfTier(4, location);
                    } else if (chance < 19) {
                        placeEnemyOfTier(5, location);
                    } else if (chance < 20) {
                        placeEnemyOfTier(6, location);
                    }
                    break;
                default:
            }
        }
    }

    private void placeEnemyOfTier(int tier, int[] location) {
        int enemyChoice = (int) (Math.random() * 60);
        switch (tier) { // see EnemyRules.txt for tier specifications
            case 0:
                if (enemyChoice % 2 == 0) {
                    stageContents[location[0]][location[1]] = new CaveBat(location[0], location[1]);
                } else if (enemyChoice % 2 == 1) {
                    stageContents[location[0]][location[1]] = new Slime(location[0], location[1]);
                }
                break;
            case 1:
                if (enemyChoice % 3 == 0) {
                    stageContents[location[0]][location[1]] = new Ghost(location[0], location[1]);
                } else if (enemyChoice % 3 == 1) {
                    stageContents[location[0]][location[1]] = new Gremlin(location[0], location[1]);
                } else if (enemyChoice % 3 == 2) {
                    stageContents[location[0]][location[1]] = new Spider(location[0], location[1]);
                }
                break;
            case 2:
                if (enemyChoice % 2 == 0) {
                    stageContents[location[0]][location[1]] = new ClayWarrior(location[0], location[1]);
                } else if (enemyChoice % 2 == 1) {
                    stageContents[location[0]][location[1]] = new Floater(location[0], location[1]);
                }
                break;
            case 3:
                if (enemyChoice % 2 == 0) {
                    stageContents[location[0]][location[1]] = new Berserker(location[0], location[1]);
                } else if (enemyChoice % 2 == 1) {
                    stageContents[location[0]][location[1]] = new Goblin(location[0], location[1]);
                }
                break;
            case 4:
                if (enemyChoice % 2 == 0) {
                    stageContents[location[0]][location[1]] = new Ogre(location[0], location[1]);
                } else if (enemyChoice % 2 == 1) {
                    stageContents[location[0]][location[1]] = new StoneGolem(location[0], location[1]);
                }
                break;
            /*
             * case 5:
             * if (enemyChoice % 2 == 0) {
             * stageContents[location[0]][location[1]] = new Mimic(location[0],
             * location[1]);
             * } else if (enemyChoice % 2 == 1) {
             * stageContents[location[0]][location[1]] = new Mimic(location[0],
             * location[1]);
             * }
             * break;
             * case 6:
             * if (enemyChoice % 2 == 0) {
             * stageContents[location[0]][location[1]] = new Berserker(location[0],
             * location[1]);
             * } else if (enemyChoice % 2 == 1) {
             * stageContents[location[0]][location[1]] = new Golem(location[0],
             * location[1]);
             * }
             * break;
             */
            default:
        }
    }

    public void enemyCycle() {
        cullEnemies();
        moveEnemies();
    }

    private void cullEnemies() {
        ArrayList<StageElement> enemyList = new ArrayList<StageElement>();
        for (int i = 0; i < STAGEHEIGHT; i++) { // for every row
            for (int k = 0; k < STAGEWIDTH; k++) { // and every space in those rows
                if (stageContents[i][k] instanceof Enemy) { // if the occupant is an enemy
                    enemyList.add(stageContents[i][k]); // add it to a list of enemies
                }
            }
        }
        for (int i = 0; i < enemyList.size(); i++) {
            if (enemyList.get(i).getHealth() <= 0) {
                stageContents[enemyList.get(i).getLocation(0)][enemyList.get(i).getLocation(1)] = null;
            }
        }
    }

    private void moveEnemies() {
        ArrayList<StageElement> enemyList = new ArrayList<StageElement>();
        for (int i = 0; i < STAGEHEIGHT; i++) { // for every row
            for (int k = 0; k < STAGEWIDTH; k++) { // and every space in those rows
                if (stageContents[i][k] instanceof Enemy) { // if the occupant is an enemy
                    enemyList.add(stageContents[i][k]); // add it to a list of enemies
                }
            }
        }
        for (int i = 0; i < enemyList.size(); i++) {
            int row = enemyList.get(i).getLocation(0);
            int col = enemyList.get(i).getLocation(1);
            if ((int) (Math.random() * 100) < percentChanceToMoveEnemies) { // roll a chance to move
                switch (findDirection(enemyList.get(i))) {
                    case 0: // up
                        stageContents[row][col].setRow(stageContents[row][col].getRow() + 1);
                        stageContents[row + 1][col] = stageContents[row][col];
                        stageContents[row][col] = null;
                        break;
                    case 1: // right
                        stageContents[row][col].setCol(stageContents[row][col].getCol() + 1);
                        stageContents[row][col + 1] = stageContents[row][col];
                        stageContents[row][col] = null;
                        break;
                    case 2: // down
                        stageContents[row][col].setRow(stageContents[row][col].getRow() - 1);
                        stageContents[row - 1][col] = stageContents[row][col];
                        stageContents[row][col] = null;
                        break;
                    case 3: // right
                        stageContents[row][col].setCol(stageContents[row][col].getCol() - 1);
                        stageContents[row][col - 1] = stageContents[row][col];
                        stageContents[row][col] = null;
                        break;
                    default:
                }
            }
        }
    }

    private int findDirection(StageElement enemy) {
        int direction = 4;
        if (playerIsNearby(enemy)) {
            int rowRelativeToPlayer = 0;
            int colRelativeToPlayer = 0;
            for (int i = -recognitionDistance; i < recognitionDistance + 1; i++) { // find which direction the player is
                                                                                   // in
                for (int k = -recognitionDistance; k < recognitionDistance + 1; k++) {
                    try {
                        if (stageContents[enemy.getRow() + i][enemy.getCol() + k] instanceof Player) {
                            rowRelativeToPlayer = i;
                            colRelativeToPlayer = k;
                        }
                    } catch (Exception e) {
                    }
                }
            }
            int choice = (int) (Math.random() * 2);
            if (rowRelativeToPlayer > 0) {
                if (colRelativeToPlayer > 0) {
                    if (choice == 0) {
                        try {
                            if (stageContents[enemy.getLocation(0) + 1][enemy.getLocation(1)] == null) {
                                direction = 0;
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) + 1] == null) {
                                direction = 1;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else if (colRelativeToPlayer < 0) {
                    if (choice == 0) {
                        try {
                            if (stageContents[enemy.getLocation(0) + 1][enemy.getLocation(1)] == null) {
                                direction = 0;
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) - 1] == null) {
                                direction = 3;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    try {
                        if (stageContents[enemy.getLocation(0) + 1][enemy.getLocation(1)] == null) {
                            direction = 0;
                        }
                    } catch (Exception e) {
                    }
                }
            } else if (rowRelativeToPlayer < 0) {
                if (colRelativeToPlayer > 0) {
                    if (choice == 0) {
                        try {
                            if (stageContents[enemy.getLocation(0) - 1][enemy.getLocation(1)] == null) {
                                direction = 2;
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) + 1] == null) {
                                direction = 1;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else if (colRelativeToPlayer < 0) {
                    if (choice == 0) {
                        try {
                            if (stageContents[enemy.getLocation(0) - 1][enemy.getLocation(1)] == null) {
                                direction = 2;
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        try {
                            if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) - 1] == null) {
                                direction = 3;
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    try {
                        if (stageContents[enemy.getLocation(0) - 1][enemy.getLocation(1)] == null) {
                            direction = 2;
                        }
                    } catch (Exception e) {
                    }
                }
            } else {
                if (colRelativeToPlayer > 0) {
                    try {
                        if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) + 1] == null) {
                            direction = 1;
                        }
                    } catch (Exception e) {
                    }
                } else if (colRelativeToPlayer < 0) {
                    try {
                        if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) - 1] == null) {
                            direction = 3;
                        }
                    } catch (Exception e) {
                    }
                } else {
                    System.out.println(
                            "what in the sam hell just happened how is there a player and enemy on the same damn square? My brother in christ you done goofed up");
                }
            }
        } else {
            for (int i = 0; i < 10; i++) { // try 10 times to move
                switch ((int) (Math.random() * 4)) { // pick a random direction 0..3, if its clear thats your direction
                    case 0: // up
                        try {
                            if (stageContents[enemy.getLocation(0) + 1][enemy.getLocation(1)] == null) {
                                direction = 0;
                            }
                        } catch (Exception e) {
                        }
                        break;
                    case 1: // right
                        try {
                            if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) + 1] == null) {
                                direction = 1;
                            }
                        } catch (Exception e) {
                        }
                        break;
                    case 2: // down
                        try {
                            if (stageContents[enemy.getLocation(0) - 1][enemy.getLocation(1)] == null) {
                                direction = 2;
                            }
                        } catch (Exception e) {
                        }
                        break;
                    case 3: // left
                        try {
                            if (stageContents[enemy.getLocation(0)][enemy.getLocation(1) - 1] == null) {
                                direction = 3;
                            }
                        } catch (Exception e) {
                        }
                        break;
                }
                if (direction != 4) { // if a valid direction was found break the loop and return it
                    break;
                }
            }
        }
        return direction;
    }

    private boolean playerIsNearby(StageElement enemy) {
        for (int i = -recognitionDistance; i < recognitionDistance + 1; i++) {
            for (int k = -recognitionDistance; k < recognitionDistance + 1; k++) {
                try {
                    if (stageContents[enemy.getRow() + i][enemy.getCol() + k] instanceof Player) {
                        return true;
                    }
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    public String toString() {
        String returnValue = "";
        for (int i = STAGEHEIGHT - 1; i > -1; i--) {
            returnValue += ("\n" + String.format("%02d", i) + ": |");
            for (int k = 0; k < STAGEWIDTH; k++) {
                try {
                    returnValue += stageContents[i][k].getName();
                    returnValue += "|";
                } catch (Exception NullPointerException) {
                    returnValue += " * |";
                }
            }
        }
        return returnValue;
    }
}