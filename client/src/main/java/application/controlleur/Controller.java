package application.controlleur;


        import application.view.ConnexionView;
        import application.view.DemineurView;
        import application.view.NewGameView;
        import javafx.stage.Stage;
        import modele.GestionDemineur;
        import modele.GestionDemineurInterface;
        import application.view.ViewFactory;
        import modele.exceptions.BombeException;
        import modele.exceptions.ExceptionLoginDejaPris;

        import java.util.ArrayList;

public class Controller {

    public GestionDemineurInterface getModel() {
        return model;
    }

    private GestionDemineurInterface model;
    private ViewFactory viewFactory;

    public String getNickname() {
        return nickname;
    }

    private String nickname;

    public Controller(Stage stage){
        this.model = new GestionDemineur();

        this.viewFactory = new ViewFactory(stage, this);

        this.viewFactory.createConnexionView();
    }

    public boolean check(String nickname,ArrayList<String> errorMsgs){
        if(!nickname.matches("[a-zA-Z0-9]*")  ){
            errorMsgs.add("Votre identifiant doit être composé de charactères alphanumérique.");
        }
        if(nickname.length() < 3){
            errorMsgs.add("Votre identifiant doit être composé de 3 charactères minimum.");
        }
        if(nickname.length() > 12){
            errorMsgs.add("Votre identifiant doit être composé de 12 charactères maximum.");
        }
        if(errorMsgs.size()==0){
            return true;
        }
        return false;
    }

    public void connect(String nickname, ConnexionView view){
        this.nickname = nickname;

        ArrayList<String> errorMsgs = new ArrayList<String>();

        if(check(nickname,errorMsgs)){}

        if(!errorMsgs.isEmpty()){
            view.error(errorMsgs);
            return;
        }
        try {
            this.model.connexion(nickname);

            this.viewFactory.createNewGameView();
        }
        catch (ExceptionLoginDejaPris exceptionLoginDejaPris) {
            errorMsgs.add("Cet identifiant est déjà utilisé.");
            view.error(errorMsgs);
            return ;
        }

    }

    public void createGame(int size, int ptBomb, NewGameView newGameView){
        this.model.associerNouvelleGrille(this.nickname, size, ptBomb);

        DemineurView view = this.viewFactory.createDemineurView();
        view.initialize(this.model.getPlateau(this.nickname).getMonPlateau());
    }

    public void devoiler(int row, int col, DemineurView view){
        try{
            if(model.decouvrir(this.nickname, row, col)){
                view.win(model.getPlateau(this.nickname).getMonPlateau());
            }
        }
        catch(BombeException e){
            view.lost(model.getPlateau(this.nickname).getMonPlateau());
        }
        view.actualize(model.getPlateau(this.nickname).getMonPlateau());
    }


    public void playAgain(){
        this.viewFactory.createNewGameView();
    }
}