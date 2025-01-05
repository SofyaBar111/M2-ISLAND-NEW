package ua.com.javarush.gnew;

public class Mouse extends Herbivore {
    public Mouse() {
        super("MOUSE", 20, 10, 10);
    }

    @Override
    protected String getDefaultMoveAction() {
        return "scurrying around looking for food";
    }

    @Override
    protected String getDefaultEatAction() {
        return "chews on seeds";
    }

    @Override
    public void reproduce() {
        System.out.println(getName() + " breeds in burrows.");
    }
}