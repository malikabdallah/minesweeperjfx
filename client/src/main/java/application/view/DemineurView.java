package application.view;

import application.controlleur.Controller;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import modele.Case;

import java.util.ArrayList;


public class DemineurView implements IView, EventHandler<MouseEvent> {
    private Controller controller;
    private CustomImage[][] arrayImage;
    @FXML
    private VBox rootLayout;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void initialize(Case[][] cases){
        GridPane grid = new GridPane();
        grid.setVgap(0);
        grid.setHgap(0);
        this.arrayImage=new CustomImage[cases.length][cases[0].length];

        for(int row = 0; row < cases.length; row++){
            for(int col = 0; col < cases[row].length; col++){

                System.out.print(cases[row][col].getValeur());

                CustomImage image = new CustomImage(row, col, "/img/cellCovered.png");
                image.setFitWidth(25);
                image.setPreserveRatio(true);
                image.setSmooth(true);

                image.setOnMouseClicked(this);

                grid.add(image, col, row);
                this.arrayImage[row][col]=image;
            }
            System.out.println("");
        }

        rootLayout.getChildren().add(grid);
    }

    public void actualize(Case[][] cases){
        for(int row = 0; row < cases.length; row++) {
            for (int col = 0; col < cases[row].length; col++) {
                Image image = null;
                if (!cases[row][col].getCachee()) {
                    if (cases[row][col].getValeur() >= 0)
                        image = new Image("/img/box" + cases[row][col].getValeur() + ".png");
                    else
                        image = new Image("/img/mineExploded.png");

                    this.arrayImage[row][col].setImage(image);
                }
            }
        }
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        CustomImage view = (CustomImage) mouseEvent.getSource();
        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            this.controller.devoiler(view.getC(),view.getR(),this);
        }
        else if(mouseEvent.getButton() == MouseButton.SECONDARY){
            this.setFlag(view.getC(),view.getR());
        }
    }

    private void setFlag(int c, int r) {
        if(this.controller.getModel().getPlateau(this.controller.getNickname()).getMonPlateau()[c][r].getCachee()){
            if(!this.arrayImage[c][r].isFlag()){
                this.arrayImage[c][r].setImage(new Image("/img/flag.png"));
                this.arrayImage[c][r].setFlag(true);
            }
            else{
                this.arrayImage[c][r].setImage(new Image("/img/cellCovered.png"));
                this.arrayImage[c][r].setFlag(false);
            }
        }
    }

    public void win(Case[][] cases) {
        Label winLabel = new Label("Win");
        winLabel.setTextFill(Paint.valueOf("green"));

        rootLayout.getChildren().add(winLabel);

        showAll(cases);
        showPlayAgain();
    }

    public void lost(Case[][] cases){
        Label lostLabel = new Label("You Loose");
        lostLabel.setTextFill(Paint.valueOf("red"));

        rootLayout.getChildren().add(lostLabel);

        showAll(cases);
        showPlayAgain();
    }

    public void showAll(Case[][] cases){
        for(int row = 0; row < cases.length; row++) {
            for (int col = 0; col < cases[row].length; col++) {
                Image image = null;
                if (cases[row][col].getValeur() >= 0)
                    image = new Image("/img/box" + cases[row][col].getValeur() + ".png");
                else
                    image = new Image("/img/mine.png");

                this.arrayImage[row][col].setImage(image);
            }
        }
    }

    public void showPlayAgain(){
        Button buttonPlayAgain = new Button("Play Again ! ");

        buttonPlayAgain.setOnAction(actionEvent -> {
            this.controller.playAgain();
        });

        rootLayout.getChildren().add(buttonPlayAgain);
    }
}
