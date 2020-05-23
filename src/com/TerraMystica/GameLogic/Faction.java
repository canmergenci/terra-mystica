package com.TerraMystica.GameLogic;

import java.util.Map;

public abstract class Faction {

    Map<TerrainType, Integer> terraformingCost;

    public Integer getCost(Hexagon hexagon) {
        return terraformingCost.get(hexagon.getTerrainType());
    }

    public abstract Resource initializeResources();
    public abstract TerrainType getTerrainType();

    public abstract void terraform(Hexagon hexagon);
}
