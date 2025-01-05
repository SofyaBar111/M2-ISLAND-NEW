package ua.com.javarush.gnew;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Island {
    private static final Logger logger = Logger.getLogger(Island.class.getName());
    private List<Animal> animals;

    public Island() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        logger.info(animal.getName() + " added to the island.");
    }

    public void simulate() {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Animal animal : animals) {
            executor.submit(() -> {
                animal.move();
                animal.eat();
                animal.reproduce();
            });
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}