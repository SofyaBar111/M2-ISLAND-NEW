package ua.com.javarush.gnew;

public class Meadow extends Biome {
    public Meadow () {
        super("MEADOW", "images/meadow.png");
    }

    @Override
    public void generateResources() {
        System.out.println("The glade provides grass and plants for herbivores.");
    }
}