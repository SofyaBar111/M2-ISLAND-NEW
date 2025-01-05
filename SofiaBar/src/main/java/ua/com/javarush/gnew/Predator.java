package ua.com.javarush.gnew;

public abstract class Predator extends Animal {
    public Predator(String name, int health, int speed, int energy) {
        super(name, health, speed, energy);
    }

    public void restoreEnergy() {
        setEnergy(getEnergy() + 20);
        System.out.println(getName() + " restored power!");
    }

    @Override
    protected String getDefaultMoveAction() {
        return "hunting and travelling";
    }

    @Override
    protected String getDefaultEatAction() {
        return "eats the herbivore";
    }
}