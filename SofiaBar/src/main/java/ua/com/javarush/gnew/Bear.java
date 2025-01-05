package ua.com.javarush.gnew;

public class Bear extends Predator {
    public Bear() {
        super("BEAR", 150, 15, 60);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "walks slowly through the woods";
    }

    @Override
    protected String getDefaultEatAction() {
        return "eats fish and herbivores";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " breeds infrequently.");
    }
}