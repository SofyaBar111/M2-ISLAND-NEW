package ua.com.javarush.gnew;

public class Panther extends Predator {
    public Panther() {
        super("PANTHER", 100, 30, 50);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "moves silently in search of prey";
    }

    @Override
    protected String getDefaultEatAction() {
        return "hunts small herbivores";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " breeds in secluded places.");
    }
}
