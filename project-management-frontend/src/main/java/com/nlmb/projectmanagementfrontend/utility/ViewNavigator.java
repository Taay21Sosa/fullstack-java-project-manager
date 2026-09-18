package com.nlmb.projectmanagementfrontend.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class ViewNavigator {
    private static Stage mainStage;

    @Getter
    @Setter
    private static String currentView;
    private static Object currentController;
    private static boolean justLoggedIn = false;

    // Stacks for navigation history

    //
    public static void setMainStage(Stage stage) {
        mainStage = stage;

        // Check if style is already set to avoid errors on reload
        if (mainStage.getStyle() != StageStyle.TRANSPARENT) {
            mainStage.initStyle(StageStyle.TRANSPARENT);
        }
    }

    //
    public static void switchView(String viewPath) {
        if (currentView != null) {

        }

        renderView(viewPath);
    }

    //
    public static void renderView(String viewPath) {
        try {
            boolean wasMaximized = mainStage.isMaximized();

            FXMLLoader fxmlLoader = new FXMLLoader(ViewNavigator.class.getResource(viewPath));
            Parent root = fxmlLoader.load();

            setCurrentView(viewPath);
            currentController = fxmlLoader.getController();

            Scene scene = new Scene(root, Color.TRANSPARENT);
            scene.getStylesheets().add(String.valueOf(ViewNavigator.class.getResource("/css/style.css")));


            mainStage.setScene(scene);
            mainStage.centerOnScreen();
            mainStage.show();

        } catch (IOException e) {
            System.out.println("renderView Error: Occured while rendering system view." + e.getMessage());
            e.printStackTrace();
        }
    }
}
