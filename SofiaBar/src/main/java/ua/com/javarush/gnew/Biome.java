package ua.com.javarush.gnew;

public abstract class Biome {
    private final String name;
    private final String imagePath;

    public Biome(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public abstract void generateResources();
}