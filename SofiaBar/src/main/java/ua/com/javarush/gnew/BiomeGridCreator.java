package ua.com.javarush.gnew;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BiomeGridCreator {
    private final Biome[][] biomes;
    private final int gridSize;

    public BiomeGridCreator(Biome[][] biomes, int gridSize) {
        this.biomes = biomes;
        this.gridSize = gridSize;
    }

    public GridPane createGrid(int tileSize) {
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                StackPane tile = createTile(biomes[row][col], tileSize);
                grid.add(tile, col, row);
            }
        }
        return grid;
    }

    private StackPane createTile(Biome biome, int tileSize) {
        StackPane stackPane = new StackPane();
        if (biome != null) {
            ImageView imageView = new ImageView(new Image(biome.getImagePath()));
            imageView.setFitWidth(tileSize);
            imageView.setFitHeight(tileSize);
            stackPane.getChildren().add(imageView);
        }
        return stackPane;
    }
}