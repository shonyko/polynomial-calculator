import controllers.Controller;
import models.viewModels.AppStateModel;
import views.View;

public class Main {
    public static void main(String[] args) {
        var model       = new AppStateModel();
        var view        = new View(model);
        var controller  = new Controller(model, view);
        System.out.println("Hello World");
    }
}
