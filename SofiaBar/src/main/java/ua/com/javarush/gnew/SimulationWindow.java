package ua.com.javarush.gnew;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SimulationWindow extends Application {
    private static final int GRID_SIZE = 80;
    private static final int TILE_SIZE = 30;
    private static final int MOVE_INTERVAL_MS = 1000;

    private Island island;
    private Biome[][] biomes;
    private GridPane grid;
    private AnimalStatsWindow statsWindow;

    @Override
    public void start(Stage primaryStage) {
        MapGenerator generator = new MapGenerator(GRID_SIZE);
        biomes = generator.generateBiomes();

        island = new Island();
        Main.populateIslandWithAnimals(island);

        BiomeGridCreator gridCreator = new BiomeGridCreator(biomes, GRID_SIZE);
        grid = gridCreator.createGrid(TILE_SIZE);

        AnimalPlacer animalPlacer = new AnimalPlacer(biomes, GRID_SIZE);
        animalPlacer.placeAnimals(island, grid, TILE_SIZE);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        Scene scene = new Scene(scrollPane, 1200, 1000);
        primaryStage.setTitle("Island simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        statsWindow = new AnimalStatsWindow();
        statsWindow.show();

        startAnimalMovement();
    }

    private void startAnimalMovement() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(MOVE_INTERVAL_MS), event -> {
            moveAnimals();
            predatorHunting();
            maintainAnimalCount();
            updateAnimalStats();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveAnimals() {
        for (Animal animal : island.getAnimals()) {
            StackPane animalNode = findAnimalNode(animal);
            if (animalNode == null) continue;

            int oldRow = GridPane.getRowIndex(animalNode);
            int oldCol = GridPane.getColumnIndex(animalNode);

            int newRow, newCol;
            do {
                newRow = oldRow + (int) (Math.random() * 3) - 1;
                newCol = oldCol + (int) (Math.random() * 3) - 1;
            } while (!isValidMove(newRow, newCol));

            GridPane.setConstraints(animalNode, newCol, newRow);
        }
    }

    private void predatorHunting() {
        for (Animal animal : island.getAnimals()) {
            if (animal instanceof Predator predator) {
                Herbivore target = findNearestHerbivore(predator);
                if (target != null) {
                    moveToTarget(predator, target);
                    if (isCaught(predator, target)) {
                        island.getAnimals().remove(target);
                        StackPane targetNode = findAnimalNode(target);
                        if (targetNode != null) {
                            grid.getChildren().remove(targetNode);
                        }
                        predator.restoreEnergy();
                    }
                }
            }
        }
    }

    private void maintainAnimalCount() {
        if (island.getAnimals().size() < 8) {
            Timeline addAnimalTimeline = new Timeline(new KeyFrame(Duration.seconds(20), event -> {
                if (island.getAnimals().size() < 8) {
                    addRandomAnimal();
                }
            }));
            addAnimalTimeline.setCycleCount(1);
            addAnimalTimeline.play();
        }
    }

    private void addRandomAnimal() {
        Animal newAnimal;
        if (Math.random() > 0.3) {
            newAnimal = createRandomHerbivore();
        } else {
            newAnimal = createRandomPredator();
        }

        island.getAnimals().add(newAnimal);

        int randomRow, randomCol;
        do {
            randomRow = (int) (Math.random() * GRID_SIZE);
            randomCol = (int) (Math.random() * GRID_SIZE);
        } while (biomes[randomRow][randomCol] instanceof Ocean || biomes[randomRow][randomCol] instanceof River);

        StackPane animalTile = new AnimalRenderer().renderAnimal(newAnimal, TILE_SIZE, TILE_SIZE);
        animalTile.setUserData(newAnimal);
        grid.add(animalTile, randomCol, randomRow);
    }

    private Herbivore createRandomHerbivore() {
        return switch ((int) (Math.random() * 4)) {
            case 0 -> new Rabbit();
            case 1 -> new Sheep();
            case 2 -> new Boar();
            case 3 -> new Horse();
            default -> new Rabbit();
        };
    }

    private Predator createRandomPredator() {
        return switch ((int) (Math.random() * 3)) {
            case 0 -> new Wolf();
            case 1 -> new Panther();
            case 2 -> new Bear();
            default -> new Wolf();
        };
    }

    private void updateAnimalStats() {
        int liveAnimals = island.getAnimals().size();
        int deadAnimals = 8 - liveAnimals;
        statsWindow.updateStats(liveAnimals, deadAnimals);
    }

    private Herbivore findNearestHerbivore(Predator predator) {
        double minDistance = Double.MAX_VALUE;
        Herbivore nearestHerbivore = null;

        for (Animal animal : island.getAnimals()) {
            if (animal instanceof Herbivore herbivore) {
                double distance = calculateDistance(predator, herbivore);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestHerbivore = herbivore;
                }
            }
        }
        return nearestHerbivore;
    }

    private double calculateDistance(Animal a, Animal b) {
        StackPane aNode = findAnimalNode(a);
        StackPane bNode = findAnimalNode(b);
        if (aNode == null || bNode == null) return Double.MAX_VALUE;

        int rowA = GridPane.getRowIndex(aNode);
        int colA = GridPane.getColumnIndex(aNode);
        int rowB = GridPane.getRowIndex(bNode);
        int colB = GridPane.getColumnIndex(bNode);

        return Math.sqrt(Math.pow(rowA - rowB, 2) + Math.pow(colA - colB, 2));
    }

    private void moveToTarget(Predator predator, Herbivore target) {
        StackPane predatorNode = findAnimalNode(predator);
        StackPane targetNode = findAnimalNode(target);
        if (predatorNode == null || targetNode == null) return;

        int predatorRow = GridPane.getRowIndex(predatorNode);
        int predatorCol = GridPane.getColumnIndex(predatorNode);
        int targetRow = GridPane.getRowIndex(targetNode);
        int targetCol = GridPane.getColumnIndex(targetNode);

        int newRow = predatorRow + Integer.compare(targetRow, predatorRow);
        int newCol = predatorCol + Integer.compare(targetCol, predatorCol);

        if (isValidMove(newRow, newCol)) {
            GridPane.setConstraints(predatorNode, newCol, newRow);
        }
    }

    private boolean isCaught(Predator predator, Herbivore herbivore) {
        StackPane predatorNode = findAnimalNode(predator);
        StackPane herbivoreNode = findAnimalNode(herbivore);

        if (predatorNode == null || herbivoreNode == null) return false;

        int predatorRow = GridPane.getRowIndex(predatorNode);
        int predatorCol = GridPane.getColumnIndex(predatorNode);
        int herbivoreRow = GridPane.getRowIndex(herbivoreNode);
        int herbivoreCol = GridPane.getColumnIndex(herbivoreNode);

        return predatorRow == herbivoreRow && predatorCol == herbivoreCol;
    }

    private StackPane findAnimalNode(Animal animal) {
        for (var node : grid.getChildren()) {
            if (node instanceof StackPane stackPane) {
                if (stackPane.getUserData() == animal) {
                    return stackPane;
                }
            }
        }
        return null;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE &&
                !(biomes[row][col] instanceof Ocean || biomes[row][col] instanceof River);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
