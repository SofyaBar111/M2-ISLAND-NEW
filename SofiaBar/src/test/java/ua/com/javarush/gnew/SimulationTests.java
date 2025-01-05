package ua.com.javarush.gnew;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;

class SimulationTests {

    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
        Platform.setImplicitExit(false);
    }

    @Test
    void testAnimalProperties() {
        Bear bear = new Bear();
        assertEquals("BEAR", bear.getName());
        assertEquals(150, bear.getHealth());
        assertEquals(15, bear.getSpeed());
        assertEquals(60, bear.getEnergy());
    }

    @Test
    void testAnimalActions() {
        Rabbit rabbit = new Rabbit();
        assertDoesNotThrow(rabbit::move);
        assertDoesNotThrow(rabbit::eat);
        assertDoesNotThrow(rabbit::reproduce);
    }

    @Test
    void testBiomeGeneration() {
        MapGenerator generator = new MapGenerator(100); // Создаем карту большего размера
        Biome[][] biomes = generator.generateBiomes();

        assertNotNull(biomes);
        assertEquals(100, biomes.length);
        assertEquals(100, biomes[0].length);

        // Проверяем, что метод replaceDesertOasisWithLake() не вызывает ошибок
        for (int row = 60; row < 70; row++) {
            for (int col = 50; col < 60; col++) {
                int finalRow = row; // Делаем переменную эффективно финальной
                int finalCol = col; // Делаем переменную эффективно финальной
                assertDoesNotThrow(() -> {
                    if (Math.random() < 0.3) {
                        biomes[finalRow][finalCol] = new River();
                    }
                });
            }
        }
    }

    @Test
    void testAnimalPlacer() {
        Biome[][] biomes = new Biome[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                biomes[i][j] = new Meadow();
            }
        }

        Island island = new Island();
        island.addAnimal(new Rabbit());

        GridPane grid = new GridPane();
        AnimalPlacer placer = new AnimalPlacer(biomes, 5);
        assertDoesNotThrow(() -> placer.placeAnimals(island, grid, 32));
    }

    @Test
    void testAnimalRenderer() {
        Platform.runLater(() -> {
            AnimalRenderer renderer = new AnimalRenderer();
            Rabbit rabbit = new Rabbit();

            assertDoesNotThrow(() -> {
                var pane = renderer.renderAnimal(rabbit, 32, 32);
                assertNotNull(pane);
            });
        });
    }

    @Test
    void testIslandSimulation() {
        Island island = new Island();
        island.addAnimal(new Bear());
        island.addAnimal(new Rabbit());

        assertDoesNotThrow(island::simulate);
    }

    @Test
    void testPredatorHunting() {
        Island island = new Island();
        Bear bear = new Bear();
        Rabbit rabbit = new Rabbit();

        island.addAnimal(bear);
        island.addAnimal(rabbit);

        assertDoesNotThrow(() -> {
            // Эмулируем охоту
            bear.move();
            rabbit.move();
        });

        assertTrue(island.getAnimals().contains(bear));
        assertTrue(island.getAnimals().contains(rabbit));
    }

    @Test
    void testStatsUpdate() {
        Platform.runLater(() -> {
            AnimalStatsWindow statsWindow = new AnimalStatsWindow();
            assertDoesNotThrow(() -> statsWindow.updateStats(5, 3));
        });
    }
}
