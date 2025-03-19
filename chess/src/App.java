
import controller.Controller;
import model.Model;
import view.View;

public class App {
    public static void main(String[] args) {

        View view = new View();
        Model model = new Model();
        @SuppressWarnings("unused")
        Controller controller = new Controller(model, view);

    }
}
