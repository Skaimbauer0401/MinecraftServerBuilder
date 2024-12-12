package org.example.minecraftserverbuilder.paper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.example.minecraftserverbuilder.HelloApplication;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PaperChooseController implements Initializable {

    @javafx.fxml.FXML
    protected ComboBox<GameVersion> paperVersion;
    @javafx.fxml.FXML
    private Label downloadLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        downloadLabel.setVisible(false);

        System.out.println("Versionsdaten lesen");
        try {
            URL urlMcVersions = new URL("https://mcutils.com/api/server-jars/paper");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Type listType = new TypeToken<List<GameVersion>>(){}.getType();

            List games = gson.fromJson(new InputStreamReader(urlMcVersions.openStream()), listType);

            System.out.println("versionsdaten gelesen");

            paperVersion.getItems().addAll(games);

            System.out.println("versionsdaten angezeigt \n");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void fertigPressed(ActionEvent actionEvent) throws IOException, InterruptedException {
        if(paperVersion.getSelectionModel().getSelectedItem() != null){

            new PaperDownloadThread(this).start();

            downloadLabel.setVisible(true);
        }else{
            System.out.println("Bitte Version wählen");
        }
    }

    public void nextScene() throws IOException {
        HelloApplication.switchScene("Server_start.fxml");
    }
}
