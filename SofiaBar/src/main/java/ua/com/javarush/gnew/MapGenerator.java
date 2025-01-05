package ua.com.javarush.gnew;

public class MapGenerator {
    private final int gridSize;
    private final Biome[][] biomes;

    public MapGenerator(int gridSize) {
        this.gridSize = gridSize;
        this.biomes = new Biome[gridSize][gridSize];
    }

    public Biome[][] generateBiomes() {
        fillWithOcean();
        addForests();
        addMountains();
        addDeserts();
        addRivers();
        return biomes;
    }

    private void fillWithOcean() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                biomes[row][col] = new Ocean();
            }
        }
    }

    private void addForests() {
        generateBiome(10, 10, 40, 30, new Forest(), 1.0, true);
        generateBiome(20, 20, 15, 10, new Meadow(), 0.8, false);
    }

    private void addMountains() {
        generateBiome(35, 35, 20, 15, new Mountain(), 1.0, true);
        replaceMountainWithRiversAndMeadows();
    }

    private void addDeserts() {
        generateBiome(55, 45, 25, 20, new Desert(), 1.0, true);
        generateBiome(60, 50, 10, 10, new Meadow(), 1.0, true);
        replaceDesertOasisWithLake();
    }

    private void addRivers() {
        generateCurvedRiver();
    }

    private void generateBiome(int startX, int startY, int width, int height, Biome biome, double density, boolean fullCenter) {
        for (int row = startY; row < startY + height; row++) {
            for (int col = startX; col < startX + width; col++) {
                if (row >= 0 && row < gridSize && col >= 0 && col < gridSize) {
                    if (fullCenter || Math.random() < density) {
                        biomes[row][col] = biome;
                    }
                }
            }
        }
    }

    private void replaceMountainWithRiversAndMeadows() {
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                if (biomes[row][col] instanceof Mountain) {
                    if (Math.random() < 0.1) {
                        biomes[row][col] = new River();
                    } else if (Math.random() < 0.2) {
                        biomes[row][col] = new Meadow();
                    }
                }
            }
        }
    }

    private void replaceDesertOasisWithLake() {
        for (int row = 60; row < 70; row++) {
            for (int col = 50; col < 60; col++) {
                if (Math.random() < 0.3) {
                    biomes[row][col] = new River();
                }
            }
        }
    }

    private void generateCurvedRiver() {
        int currentRow = 5;
        int currentCol = 5;
        int riverWidth = 2;

        while (currentRow < gridSize - 5 && currentCol < gridSize - 5) {
            for (int i = 0; i < riverWidth; i++) {
                int row = currentRow + i;
                int col = currentCol;
                if (row >= 0 && row < gridSize && col >= 0 && col < gridSize) {
                    biomes[row][col] = new River();
                }
            }

            currentRow += Math.random() > 0.7 ? 1 : 0;
            currentCol += Math.random() > 0.3 ? 1 : -1;
        }
    }
}