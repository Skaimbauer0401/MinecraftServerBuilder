package org.minecraftserverbuilder.vanilla;

import javafx.application.Platform;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class VanillaDownloadThread extends Thread {
    VanillaChooseController controller;

    public VanillaDownloadThread(VanillaChooseController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        super.run();
        controller.downloadLabel.setVisible(true);

        try {
            URL url = new URL(controller.vanillaVersion.getSelectionModel().getSelectedItem().getUrl()+"/download");

            InputStream stream = stream = url.openStream();

            System.out.println(".jar Download started");
            FileOutputStream fos = new FileOutputStream("server/vanilla.jar");

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
