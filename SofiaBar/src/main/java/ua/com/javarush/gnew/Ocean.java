package ua.com.javarush.gnew;

public class Ocean extends Biome {
    public Ocean() {
        super("OCEAN", "images/Ocean.png");
    }

    @Override
    public void generateResources() {
        System.out.println("The ocean provides fish and seaweed.");
    }
}