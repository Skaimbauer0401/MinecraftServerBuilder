package org.minecraftserverbuilder;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.*;

import java.io.*;
import java.net.URL;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ResourceBundle;

public class ServerKindChooser implements Initializable {
    @javafx.fxml.FXML
    private ImageView fabricImageView;
    @javafx.fxml.FXML
    private ImageView vanillaImageView;
    @javafx.fxml.FXML
    private ImageView paperImageView;
    @javafx.fxml.FXML
    private Label vanillaText;
    @javafx.fxml.FXML
    private Label fabricText;
    @javafx.fxml.FXML
    private Label paperText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            fileloeschen(Paths.get("server/"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new File("server").mkdir();
        new File("images").mkdir();

        Path path = Path.of("images");
        try {
            Files.setAttribute(path,"dos:hidden",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println();
        try {
            loadImage("https://external-preview.redd.it/INBHMCNdcPNCBvgGn3yQ-RH4jiAhFP4bde7-wCC2xiw.png?auto=webp&s=7fbcf884991ea6c916da84752af364fbf962687b","images/vanilla.png");
            loadImage("https://cdn.apexminecrafthosting.com/img/uploads/2022/11/10212017/Minecraft-Fabric-Logo.png","images/fabric.png");
            loadImage("https://static.nitrado.net/cdn/guides/en/images/thumb/c/cc/Papermc_logomark_500.png/300px-Papermc_logomark_500.png","images/paper.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vanillaImageView.setImage(new Image("file:images/vanilla.png"));
        fabricImageView.setImage(new Image("file:images/fabric.png"));
        paperImageView.setImage(new Image("file:images/paper.png"));

        vanillaText.setText("The standard Minecraft server\n" +
                "No modsupport\n" +
                "No pluginsupport");
        fabricText.setText("The advanced Minecraft server with unlimited possibilities\n" +
                "Modsupport\n" +
                "No pluginsupport");
        paperText.setText("Performance optimized Minecraft server\n" +
                "No modsupport\n" +
                "Pluginsupport");

    }

    @javafx.fxml.FXML
    public void vanillaChoosen(ActionEvent actionEvent) throws IOException {
        GraphicStart.serverType = ServerType.VANILLA;
        GraphicStart.switchScene("VanillaChoose.fxml");
    }

    @javafx.fxml.FXML
    public void fabricChoosen(ActionEvent actionEvent) throws IOException {
        GraphicStart.serverType = ServerType.FABRIC;
        GraphicStart.switchScene("FabricChoose.fxml");
    }

    @javafx.fxml.FXML
    public void paperChoosen(ActionEvent actionEvent) throws IOException {
        GraphicStart.serverType = ServerType.PAPER;
        GraphicStart.switchScene("PaperChoose.fxml");
    }



    public void loadImage(String urlString, String destination) throws IOException {
        URL url = new URL(urlString);

        InputStream stream = stream = url.openStream();

        FileOutputStream fos = new FileOutputStream(destination);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = stream.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        stream.close();
    }

    public void fileloeschen(Path ordner) throws IOException {
        try {
            Files.walkFileTree(ordner, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file); // Lösche Datei
                    System.out.println("Gelöscht: " + file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir); // Lösche Ordner nach Inhalt
                    System.out.println("Gelöscht: " + dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Fehler beim Löschen des Ordners: " + e.getMessage());
        }
    }
}
