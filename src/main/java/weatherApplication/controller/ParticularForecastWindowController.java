package weatherApplication.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import weatherApplication.view.ViewFactory;

public class ParticularForecastWindowController extends BaseController{

    @FXML
    private Label day;

    @FXML
    private ImageView image;

    @FXML
    private Label particularForecastLabel;

    @FXML
    private Label pressure;

    @FXML
    private Label temperature;

    @FXML
    private Label wind;

    public Label getParticularForecastLabel() {
        return particularForecastLabel;
    }

    public ParticularForecastWindowController(ViewFactory viewFactory, String fxmlName) {
        super(viewFactory, fxmlName);
    }
}
