package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import weatherApplication.view.ViewFactory;

public class VBoxController extends BaseController{

    @FXML
    private VBox vBox;


    public VBoxController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }

    public VBoxController() {
    }

    @FXML
    void showCurrentAndForecastWeather(String CityName) {
        System.out.println("aaa");
        //leftAnchorPane.setVisible(true);
            /*for (int i = 0; i < 4; i++) {
                //ViewFactory viewFactory = new ViewFactory();
                Parent parent = viewFactory.loadParticularWeatherWindow();
                //Parent parent1 = (Parent) leftController.getWeatherFromClient(currentLocation, parent);
                //Parent parent = viewFactory.loadParticularWeatherWindow();
                //leftVBox.getChildren().add(parent1);
            }*/
    }

}
