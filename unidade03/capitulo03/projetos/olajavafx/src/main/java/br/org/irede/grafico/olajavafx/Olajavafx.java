package br.org.irede.grafico.olajavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Olajavafx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Button botao = new Button("Clique aqui!");
        
        StackPane layout = new StackPane();
        layout.getChildren().add(botao);
        Scene scene = new Scene(layout, 300, 200);
        
        primaryStage.setTitle("Minha primeira aplicação JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
