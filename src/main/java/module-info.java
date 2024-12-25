module org.example.minecrafttest {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports org.minecraftserverbuilder;
    exports org.minecraftserverbuilder.fabric;
    exports org.minecraftserverbuilder.paper;
    exports org.minecraftserverbuilder.vanilla;
    opens org.minecraftserverbuilder.fabric to javafx.fxml, com.google.gson;
    opens org.minecraftserverbuilder.paper to javafx.fxml, com.google.gson;
    opens org.minecraftserverbuilder.vanilla to javafx.fxml, com.google.gson;
    opens org.minecraftserverbuilder to com.google.gson, javafx.fxml;
    exports org.minecraftserverbuilder.Serververwaltung;
    opens org.minecraftserverbuilder.Serververwaltung to com.google.gson, javafx.fxml;

}