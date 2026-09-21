package main.java.abstraction.assignment_problems;

public abstract class GameCharacter {
    private final String characterId;
    private static int characterCounter = 0;

    protected GameCharacter(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        characterCounter++;
        characterId = "CHAR-" + characterCounter;
    }

    public abstract String getSpecialMove();

    public String getCharacterId() {
        return characterId;
    }
}

interface Attackable {
    String attack();
    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

class Warrior extends GameCharacter implements Attackable, Defendable {
    private final String name;

    public Warrior(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        if (weaponName == null || weaponName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {
    private final String trapType;

    public Trap(String trapType) {
        if (trapType == null || trapType.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

class Arena {
    public static void resolveDefense(Defendable[] combatants) {
        if (combatants == null) {
            return;
        }

        for (Defendable combatant : combatants) {
            if (combatant != null) {
                System.out.println(combatant.defend());
            }
        }
    }
}
