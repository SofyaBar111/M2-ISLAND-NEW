package ua.com.javarush.gnew;

public class Forest extends Biome {
    public Forest() {
        super("FOREST", "images/forest.png");
    }

    @Override
    public void generateResources() {
        System.out.println("The forest generates trees and food for herbivores.");
    }
}