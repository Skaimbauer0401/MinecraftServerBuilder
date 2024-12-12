package org.example.minecraftserverbuilder.Serververwaltung;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;

public class ServerThread extends Thread {
    int xmx;
    int xms;
    TextArea serverConsole;
    TextField commands;

    public ServerThread(int xmx, int xms, TextArea serverConsole, TextField commands) {
        this.xmx = xmx;
        this.xms = xms;
        this.serverConsole = serverConsole;
        this.commands = commands;
    }


    private ProcessBuilder pb;
    private Process process;

    private BufferedWriter writer;
    protected String command;

    @Override
    public void run() {
        super.run();
        File fil = new File("server/");
        File[] files = fil.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".jar")) {
                pb = new ProcessBuilder("java","-Xmx"+xmx+"M","-Xms"+xms+"M", "-jar", files[i].getName(), "nogui");
                pb.directory(files[i].getParentFile());
                System.out.println(files[i].getName());
                break;
            }
        }

        pb.redirectInput();

        try {
            process = pb.start();
            //Streams für outputs und inputs starten
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            while (process.isAlive()) {
                //server output in textArea schreiben
                if (reader.ready()) {
                    String line = reader.readLine();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            serverConsole.appendText(line+"\n");
                        }
                    });
                }

                //commands lesen und an die TextArea und den Server weitergeben
                if(command !=null){
                    System.out.println(command);
                    writer.write(command+"\n");
                    writer.flush();
                    String tempcommand = command;
                    command = null;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            serverConsole.appendText(tempcommand+"\n");
                        }
                    });
                }
            }

            //wenn nach dem Run die Eula false ist dann wird sie akzeptiert und neu gerunnt
            BufferedReader isEulaAccepted = new BufferedReader(new FileReader("server/eula.txt"));
            isEulaAccepted.readLine();
            isEulaAccepted.readLine();
            if(isEulaAccepted.readLine().contains("false")){
                isEulaAccepted.close();
                eulaAccept();
                run();
            }else {
                isEulaAccepted.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void eulaAccept() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("server/eula.txt"));
        String temp = br.readLine()+"\n"+br.readLine()+"\n";
        String importantLine = br.readLine();
        br.close();
        if(importantLine.contains("false")){
            BufferedWriter bw = new BufferedWriter(new FileWriter("server/eula.txt"));
            bw.write(temp);
            bw.write("eula=true");
            bw.close();
            System.out.println("Eula akzeptiert");
        }
    }
}
