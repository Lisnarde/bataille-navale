package model.weapons;

public enum WeaponTypes {
    MISSILE,
    ISLANDSEARCH,
    BOMB,
    SONAR;

    public String toString() {
        return switch (this) {
            case MISSILE -> "Missile";
            case ISLANDSEARCH -> "Fouiller";
            case BOMB -> "Bombe";
            case SONAR -> "Sonar";
        };
    }
}
