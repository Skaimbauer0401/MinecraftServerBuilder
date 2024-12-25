package org.minecraftserverbuilder.Serververwaltung;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import org.minecraftserverbuilder.ServerType;
import org.minecraftserverbuilder.HelloApplication;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerStartController implements Initializable {

    @javafx.fxml.FXML
    private TextField xmx;
    @javafx.fxml.FXML
    private TextField xms;
    @FXML
    private TextArea serverConsole;
    @FXML
    private TextField commandField;
    @FXML
    private Button serverStartButton;
    @FXML
    private Button serverStopButton;
    @FXML
    private Label ramInfoLabel;
    @FXML
    private TextArea modPluginList;
    @FXML
    private Label modListLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modPluginList.setStyle("-fx-control-inner-background: #404040;-fx-control-outer-background: #404040;-fx-text-fill: white;");
        commandField.setStyle("-fx-control-inner-background: #404040;-fx-control-outer-background: #404040;-fx-prompt-text-fill: grey");
        serverConsole.setStyle("-fx-control-inner-background: #404040;-fx-control-outer-background: #404040;-fx-text-fill: white;");

        serverStopButton.setVisible(false);

        if(HelloApplication.serverType== ServerType.FABRIC || HelloApplication.serverType==ServerType.PAPER){

            modListLabel.setVisible(true);
            modPluginList.setVisible(true);
            modPluginListAktualisieren();
        }else{
            modPluginList.setVisible(false);
            modListLabel.setVisible(false);
        }
    }

    @javafx.fxml.FXML
    public void serverStart(ActionEvent actionEvent) throws IOException, InterruptedException {
        int xmxInt;
        int xmsInt;

        if(xmx.getText().isEmpty()||xms.getText().isEmpty()){
            xmxInt = 2048;
            xmsInt = 1024;
        }else{
            xmxInt = Integer.parseInt(xmx.getText());
            xmsInt = Integer.parseInt(xms.getText());
        }

        serverThreadStarten(xmsInt, xmxInt);

        xmx.setVisible(false);
        xms.setVisible(false);
        ramInfoLabel.setVisible(false);
        serverStartButton.setVisible(false);
        serverStopButton.setVisible(true);
    }

    ServerThread serverThread;
    public void serverThreadStarten(int xms, int xmx) throws IOException, InterruptedException {
        serverThread = new ServerThread(xmx,xms,serverConsole,commandField);
        serverThread.start();
        System.out.println("file ausgeführt");
    }

    @FXML
    public void keypRressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            serverThread.command = commandField.getText();
            if(commandField.getText().equals("stop")){
                serverStopButton.setVisible(false);
                serverStartButton.setVisible(true);

                xmx.setVisible(true);
                xms.setVisible(true);
                ramInfoLabel.setVisible(true);
            }
            commandField.clear();
        }
    }

    @FXML
    public void serverStop(ActionEvent actionEvent) {
        serverThread.command = "stop";
        serverStartButton.setVisible(true);
        serverStopButton.setVisible(false);
        xmx.setVisible(true);
        xms.setVisible(true);
        ramInfoLabel.setVisible(true);
    }

    @FXML
    public void onDragOver(DragEvent event) {
        if(HelloApplication.serverType==ServerType.FABRIC || HelloApplication.serverType== ServerType.PAPER){
            if(event.getGestureSource() != modPluginList && event.getDragboard().hasFiles()){
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        }else {
            System.out.println("In Vanilla sind keine Mods/Plugins supportet");
        }
    }

    @FXML
    public void onDragDropped(DragEvent event) {
        if(new File("server/mods").exists()||new File("server/plugins").exists()){
            if(HelloApplication.serverType==ServerType.FABRIC){
                Dragboard dragboard = event.getDragboard();
                if(dragboard.hasFiles()){
                    File file = dragboard.getFiles().get(0);
                    try{
                        file.renameTo(new File("server/mods/"+file.getName()));
                        System.out.println("File verschoben");
                    }catch(Exception e){
                        System.out.println("Fehler beim Fileverschieben");
                    }
                }

                modPluginListAktualisieren();
            }else if(HelloApplication.serverType==ServerType.PAPER){
                Dragboard dragboard = event.getDragboard();
                if(dragboard.hasFiles()){
                    File file = dragboard.getFiles().get(0);
                    try{
                        file.renameTo(new File("server/Plugins/"+file.getName()));
                    }catch(Exception e){
                        System.out.println("Fehler beim Fileverschieben");
                    }
                }

                modPluginListAktualisieren();
            }else if(HelloApplication.serverType==ServerType.VANILLA){
                System.out.println("In Vanilla sind keine Mods/Plugins supportet");
            }
        }else{
            System.out.println("existiert nicht");
        }
    }

    public void modPluginListAktualisieren(){
        modPluginList.clear();

        File[] files = new File("server/mods/").listFiles();
        if(files!=null){
            for (int i = 0; i < files.length; i++) {
                modPluginList.appendText(files[i].getName()+"\n");
            }
        }

        modPluginList.setScrollLeft(Double.MAX_VALUE);
    }
}
