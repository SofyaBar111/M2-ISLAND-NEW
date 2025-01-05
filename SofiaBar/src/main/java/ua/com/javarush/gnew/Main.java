package ua.com.javarush.gnew;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean useConsole = false;

        if (useConsole) {
            runConsoleSimulation();
        } else {
            javafx.application.Application.launch(SimulationWindow.class);
        }
    }

    private static void runConsoleSimulation() {
        Island island = new Island();
        populateIslandWithAnimals(island);

        System.out.println("Console simulation begins....");
        island.simulate();
    }

    static void populateIslandWithAnimals(Island island) {
        List.of(
                new Horse(),
                new Sheep(),
                new Rabbit(),
                new Boar(),
                new Mouse(),
                new Wolf(),
                new Panther(),
                new Bear()
        ).forEach(island::addAnimal);
    }
}