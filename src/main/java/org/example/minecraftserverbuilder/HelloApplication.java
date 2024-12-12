package org.example.minecraftserverbuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    static Stage s;
    public static ServerType serverType;
    String test = "testsetsetsetsetsetsetsetse";

    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("server/eula.txt");

        if(file.exists()){

            if(new File("server/fabric.jar").exists()){
                serverType = ServerType.FABRIC;
            }else if(new File("server/paper.jar").exists()){
                serverType = ServerType.PAPER;
            }else{
                serverType = ServerType.VANILLA;
            }

            s=stage;
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Server_start.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ServerManager");
            stage.setScene(scene);
            stage.show();
        }else{
            s=stage;
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoaderVersion.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ServerBuilder");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public static void switchScene(String nameFXMLFILE) throws IOException {
        FXMLLoader l = new FXMLLoader(HelloApplication.class.getResource(nameFXMLFILE));
        Parent p = l.load();
        Scene scene = new Scene(p);
        s.setScene(scene);
        s.show();
    }
}
