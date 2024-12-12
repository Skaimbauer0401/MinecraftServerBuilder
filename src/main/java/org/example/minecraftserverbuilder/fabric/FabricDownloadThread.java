package org.example.minecraftserverbuilder.fabric;

import javafx.application.Platform;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FabricDownloadThread extends Thread {
    FabricChooseController controller;

    public FabricDownloadThread(FabricChooseController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        super.run();
        try {
            URL url = new URL("https://meta.fabricmc.net/v2/versions/loader/"+controller.minecraftVersion.getSelectionModel().getSelectedItem().version+"/"+ controller.fabricVersion.getSelectionModel().getSelectedItem().version+"/"+controller.installerVersion.getSelectionModel().getSelectedItem().version+"/server/jar");
            InputStream stream = stream = url.openStream();

            System.out.println(".jar Download started");
            FileOutputStream fos = new FileOutputStream("server/fabric.jar");

            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            stream.close();
            System.out.println(".jar Download complete"+"\n");

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        controller.nextScene();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
