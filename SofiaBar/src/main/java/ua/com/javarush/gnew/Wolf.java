package ua.com.javarush.gnew;

public class Wolf extends Predator {
    public Wolf() {
        super("WOLF", 80, 20, 40);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "creeping through the forest";
    }

    @Override
    protected String getDefaultEatAction() {
        return "hunts herbivores";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " Reproduction in a flock");
    }
}
