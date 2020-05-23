package com.TerraMystica.GameLogic;

import java.util.List;
import java.util.Set;

public abstract class Hexagon {
    int row;
    int col;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public abstract Dwelling getStructure();

    public abstract Set<Terrain> getShippingTerrains(int shippingValue);

    public abstract TerrainType getTerrainType();

}
