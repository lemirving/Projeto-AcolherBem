module com.project.project_healtheducation {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires jbcrypt;
    requires com.jfoenix;

    opens com.project.project_healtheducation to javafx.fxml;
    exports com.project.project_healtheducation;
    exports com.project.project_healtheducation.controllers;
    opens com.project.project_healtheducation.controllers to javafx.fxml;
    exports com.project.project_healtheducation.utils;
    opens com.project.project_healtheducation.utils to javafx.fxml;
    exports com.project.project_healtheducation.controllers.home;
    opens com.project.project_healtheducation.controllers.home to javafx.fxml;
}