package ua.com.javarush.gnew;

public class Desert extends Biome {
    public Desert() {
        super("DESERT", "images/Desert (2).png");
    }

    @Override
    public void generateResources() {
        System.out.println("The desert is a harsh environment with limited resources.");
    }
}