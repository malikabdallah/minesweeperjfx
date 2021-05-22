package application.view;

import application.controlleur.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ViewFactory {

    private Stage stage;
    private Controller controller;

    public ViewFactory(Stage stage, Controller controller){
        this.stage = stage;
        this.controller = controller;
    }

    public void createConnexionView(){
        load(NewGameView.class.getResource("/view/connexion.fxml"), "Créer une partie.");
    }

    public void createNewGameView(){
        load(NewGameView.class.getResource("/view/newGame.fxml"), "Jouer au démineur !");
    }

    public DemineurView createDemineurView(){
        return (DemineurView)load(DemineurView.class.getResource("/view/demineur.fxml"),"Partie en cours");
    }

    private IView load(URL url, String title){
        FXMLLoader loader = new FXMLLoader(url);

        Parent root = null;

        try{
            root = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }

        IView view = loader.getController();
        view.setController(controller);

        stage.setTitle(title);
        stage.setScene(new Scene(root, 800, 450));
        stage.show();
        return view;
    }
}
