package ua.com.javarush.gnew;

public class Boar extends Herbivore {
    public Boar() {
        super("BOAR", 120, 15, 40);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "through the woods";
    }

    @Override
    protected String getDefaultEatAction() {
        return "digging for roots";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + "breeds in forests.");
    }
}