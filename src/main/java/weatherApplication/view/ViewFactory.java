package weatherApplication.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weatherApplication.controller.BaseController;
import weatherApplication.controller.MainWindowController;

import java.io.IOException;

public class ViewFactory {

    public void showWindow() {
        BaseController controller = new MainWindowController(this, "/fxml/MainWindow.fxml");
        initializeStage(controller);
    }

    public Parent passParticularWindow() throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/ParticularWeatherWindow.fxml"));
        return parent;
    }

    private void initializeStage(BaseController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Weather Application");
        stage.setResizable(false);
        stage.show();
    }
}
