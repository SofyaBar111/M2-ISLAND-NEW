package ua.com.javarush.gnew;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class AnimalPlacer {
    private final Biome[][] biomes;
    private final AnimalRenderer renderer;
    private final int gridSize;

    public AnimalPlacer(Biome[][] biomes, int gridSize) {
        this.biomes = biomes;
        this.renderer = new AnimalRenderer();
        this.gridSize = gridSize;
    }

    public void placeAnimals(Island island, GridPane grid, int tileSize) {
        for (Animal animal : island.getAnimals()) {
            int randomRow;
            int randomCol;

            do {
                randomRow = (int) (Math.random() * gridSize);
                randomCol = (int) (Math.random() * gridSize);
            } while (biomes[randomRow][randomCol] instanceof Ocean ||
                    biomes[randomRow][randomCol] instanceof River);

            StackPane animalTile = renderer.renderAnimal(animal, tileSize, tileSize);
            animalTile.setUserData(animal);
            grid.add(animalTile, randomCol, randomRow);
        }
    }
}
