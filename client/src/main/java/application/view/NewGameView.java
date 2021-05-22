package application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import application.controlleur.Controller;
import javafx.scene.control.TextField;

public class NewGameView implements IView {

    @FXML
    private TextField sizeTextField;

    @FXML
    private Slider ptBombsSlider;

    @FXML
    private Label errorLabel;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Controller controller;

    public void easy(ActionEvent event){
        createGame(8, 10);
    }

    public void medium(ActionEvent event){
        createGame(16, 16);
    }

    public void hard(ActionEvent event){
        createGame(22, 22);
    }

    public void custom(ActionEvent event){
        Integer taille = null;

        try{
            taille = Integer.parseInt(sizeTextField.getText());
        }
        catch(Exception e) {
            errorLabel.setText("Votre taille de plateau doit être un nombre.");
            return ;
        }

        createGame(taille, (int)ptBombsSlider.getValue());
    }

    private void createGame(int size, int ptBombs){
        controller.createGame(size, ptBombs, this);
    }
}
