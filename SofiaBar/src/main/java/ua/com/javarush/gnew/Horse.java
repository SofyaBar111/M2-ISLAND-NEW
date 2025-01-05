package ua.com.javarush.gnew;

public class Horse extends Herbivore {
    public Horse() {
        super("HORSE", 100, 25, 50);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "running fast in the fields";
    }

    @Override
    protected String getDefaultEatAction() {
        return "grass eater";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + "reproduces quickly.");
    }
}