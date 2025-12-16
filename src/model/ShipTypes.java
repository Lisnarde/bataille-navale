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
            case Destroyer -> 4;
            case Submarine -> 3;
            case Torpedo -> 2;
        };
    }
}
