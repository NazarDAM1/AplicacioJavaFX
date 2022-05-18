package Concessionari_Vehicles;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ConcessionariJavaFX extends Application {

    Label lblMissatge = new Label();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenari) throws Exception {
        // aqui escriue el codi de la nostra interficie grafica
        // que es compon: Stage (escenari), Scene (escena)
        // en lescena tenim els actors, nodes o components

        // contenidors de nodes

        // recomanable protocol de enomenar els elements
        Label lblNom = new Label("Nom: ");
        lblNom.setTranslateX(20);
        lblNom.setTranslateY(20);
        TextField txtNom = new TextField();
        txtNom.relocate(60, 20);

        Label lblCognom = new Label("Cognom: ");
        lblCognom.relocate(10, 60);
        TextField txtCognom = new TextField();
        txtCognom.relocate(60, 60);


        Button btnNou = new Button("Nou");
        btnNou.relocate(20, 100);
        btnNou.setMinWidth(70);

        
        lblMissatge.relocate(20, 130);

        btnNou.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                lblMissatge.setText(txtNom.getText() + " " + txtCognom.getText());
                
            }
            
        });


        Button btnGuardar = new Button("Guardar");
        btnGuardar.relocate(120, 100);
        btnGuardar.setMinWidth(70);
        btnGuardar.setOnAction(e -> lblMissatge.setText("Has cliat al boto Guardar"));


        Button btnCancelar = new Button("Finestra Nova");
        btnCancelar.relocate(200, 100);

        btnCancelar.setOnAction(e -> obrirFinestra());
        btnCancelar.setMinWidth(90);
        // creem el contenidor

        Pane pane = new Pane();

        pane.getChildren().addAll(lblNom, txtNom, lblCognom, txtCognom,btnNou,btnGuardar,btnCancelar,lblMissatge);

        escenari.setTitle("la meva aplicacio");
       

        // creem la escena li pasem al constructor el contenidor

        Scene escena = new Scene(pane);
        // escenecri no cal crear, arriba com a parametre

        escenari.setScene(escena);
        escenari.setMinHeight(600);
        escenari.setMinWidth(300);
        escenari.show();
    }

    private void obrirFinestra() {

        Stage nouEscenari = new Stage();

        nouEscenari.show();
        // return nouEscenari;
        
    }

    private Object cancelar() {
        lblMissatge.setText("Has cliat al boto Cancelar");
        return lblMissatge;
    }

}