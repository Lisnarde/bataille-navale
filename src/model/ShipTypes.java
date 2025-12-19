package model;

public enum ShipTypes {
    AircraftCarrier,
    Cruiser,
    Destroyer,
    Submarine,
    Torpedo;

    public int getSize() {
        return switch (this) {
            case AircraftCarrier -> 5;
            case Cruiser -> 4;
            case Destroyer -> 3;
            case Submarine -> 3;
            case Torpedo -> 2;
        };
    }
    @Override
    public String toString() {
        return switch (this) {
            case AircraftCarrier -> "Porte-avion";
            case Cruiser -> "Croiseur";
            case Destroyer -> "Contre-torpilleur";
            case Submarine -> "Sous-marin";
            case Torpedo -> "Torpilleur";
        };
    }
}
