package edu.ozyegin;

public class Ship {
    private int size;
    private int hitsLeft;
    private String symbol;
    private int shipNumber;
    ShipController shipController;

    Ship(String symbol, int size, int shipNumber){
        this.symbol=symbol;
        this.size = size;
        this.hitsLeft = size;
        this.shipNumber = shipNumber;
        this.shipController = new ShipController();
    }

    public int getShipNumber() {
        return shipNumber;
    }

    public int getSize() {
        return size;
    }

    public int getHitsLeft() {
        return hitsLeft;
    }

    public String getSymbol() {
        return symbol;
    }
}
