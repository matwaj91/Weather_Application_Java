package weatherApplication.controller;

import weatherApplication.view.ViewFactory;

public abstract class BaseController {

    protected ViewFactory viewFactory;
    private String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    protected BaseController() {
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
