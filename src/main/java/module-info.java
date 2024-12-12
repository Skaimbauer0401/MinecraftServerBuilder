module org.example.minecrafttest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports org.example.minecraftserverbuilder;
    exports org.example.minecraftserverbuilder.fabric;
    exports org.example.minecraftserverbuilder.paper;
    exports org.example.minecraftserverbuilder.vanilla;
    opens org.example.minecraftserverbuilder.fabric to javafx.fxml, com.google.gson;
    opens org.example.minecraftserverbuilder.paper to javafx.fxml, com.google.gson;
    opens org.example.minecraftserverbuilder.vanilla to javafx.fxml, com.google.gson;
    opens org.example.minecraftserverbuilder to com.google.gson, javafx.fxml;
    exports org.example.minecraftserverbuilder.Serververwaltung;
    opens org.example.minecraftserverbuilder.Serververwaltung to com.google.gson, javafx.fxml;

}