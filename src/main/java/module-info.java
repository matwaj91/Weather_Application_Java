module WeatherApplication {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires json.simple;
    requires spring.web;

    opens weatherApplication;
    opens weatherApplication.controller;
    opens weatherApplication.view;
}