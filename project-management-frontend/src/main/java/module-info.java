module com.nlmb.projectmanagementfrontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;

    opens com.nlmb.projectmanagementfrontend to javafx.fxml;
    exports com.nlmb.projectmanagementfrontend;
    exports com.nlmb.projectmanagementfrontend.controller;
//    exports com.nlmb.projectmanagementfrontend.entity;
//    exports com.nlmb.projectmanagementfrontend.repository;
//    exports com.nlmb.projectmanagementfrontend.service;
    exports com.nlmb.projectmanagementfrontend.utility;
}