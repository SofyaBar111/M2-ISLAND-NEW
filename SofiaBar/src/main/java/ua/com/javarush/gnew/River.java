package ua.com.javarush.gnew;

public class River extends Biome {
    public River() {
        super("RIVER", "images/river.png");
    }

    @Override
    public void generateResources() {
        System.out.println("The river provides water and fish for the animals.");
    }
}
