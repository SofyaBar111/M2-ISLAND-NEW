package ua.com.javarush.gnew;

public abstract class Animal {
    private String name;
    private int health;
    private int speed;
    private int energy;

    public Animal(String name, int health, int speed, int energy) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void move() {
        System.out.println(getName() + " " + getDefaultMoveAction());
    }

    public void eat() {
        System.out.println(getName() + " " + getDefaultEatAction());
    }

    protected abstract String getDefaultMoveAction();

    protected abstract String getDefaultEatAction();

    public abstract void reproduce();
}