package com.nlmb.projectmanagementfrontend;

import com.nlmb.projectmanagementfrontend.utility.ConstantsUtil;
import com.nlmb.projectmanagementfrontend.utility.ViewNavigator;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewNavigator.setMainStage(primaryStage);
        ViewNavigator.switchView(ConstantsUtil.Dashboard);
    }

}
