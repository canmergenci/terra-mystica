package com.TerraMystica.GameLogic;

import java.util.Map;

public class Witches extends Faction {

    public Witches() {
        terraformingCost =  Map.ofEntries(
                Map.entry(TerrainType.FOREST,0),
                Map.entry(TerrainType.LAKES, 1),
                Map.entry(TerrainType.MOUNTAINS, 1),
                Map.entry(TerrainType.WASTELAND, 2),
                Map.entry(TerrainType.SWAMP, 2),
                Map.entry(TerrainType.PLAINS, 3),
                Map.entry(TerrainType.DESERT, 3)
        );
    }

    public Resource initializeResources() {
        return new Resource(3, 15);
    }

    public TerrainType getTerrainType() {
        return TerrainType.FOREST;
    }

    public void terraform(Hexagon hexagon) {
        hexagon.setTerrainType(getTerrainType());
    }
}
