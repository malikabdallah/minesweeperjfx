package application.view;

import application.controlleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class ConnexionView implements IView {

    @FXML
    private Button connexionButton;

    @FXML
    private TextField nicknameTextField;

    @FXML
    private Label errorLabel;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Controller controller;


    public void connect(ActionEvent event){
        controller.connect(nicknameTextField.getText(), this);
    }

    public void error(ArrayList<String> errorMsgs){
        String errorMsg = "";

        for(String msg : errorMsgs){
            errorMsg += msg+"\n";
        }

        errorLabel.setText(errorMsg);
    }

    public void connectKey(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            controller.connect(nicknameTextField.getText(), this);
            return;
        }
    }

    public void connect() {

    }
}
