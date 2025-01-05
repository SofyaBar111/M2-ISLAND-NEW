package ua.com.javarush.gnew;

public abstract class Herbivore extends Animal {
    public Herbivore(String name, int health, int speed, int energy) {
        super(name, health, speed, energy);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "looking for plants and moving around";
    }

    @Override
    protected String getDefaultEatAction() {
        return "eats plants";
    }
}