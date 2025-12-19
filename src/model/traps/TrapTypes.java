package model.traps;

public enum TrapTypes {
    BLACKHOLE,
    TORNADO;

    public String toString() {
        return switch (this) {
            case BLACKHOLE -> "Trou noir";
            case TORNADO -> "Tornade";
        };
    }
}
