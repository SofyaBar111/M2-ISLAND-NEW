package ua.com.javarush.gnew;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

public class AnimalRenderer {

    private static final int DEFAULT_ANIMAL_IMAGE_SIZE = 30;
    private final Map<String, String> animalImagePaths;

    public AnimalRenderer() {
        animalImagePaths = new HashMap<>();
        initializeAnimalImages();
    }

    private void initializeAnimalImages() {
        animalImagePaths.put("BEAR", "images/predator/Bear.png");
        animalImagePaths.put("WOLF", "images/predator/Wolf.png");
        animalImagePaths.put("MOUSE", "images/herbivore/Mouse.png");
        animalImagePaths.put("BOAR", "images/herbivore/Boar.png");
        animalImagePaths.put("HORSE", "images/herbivore/Horse.png");
        animalImagePaths.put("RABBIT", "images/herbivore/Rabbit.png");
        animalImagePaths.put("SHEEP", "images/herbivore/Sheep.png");
        animalImagePaths.put("PANTHER", "images/predator/Panther.png");
    }

    public StackPane renderAnimal(Animal animal) {
        return renderAnimal(animal, DEFAULT_ANIMAL_IMAGE_SIZE, DEFAULT_ANIMAL_IMAGE_SIZE);
    }

    public StackPane renderAnimal(Animal animal, int width, int height) {
        String imagePath = animalImagePaths.get(animal.getName());
        if (imagePath == null) {
            throw new IllegalArgumentException("Image not found for animal: " + animal.getName());
        }

        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        StackPane animalPane = new StackPane(imageView);
        return animalPane;
    }
}