package org.minecraftserverbuilder.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.minecraftserverbuilder.GraphicStart;

import java.io.*;
import java.net.URL;
import java.util.*;

public class FabricChooseController implements Initializable {


    @FXML
    protected ComboBox<Installer> installerVersion;
    @FXML
    protected ComboBox<GameVersion> minecraftVersion;
    @FXML
    protected ComboBox<Loader> fabricVersion;
    @FXML
    private Label downloadLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        downloadLabel.setVisible(false);

        System.out.println("Versionsdaten lesen");
        try {
            URL urlMcVersions = new URL("https://meta.fabricmc.net/v2/versions");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            //Hier wurden 8 Stunden Arbeit investiert, um festzustellen, dass in module-info opens zum package vergessen wurde
            FabricRoot fabricRoot = gson.fromJson(new InputStreamReader(urlMcVersions.openStream()), FabricRoot.class);

            System.out.println("versionsdaten gelesen");

            minecraftVersion.getItems().addAll(fabricRoot.game);
            installerVersion.getItems().addAll(fabricRoot.installer);
            fabricVersion.getItems().addAll(fabricRoot.loader);

            System.out.println("versionsdaten angezeigt \n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }


    @FXML
    public void readyPressed(ActionEvent actionEvent) throws IOException, InterruptedException {
        if(minecraftVersion.getSelectionModel().getSelectedItem()!=null||installerVersion.getSelectionModel().getSelectedItem()!=null||fabricVersion.getSelectionModel().getSelectedItem()!=null){

            new FabricDownloadThread(this).start();

            downloadLabel.setVisible(true);
        }else{
            System.out.println("Bitte Versionen auswählen");
        }
    }

    public void nextScene() throws IOException {
        GraphicStart.switchScene("Server_start.fxml");
    }
}