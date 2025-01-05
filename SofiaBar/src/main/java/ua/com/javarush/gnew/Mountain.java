package ua.com.javarush.gnew;

public class Mountain extends Biome {
    public Mountain() {
        super("MOUNTAIN", "images/mountain.png");
    }

    @Override
    public void generateResources() {
        System.out.println("Mountains are difficult to access but may contain animal refuges.");
    }
}