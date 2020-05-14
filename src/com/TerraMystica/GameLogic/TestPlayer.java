package com.TerraMystica.GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    Player player;

    @BeforeEach
    void setup() {
        Faction faction = new Witches();
        player = new Player(faction);
    }

    static Stream<Terrain> terrainFactory() {
        return Stream.of(TerrainType.values())
                     .map(Terrain::new);
    }

    static Stream<Faction> factionFactory() {
        return Stream.of(new Witches());
    }

    @ParameterizedTest
    @MethodSource("factionFactory")
    void testPlayer() {
        assertTrue(player.getResource().getWorker() > 0, "Player should have worker(s) at the beginning.");
        assertTrue(player.getResource().getCoin() > 0, "Player should have coin(s) at the beginning.");
        assertTrue(player.getResource().getPriest() == 0 | player.getResource().getPriest() == 1, "Player should have zero or one priest at the beginning.");
        assertTrue(player.getResource().getPower() == 12, "Player should have exactly 12 powers at the beginning.");
        assertEquals(0, player.getResource().getSpade(), "Player should not have any spades at the beginning.");
    }

    @ParameterizedTest
    @MethodSource("terrainFactory")
    void testTerraformAndBuild(Terrain terrain) {
        int spadesBefore = player.getResource().getSpade();
        int workerBefore = player.getResource().getWorker();
        int coinBefore = player.getResource().getCoin();
        player.terraformAndBuild(terrain);
        int spadesAfter = player.getResource().getSpade();
        int workerAfter = player.getResource().getWorker();
        int coinAfter = player.getResource().getCoin();

        assertNotNull(terrain.getStructure(), "Hexagon should have structure.");
        assertTrue(player.getStructures().contains(terrain.getStructure()), "Structures of player should contain structure on the hexagon.");
        assertTrue(spadesAfter <= spadesBefore, "Spades should decrease after terraforming.");
        assertEquals(terrain.getTerrainType(), player.getFaction().getTerrainType(), "Terrain type of the hexagon should be same as faction's terrain type." );

        assertTrue(workerAfter <= workerBefore, "Workers of the player should decrease after terraformAndBuild.");
        assertTrue(coinAfter <= coinBefore, "Coins of the player should decrease after terraformAndBuild.");

        System.out.println("Player has " + player.getStructures().size() + " structure");
    }

    static Stream<Arguments> structureFactory() {
        var list = new ArrayList();
        list.add(new Object());
        return Stream.of(
                Arguments.of("dwellings", list),
                Arguments.of("tradingHouses", list),
                Arguments.of("strongholds", list),
                Arguments.of("temples", list),
                Arguments.of("sanctuaries", list)
        );
    }

    @ParameterizedTest
    @MethodSource("structureFactory")
    void testCollectIncome(String structuresFieldName, List structures) throws NoSuchFieldException, IllegalAccessException {
        Field structuresProperty = player.getClass().getDeclaredField(structuresFieldName);
        structuresProperty.setAccessible(true); // Make player.dwellings public at runtime.
        structuresProperty.set(player, structures); // player.dwellings = dwellingList;

        Resource beforeResource = new Resource(player.getResource());
        player.collectIncome();
        Resource afterResource = player.getResource();

        incomeTest(afterResource, beforeResource);
    }

    public static void incomeTest(Resource afterResource, Resource beforeResource) {
        assertTrue(afterResource.getWorker() >= beforeResource.getWorker(), "Resources should not decrease after collecting income.");
        assertTrue(afterResource.getCoin() >= beforeResource.getCoin(), "Coins should not decrease after collecting income.");
        assertTrue(afterResource.getPriest() >= beforeResource.getPriest(), "Priests should not decrease after collecting income.");
        assertTrue(afterResource.getPower() >= beforeResource.getPower(), "Powers should not decrease after collecting income.");

        assertTrue(
                afterResource.getWorker() > beforeResource.getWorker() ||
                        afterResource.getCoin() > beforeResource.getCoin() ||
                        afterResource.getPriest() > beforeResource.getPriest() ||
                        afterResource.getPower() > beforeResource.getPower(),
                "One of the resources should increase after collecting income."
        );
    }
}
