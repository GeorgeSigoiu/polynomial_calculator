import controller.Controller;
import model.Model;
import view.View;

public class Main {

    public static void main(String[] args) {
        Model theModel=new Model();
        View theView = new View();
        Controller theController=new Controller(theView,theModel);
        theView.setVisible(true);
    }
}