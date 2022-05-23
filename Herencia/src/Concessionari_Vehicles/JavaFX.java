package Concessionari_Vehicles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.CallableStatement;

import java.sql.Date;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX extends Application {

    private static ConnexioBD conn = new ConnexioBD();

    static Connection connexioBD;

    static ArrayList<Client> Clients = new ArrayList<>();
    static ArrayList<CursIndividual> ci = new ArrayList<>();
    static ArrayList<CursColectiu> cc = new ArrayList<>();
    static ArrayList<CursCompeticio> Ccompeticio = new ArrayList<>();
    Button btnReservar;
    Label lblNom;
    Label lblCognom;
    Label lblDni;
    Label lblNumFamilia;
    Label lblNivell;
    Label lblDataFederat;
    Label lblDataFamiliar;

    TextField txtNom;
    TextField txtCognom;
    TextField txtDni;
    TextField txtNumFamilia;
    TextField txtNivell;
    TextField txtDataFederat;
    TextField txtDataFamiliar;

    Label lblNomCurs;
    Label lblIdCurs;
    Label lblPreuCurs;
    Label lblPreuFinalCurs;

    TextField txtNomCurs;
    TextField txtIdCurs;
    TextField txtPreuCurs;
    TextField txtPreuFinalCurs;

    Tab tab1;
    Tab tab2;
    Tab tab3;

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        conn.connexio();

        consultarCursosCompeticio();
        consultarCursosIndividual();
        consultarCursosColectiu();
        consultarClient();
        launch();

    }

    /**
     * @param escenari
     * @throws Exception
     */
    @Override
    public void start(Stage escenari) throws Exception {
        // Fem servir un contenidor BorderPane per la nostra aplicació
        // el contingut de cada regió el dibuixem en els diferents mètodes

        BorderPane contenidor = new BorderPane();

        contenidor.setTop(partSuperior());
        contenidor.setBottom(partInferior());
        contenidor.setLeft(lateralEsquerre());
        contenidor.setCenter(formulariCentral());
        contenidor.setRight(lateralDret());

        // VBox contenidor = new VBox(); //organitza els nodes en vertical
        // HBox contenidor = new HBox(); //organitza els nodes en horitzontal
        // Pane contenidor = new Pane(); //coloca els nodes al cantó superior esquerra
        // StackPane contenidor = new StackPane(); //apila els nodes un al damunt de
        // l'altre
        // FlowPane contenidor = new FlowPane();

        // contenidor.getChildren().addAll(btn1,btn2,btn3);
        // contenidor.setAlignment(Pos.CENTER);
        // contenidor.setSpacing(20);

        Scene escena = new Scene(contenidor);

        escenari.setScene(escena);
        escenari.setMinHeight(300);
        escenari.setMinWidth(300);

        escenari.show();
    }

    /**
     * @return Pane
     */
    private Pane partSuperior() {
        HBox hb = new HBox();
        hb.getChildren().addAll(new Label("Titol"));
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    /**
     * @return Pane
     * @throws SQLException
     */
    private Pane lateralEsquerre() throws SQLException {
        VBox vlateral = new VBox();

        vlateral.setAlignment(Pos.CENTER);

        TableView<Client> tblClients = new TableView<>();
        TableColumn<Client, String> colDni = new TableColumn<>("DNI");
        TableColumn<Client, String> colNom = new TableColumn<>("Nom");
        TableColumn<Client, String> colCognom = new TableColumn<>("Cognom");
        // TableColumn<Client, String> colEmail = new TableColumn<>("Email");
        // TableColumn<Client, int> coltelefon = new TableColumn<>("Telefon");

        tblClients.getColumns().addAll(colDni, colNom, colCognom);
        vlateral.getChildren().add(tblClients);

        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));

        for (Client cl : Clients) {

            tblClients.getItems().add(cl);
        }

        vlateral.getChildren().addAll(new Label("CLIENTS"));

        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Client client = (Client) newValue;

                if (client != null) {
                    txtDni.setText(client.getDni());
                    txtNom.setText(client.getNom());
                    txtCognom.setText(client.getCognom());
                    txtNumFamilia.setText(client.getnumFamilia());
                    txtNivell.setText(String.valueOf(client.getNivell()));
                    txtDataFederat.setText(String.valueOf(client.getDataFederat()));
                    txtDataFamiliar.setText(String.valueOf(client.getDataFamiliar()));

                    if (tab1.isSelected())
                        calcularPreuFinalColectiu();
                }
            }
        });
        return vlateral;
    }

    /**
     * @return VBox
     */
    private VBox formulariCentral() {
        VBox global = new VBox();
        global.setAlignment(Pos.CENTER);
        VBox vlateral = new VBox();
        vlateral.getChildren().addAll(new Label("Clients"));

        vlateral.setAlignment(Pos.CENTER);
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);

        lblNom = new Label("Nom");
        lblCognom = new Label("Cognom");
        lblDni = new Label("Dni");
        lblNumFamilia = new Label("NumFamilia");
        lblNivell = new Label("Nivell");
        lblDataFederat = new Label("DataFederat");
        lblDataFamiliar = new Label("DataFamiliar");

        txtNom = new TextField();
        txtNom.setDisable(true);
        txtCognom = new TextField();
        txtCognom.setDisable(true);
        txtDni = new TextField();
        txtDni.setDisable(true);
        txtNumFamilia = new TextField();
        txtNumFamilia.setDisable(true);
        txtNivell = new TextField();
        txtNivell.setDisable(true);
        txtDataFederat = new TextField();
        txtDataFederat.setDisable(true);
        txtDataFamiliar = new TextField();
        txtDataFamiliar.setDisable(true);

        gp.add(lblNom, 0, 0);
        gp.add(txtNom, 1, 0);
        gp.add(lblCognom, 0, 1);
        gp.add(txtCognom, 1, 1);
        gp.add(lblDni, 0, 2);
        gp.add(txtDni, 1, 2);

        gp.add(lblNumFamilia, 0, 3);
        gp.add(txtNumFamilia, 1, 3);
        gp.add(lblNivell, 0, 4);
        gp.add(txtNivell, 1, 4);
        gp.add(lblDataFederat, 0, 5);
        gp.add(txtDataFederat, 1, 5);
        gp.add(lblDataFamiliar, 0, 6);
        gp.add(txtDataFamiliar, 1, 6);

        vlateral.getChildren().add(gp);

        VBox vlateral2 = new VBox();
        vlateral2.getChildren().addAll(new Label("Cursos"));

        vlateral2.setAlignment(Pos.CENTER);
        GridPane gp2 = new GridPane();
        gp2.setAlignment(Pos.CENTER);

        lblNomCurs = new Label("Nom");
        lblIdCurs = new Label("ID");
        lblPreuCurs = new Label("PreuCurs");
        lblPreuFinalCurs = new Label("PreuFinalCurs");

        txtNomCurs = new TextField();
        txtNomCurs.setDisable(true);
        txtIdCurs = new TextField();
        txtIdCurs.setDisable(true);
        txtPreuCurs = new TextField();
        txtPreuCurs.setDisable(true);
        txtPreuFinalCurs = new TextField();
        txtPreuFinalCurs.setDisable(true);

        gp2.add(lblNomCurs, 0, 1);
        gp2.add(txtNomCurs, 1, 1);
        gp2.add(lblIdCurs, 0, 2);
        gp2.add(txtIdCurs, 1, 2);
        gp2.add(lblPreuCurs, 0, 3);
        gp2.add(txtPreuCurs, 1, 3);
        gp2.add(lblPreuFinalCurs, 0, 4);
        gp2.add(txtPreuFinalCurs, 1, 4);

        // return gp;

        vlateral2.getChildren().add(gp2);
        global.getChildren().add(vlateral);
        global.getChildren().add(vlateral2);
        return global;

    }

    /**
     * @return Pane
     */
    // private GridPane formulariCentral2() {
    // GridPane gp = new GridPane();
    // gp.setAlignment(Pos.CENTER);

    // lblNom = new Label("Nom");
    // lblCognom = new Label("Cognom");
    // lblDni = new Label("Dni");
    // txtNom = new TextField();
    // txtCognom = new TextField();
    // txtDni = new TextField();

    // gp.add(lblNom, 0, 0);
    // gp.add(txtNom, 1, 0);
    // gp.add(lblCognom, 0, 1);
    // gp.add(txtCognom, 1, 1);
    // gp.add(lblDni, 0, 2);
    // gp.add(txtDni, 1, 2);
    // return gp;
    // }

    private Pane partInferior() {
        Button btnNeteijar = new Button("Neteijar");

        btnNeteijar.setOnAction(e -> neteijarFormulari());

        btnReservar = new Button("Reservar");

        btnReservar.setOnAction(e -> mostrarAlertWarning(e));

        Button btn3 = new Button("Boto 3");
        HBox hinferior = new HBox();
        hinferior.getChildren().addAll(btnNeteijar, btnReservar, btn3);

        hinferior.setAlignment(Pos.CENTER);
        return hinferior;
    }

    /**
     * @return Object
     */
    // private Pane lateralDret() {
    // VBox vb = new VBox();
    // Label lblDret = new Label("Lateral dret");
    // vb.setAlignment(Pos.CENTER);
    // vb.getChildren().addAll(lblDret);
    // return vb;
    // }

    private Object neteijarFormulari() {
        txtCognom.setText("");

        txtNom.setText("");
        txtCognom.setText("");
        txtDni.setText("");
        txtNumFamilia.setText("");
        txtNivell.setText("");
        txtDataFederat.setText("");
        txtDataFamiliar.setText("");
        txtPreuCurs.setText("");
        txtPreuFinalCurs.setText("");
        txtNomCurs.setText("");
        txtIdCurs.setText("");

        btnReservar.setOnAction(e -> mostrarAlertWarning(e));
        return lblCognom;
    }

    /**
     * @return Pane
     */
    private Pane lateralDret() {

        VBox vb = new VBox();

        vb.setPadding(new Insets(10.0));
        vb.setSpacing(10.0);
        vb.setAlignment(Pos.CENTER);

        TabPane tp = new TabPane();

        tab1 = new Tab("Colectius", cursosColectius());
        tab2 = new Tab("Competicio", cursosCometicio());
        tab3 = new Tab("Individual", cursosIndividuals());

        tp.getTabs().add(tab1);
        tp.getTabs().add(tab2);
        tp.getTabs().add(tab3);

        vb.getChildren().addAll(tp);
        return vb;
    }

    /**
     * @return Pane
     */
    private Pane cursosColectius() {

        VBox vlateral = new VBox();
        vlateral.getChildren().addAll(new Label("CURSOS"));
        vlateral.setAlignment(Pos.CENTER);

        TableView<CursColectiu> tblClients = new TableView<>();

        TableColumn<CursColectiu, String> colNom = new TableColumn<>("NOM");
        TableColumn<CursColectiu, String> colDia = new TableColumn<>("data");
        TableColumn<CursColectiu, String> colPreuFinal = new TableColumn<>("Preu Final");

        tblClients.getColumns().addAll(colNom, colDia, colPreuFinal);
        vlateral.getChildren().add(tblClients);

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDia.setCellValueFactory(new PropertyValueFactory<>("data"));
        colPreuFinal.setCellValueFactory(new PropertyValueFactory<>("preuFinal"));

        for (CursColectiu CC : cc) {

            tblClients.getItems().add(CC);
        }

        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursColectiu CI = (CursColectiu) newValue;

                if (CI != null) {
                    txtNomCurs.setText(String.valueOf(CI.getNom()));
                    txtIdCurs.setText(String.valueOf(CI.getId()));
                    txtPreuCurs.setText(String.valueOf(CI.getPreuFinal()));
                    calcularPreuFinalColectiu();
                }
            }
        });
        return vlateral;
    }

    /**
     * @return Pane
     */
    private Pane cursosCometicio() {
        VBox vlateral = new VBox();
        vlateral.getChildren().addAll(new Label("CLIENTS"));
        vlateral.setAlignment(Pos.CENTER);

        TableView<CursCompeticio> tblClients = new TableView<>();
        // TableColumn<CursCompeticio, String> colId = new TableColumn<>("ID");
        TableColumn<CursCompeticio, String> colNom = new TableColumn<>("NOM");
        TableColumn<CursCompeticio, String> colNivell = new TableColumn<>("NIVELL");
        TableColumn<CursCompeticio, String> colDatainici = new TableColumn<>("Data_inici");
        TableColumn<CursCompeticio, String> colDataFi = new TableColumn<>("Data_Fi");
        TableColumn<CursCompeticio, String> colPreu = new TableColumn<>("Preu");
        // TableColumn<Client, String> colEmail = new TableColumn<>("Email");
        // TableColumn<Client, int> coltelefon = new TableColumn<>("Telefon");

        tblClients.getColumns().addAll(colNom, colNivell, colDatainici, colDataFi, colPreu);
        vlateral.getChildren().add(tblClients);

        // colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNivell.setCellValueFactory(new PropertyValueFactory<>("nivell"));
        colDatainici.setCellValueFactory(new PropertyValueFactory<>("dataInici"));
        colDataFi.setCellValueFactory(new PropertyValueFactory<>("dataFi"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));

        for (CursCompeticio CC : Ccompeticio) {
            tblClients.getItems().add(CC);
        }

        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursCompeticio CI = (CursCompeticio) newValue;

                if (CI != null) {
                    txtNomCurs.setText(String.valueOf(CI.getNom()));
                    txtIdCurs.setText(String.valueOf(CI.getId()));

                }
            }
        });
        return vlateral;
    }

    /**
     * @return Pane
     */
    private Pane cursosIndividuals() {
        VBox vlateral = new VBox();
        vlateral.getChildren().addAll(new Label("CLIENTS"));
        vlateral.setAlignment(Pos.CENTER);

        TableView<CursIndividual> tblClients = new TableView<>();
        TableColumn<CursIndividual, String> colNom = new TableColumn<>("NOM");
        TableColumn<CursIndividual, String> colPreuHora = new TableColumn<>("Preu Hora");

        // TableColumn<Client, String> colEmail = new TableColumn<>("Email");
        // TableColumn<Client, int> coltelefon = new TableColumn<>("Telefon");

        tblClients.getColumns().addAll(colNom, colPreuHora);
        vlateral.getChildren().add(tblClients);

        // colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        colPreuHora.setCellValueFactory(new PropertyValueFactory<>("preuHora"));

        for (CursIndividual CC : ci) {
            tblClients.getItems().add(CC);
        }

        tblClients.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CursIndividual CI = (CursIndividual) newValue;

                if (CI != null) {
                    txtNomCurs.setText(String.valueOf(CI.getNom()));
                    txtIdCurs.setText(String.valueOf(CI.getId()));
                }
            }
        });

        return vlateral;

    }

    /**
     * @throws SQLException
     */
    private static void consultarCursosCompeticio() throws SQLException {

        connexioBD = conn.getConnexioBD();

        String SQL = "select * from curs,curs_competicio where curs.id = curs_competicio.id;";

        PreparedStatement ps = connexioBD.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Ccompeticio.add(new CursCompeticio(rs.getInt("id"), rs.getString("nom"), rs.getString("dni_monitor"),
                    rs.getInt("nivell"), rs.getDate("data_inici").toLocalDate(), rs.getDate("data_fi").toLocalDate(),
                    rs.getInt("preu")));
        }
    }

    /**
     * @throws SQLException
     */
    private static void consultarCursosIndividual() throws SQLException {

        connexioBD = conn.getConnexioBD();

        String SQL = "select * from curs,curs_individual where curs.id = curs_individual.id;";

        PreparedStatement ps = connexioBD.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ci.add(new CursIndividual(rs.getInt("id"), rs.getString("nom"), rs.getString("dni_monitor"),
                    rs.getInt("preu_hora")));
        }
    }

    private void calcularPreuFinalColectiu() {

        int preuCursFinal = 0;

        if (txtPreuCurs.getText() != "" && txtDni.getText() != "") {
            preuCursFinal = Integer.parseInt(txtPreuCurs.getText());
        }

        if (txtDataFamiliar.getText().equals("null") != true) {
            preuCursFinal = (int) (preuCursFinal * 0.60);

        }

        txtPreuFinalCurs.setText(Integer.toString(preuCursFinal));

        if (!txtDni.getText().equals("") && !txtIdCurs.getText().equals("")) {
            btnReservar.setOnAction(e -> reservarCurs());
        } else {
            btnReservar.setOnAction(e -> mostrarAlertWarning(e));
        }

    }

    private void reservarCurs() {

        int descompte = 0;
        int idCurs = 0;

        if (txtDataFamiliar.getText().equals("null") != true) {
            descompte = 40;
        }

        int preuCursFinal = Integer.parseInt(txtPreuFinalCurs.getText());

        int j = 0;
        if (!txtIdCurs.getText().equals("")) {
            idCurs = Integer.parseInt(txtIdCurs.getText());
        }

        String dni = txtDni.getText();
        String tipoCurs = "colecitu";

        connexioBD = conn.getConnexioBD();
        CallableStatement SQL;
        try {
            SQL = connexioBD.prepareCall("{call llogarCursos(?,?,?,?,?,?)}");

            SQL.setLong(1, descompte);
            SQL.setLong(2, preuCursFinal);
            SQL.setLong(3, j);
            SQL.setLong(4, idCurs);
            SQL.setString(5, dni);
            SQL.setString(6, tipoCurs);
            SQL.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cabecera");
            alert.setTitle("Info");
            alert.setContentText("Correcte reserva");
            alert.showAndWait();

        } catch (SQLException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        neteijarFormulari();

    };

    /**
     * @param event
     */
    private void mostrarAlertWarning(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("Cap curs seleccionat o client seleccionat");
        alert.showAndWait();

    }

    /**
     * @throws SQLException
     */
    private static void consultarCursosColectiu() throws SQLException {

        connexioBD = conn.getConnexioBD();

        String SQL = "select * from curs,curs_colectiu where curs.id = curs_colectiu.id;";

        PreparedStatement ps = connexioBD.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            cc.add(new CursColectiu(rs.getInt("id"), rs.getString("nom"), rs.getString("dni_monitor"),
                    rs.getInt("preu"), getLocalDate(rs, "data")));
        }
    }

    /**
     * @throws SQLException
     */
    private static void consultarClient() throws SQLException {

        connexioBD = conn.getConnexioBD();

        String SQL = "select client.*, fam_num.num_fam,federat.nivell,federat.num_federacio,fam_num.data_caducitat as DATAFAM,federat.data_caducitat as DATAFED from client left join fam_num on client.dni = fam_num.dni left join federat on client.dni = federat.dni;";

        PreparedStatement ps = connexioBD.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            // System.out.println(rs.getDate("DATAFED"));
            // Clients.add(new Client(rs.getString("dni"), rs.getString("nom"),
            // rs.getString("cognom"),
            // rs.getInt("telefon"), rs.getString("email"), rs.getString("num_fam"),
            // rs.getString("num_federacio"), rs.getInt("nivell"),
            // rs.getDate("DATAFED").toLocalDate(), rs.getDate("DATAFAM").toLocalDate()));

            Clients.add(new Client(rs.getString("dni"), rs.getString("nom"), rs.getString("cognom"),
                    rs.getInt("telefon"), rs.getString("email"), rs.getString("num_fam"), rs.getString("num_federacio"),
                    rs.getInt("nivell"), getLocalDate(rs, "DATAFED"), getLocalDate(rs, "DATAFAM")));
        }
    }

    /**
     * @param rs
     * @param columnName
     * @return LocalDate
     * @throws SQLException
     */
    static public LocalDate getLocalDate(ResultSet rs, String columnName) throws SQLException {
        Date sqlDate = rs.getDate(columnName);
        return sqlDate == null ? null : sqlDate.toLocalDate();
    }

}