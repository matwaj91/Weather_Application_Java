module WeatherApplication {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires json.simple;

    opens weatherApplication;
    opens weatherApplication.controller;
    opens weatherApplication.view;
}