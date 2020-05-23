package com.TerraMystica.GameLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.TerraMystica.GameLogic.TerrainType.*;

public class GameBoard {
    private static final GameBoard SINGLETON = new GameBoard();
    private Hexagon[][] hexagons;

    private GameBoard() {
        reset();
    }

    public static GameBoard getInstance() {
        return SINGLETON;
    }

    public void reset() {
        hexagons = new Hexagon[9][];
        hexagons[0] = new Hexagon[] {
                new Terrain(PLAINS), new Terrain(MOUNTAINS), new Terrain(FOREST), new Terrain(LAKES),
                new Terrain(DESERT), new Terrain(WASTELAND), new Terrain(PLAINS), new Terrain(SWAMP),
                new Terrain(WASTELAND), new Terrain(FOREST), new Terrain(LAKES), new Terrain(WASTELAND), new Terrain(SWAMP)
        };
        hexagons[1] = new Hexagon[] {
                new Terrain(DESERT), new River(), new River(), new Terrain(PLAINS), new Terrain(SWAMP),
                new River(), new River(), new Terrain(DESERT), new Terrain(SWAMP), new River(), new River(), new Terrain(DESERT)
        };
        hexagons[2] = new Hexagon[] {
                new River(), new River(), new Terrain(SWAMP), new River(), new Terrain(MOUNTAINS), new River(), new Terrain(FOREST),
                new River(), new Terrain(FOREST), new River(), new Terrain(MOUNTAINS), new River(), new River()
        };
        hexagons[3] = new Hexagon[] {
                new Terrain(FOREST), new Terrain(LAKES), new Terrain(DESERT), new River(), new River(), new Terrain(WASTELAND),
                new Terrain(LAKES), new River(), new Terrain(WASTELAND), new River(), new Terrain(WASTELAND), new Terrain(PLAINS)
        };
        hexagons[4] = new Hexagon[] {
                new Terrain(SWAMP), new Terrain(PLAINS), new Terrain(WASTELAND), new Terrain(LAKES), new Terrain(SWAMP),
                new Terrain(PLAINS), new Terrain(MOUNTAINS), new Terrain(DESERT), new River(), new River(), new Terrain(FOREST),
                new Terrain(SWAMP), new Terrain(LAKES)
        };
        hexagons[5] = new Hexagon[] {
                new Terrain(MOUNTAINS), new Terrain(FOREST), new River(), new River(), new Terrain(DESERT), new Terrain(FOREST),
                new River(), new River(), new River(), new Terrain(PLAINS), new Terrain(MOUNTAINS), new Terrain(PLAINS)
        };
        hexagons[6] = new Hexagon[] {
                new River(), new River(), new River(), new Terrain(MOUNTAINS), new River(), new Terrain(WASTELAND), new River(),
                new Terrain(FOREST), new River(), new Terrain(DESERT), new Terrain(SWAMP), new Terrain(LAKES), new Terrain(DESERT)
        };
        hexagons[7] = new Hexagon[] {
                new Terrain(DESERT), new Terrain(LAKES), new Terrain(PLAINS), new River(), new River(), new River(), new Terrain(LAKES),
                new Terrain(SWAMP), new River(), new Terrain(MOUNTAINS), new Terrain(PLAINS), new Terrain(MOUNTAINS)
        };
        hexagons[8] = new Hexagon[] {
                new Terrain(WASTELAND), new Terrain(SWAMP), new Terrain(MOUNTAINS), new Terrain(LAKES), new Terrain(WASTELAND),
                new Terrain(FOREST), new Terrain(DESERT), new Terrain(PLAINS), new Terrain(MOUNTAINS), new River(), new Terrain(LAKES),
                new Terrain(FOREST), new Terrain(WASTELAND)
        };

        for (int i = 0; i < hexagons.length; i++) {
            for (int j = 0; j < hexagons[i].length; j++) {
                hexagons[i][j].setRow(i);
                hexagons[i][j].setCol(j);
            }
        }
    }

    public Set<Terrain> getAvailableTerrainsForTerraformAndBuild(Player player) {
        Set<Terrain> result = new HashSet<>();
        player.getTerrains().forEach(terrain -> result.addAll((getAllAdjacentTerrains(terrain, player.getShippingValue()))));
        result.removeIf(terrain -> terrain.hasStructure());
        return result;
    }

    public Set<Terrain> getAllAdjacentTerrains(Hexagon hexagon, int shippingValue) {
        Set<Terrain> result = new HashSet<>();
        for (var neighbor : getNeighborHexagons(hexagon)) {
            result.addAll(neighbor.getShippingTerrains(shippingValue - 1));
        }
        result.remove(hexagon);
        return result;
    }

    public List<Hexagon> getNeighborHexagons(Hexagon hexagon) {
        int row = hexagon.getRow();
        int col = hexagon.getCol();
        List<Hexagon> result = new ArrayList<Hexagon>();

        try {
            result.add(hexagons[row][col-1]);
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            result.add(hexagons[row][col+1]);
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            result.add(hexagons[row-1][col]);
        }
        catch (IndexOutOfBoundsException e) {}

        try {
            result.add(hexagons[row+1][col]);
        }
        catch (IndexOutOfBoundsException e) {}

        if (row % 2 == 0) {
            try {
                result.add(hexagons[row-1][col-1]);
            }
            catch (IndexOutOfBoundsException e) {}

            try {
                result.add(hexagons[row+1][col-1]);
            }
            catch (IndexOutOfBoundsException e) {}
        }
        else {
            try {
                result.add(hexagons[row-1][col+1]);
            }
            catch (IndexOutOfBoundsException e) {}

            try {
                result.add(hexagons[row+1][col+1]);
            }
            catch (IndexOutOfBoundsException e) {}
        }
        return result;
    }

    public Hexagon getHexagon(int row, int col) {
        return hexagons[row][col];
    }

    public Hexagon[][] getHexagons2D() {
        return hexagons;
    }

    public List<Hexagon> getHexagons() {
        var result = new ArrayList<Hexagon>();
        for (int i = 0; i < hexagons.length; i++) {
            for (int j = 0; j < hexagons[i].length; j++) {
                result.add(hexagons[i][j]);
            }
        }
        return result;
    }

    public List<Terrain> getUnoccupiedTerrains(TerrainType terrainType) {
        List<Terrain> result = new ArrayList<>();
        for (var hexagon : getHexagons()) {
            if (hexagon.getTerrainType() == terrainType && hexagon.getStructure() == null) {
                result.add((Terrain) hexagon);
            }
        }
        return result;
    }
}
