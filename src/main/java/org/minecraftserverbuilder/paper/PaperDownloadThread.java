package org.minecraftserverbuilder.paper;

import javafx.application.Platform;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PaperDownloadThread extends Thread {
    PaperChooseController controller;

    public PaperDownloadThread(PaperChooseController controller) {
        this.controller = controller;
    }


    @Override
    public void run() {
        super.run();

        try {
            URL url = new URL(controller.paperVersion.getSelectionModel().getSelectedItem().getUrl()+"/download");
            InputStream stream = stream = url.openStream();

            System.out.println(".jar Download started");
            FileOutputStream fos = new FileOutputStream("server/paper.jar");

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

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
