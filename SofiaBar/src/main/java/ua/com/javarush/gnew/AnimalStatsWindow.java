package ua.com.javarush.gnew;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AnimalStatsWindow {
    private final Label liveAnimalsLabel = new Label();
    private final Label deadAnimalsLabel = new Label();

    public void show() {
        Stage statsStage = new Stage();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Animal Statistics:"),
                liveAnimalsLabel,
                deadAnimalsLabel
        );

        Scene scene = new Scene(layout, 200, 100);
        statsStage.setScene(scene);
        statsStage.setTitle("Animal Statistics");
        statsStage.show();
    }

    public void updateStats(int liveAnimals, int deadAnimals) {
        liveAnimalsLabel.setText("Alive: " + liveAnimals);
        deadAnimalsLabel.setText("Dead: " + deadAnimals);
    }
}