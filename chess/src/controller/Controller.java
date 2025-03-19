package controller;

import java.util.ArrayList;

import model.Model;
import view.View;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        model.setController(this);
        this.view = view;
        view.setController(this);
    }

    public void showMovements(int x, int y) {
        model.showMovements(x, y);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void showMovements(ArrayList movements) {
        view.showMovements(movements);
    }

    public void moveInModel(int x, int y) {
        model.moveInModel(x, y);
    }

    public void moveInView(int[] movement){
        view.moveInView(movement);
    }

    public void updateView() {

    }

}
