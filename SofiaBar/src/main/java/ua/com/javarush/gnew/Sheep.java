package ua.com.javarush.gnew;

public class Sheep extends Herbivore {
    public Sheep() {
        super("SHEEP", 70, 10, 30);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "moving leisurely through the meadow";
    }

    @Override
    protected String getDefaultEatAction() {
        return "grass eater";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " breeds in a herd.");
    }
}
