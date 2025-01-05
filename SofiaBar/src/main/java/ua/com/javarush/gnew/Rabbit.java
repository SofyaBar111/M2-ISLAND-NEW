package ua.com.javarush.gnew;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super("RABBIT", 30, 20, 15);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "bouncing around the meadow";
    }

    @Override
    protected String getDefaultEatAction() {
        return "nibble on grass";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " reproduces quickly.");
    }
}