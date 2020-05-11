package controller;

import app.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import model.Client;
import model.Person;
import model.Privacy;
import model.Salutation;

import java.io.IOException;

public class ClientSummary_Controller extends SceneLoader {


    @FXML
    private ImageView imgClient;

    @FXML
    private Button btnDeleteClient;

    @FXML
    private Button btnEditClient;

    @FXML
    private Label lbNameClient;

    @FXML
    private Label lbSsnrClient;

    @FXML
    private Label lbBirthdateClient;

    @FXML
    private Label lbStreetClient;

    @FXML
    private Label lbPlaceClient;

    @FXML
    private Label lbPhoneClient;

    @FXML
    private Label lbEmailClient;

    @FXML
    private Label lbIbanClient;

    @FXML
    private Label lbBicClient;

    @FXML
    private Label lbDiagnosisClient;

    @FXML
    private Label lbOccupationClient;

    @FXML
    private Label lbAllergiesClient;

    @FXML
    private Label lbOtherClient;

    @FXML
    private CheckBox cbPrivacy1Client;

    @FXML
    private CheckBox cbPrivacy4Client;

    @FXML
    private CheckBox cbPrivacy3Client;

    @FXML
    private CheckBox cbPrivacy2Client;

    @FXML
    private Button btnShowEsv;

    @FXML
    private Button btnShowContact1;

    @FXML
    private Button btnShowContact2;

    @FXML
    private Label lbNameEsv;

    @FXML
    private Label lbNameContact1;

    @FXML
    private Label lbNameContact2;

    private Client editableClient = null;

    public Client getEditableClient() {
        return editableClient;
    }

    public void setEditableClient(Client editableClient) {
        this.editableClient = editableClient;
        if (editableClient != null) {
            if (editableClient.getSsnr() != 0){
                lbSsnrClient.setText(""+editableClient.getSsnr());
            }
            lbNameClient.setText(editableClient.getSalutation() + " " + editableClient.getTitle() + " " + editableClient.getFirstName() + " " + editableClient.getLastName());
            lbBirthdateClient.setText(editableClient.getBirthDate().toString());
            lbStreetClient.setText(editableClient.getStreetAndNr());
            lbPlaceClient.setText(editableClient.getPlace());
            lbPhoneClient.setText(editableClient.getTelNr());
            lbEmailClient.setText(editableClient.getEmail());
            lbNameEsv.setText("Nicht angegeben");
            lbNameContact1.setText("Nicht angegeben");
            lbNameContact2.setText("Nicht angegeben");
            if (editableClient.getEsv() != null) {
                lbNameEsv.setText(editableClient.getEsv().getSalutation() + " " + editableClient.getEsv().getFirstName()+ " "+ editableClient.getEsv().getLastName());
            }
            if (editableClient.getEmergencyContact1() != null) {
                lbNameContact1.setText(editableClient.getEmergencyContact1().getSalutation() + " " + editableClient.getEmergencyContact1().getFirstName()+ " "+ editableClient.getEmergencyContact1().getLastName());
                if (editableClient.getEmergencyContact2() != null) {
                    lbNameContact2.setText(editableClient.getEmergencyContact2().getSalutation() + " " + editableClient.getEmergencyContact2().getFirstName()+ " "+ editableClient.getEmergencyContact2().getLastName());
                }
            }
            lbDiagnosisClient.setText(editableClient.getDiagnose());
            lbOccupationClient.setText(editableClient.getJob());
            lbAllergiesClient.setText(editableClient.getAllergies());
            lbOtherClient.setText(editableClient.getOther());

            //PRIVACY
            Privacy privacyOfEditableClient = new Privacy();
            if (editableClient.getPrivacy() != null) {
                privacyOfEditableClient = editableClient.getPrivacy();
            }
            cbPrivacy1Client.setSelected(privacyOfEditableClient.getPrivacies().get(0));
            cbPrivacy2Client.setSelected(privacyOfEditableClient.getPrivacies().get(1));
            cbPrivacy3Client.setSelected(privacyOfEditableClient.getPrivacies().get(2));
            cbPrivacy4Client.setSelected(privacyOfEditableClient.getPrivacies().get(3));



        }
    }



    @FXML
    void btnDeleteClient_Clicked(ActionEvent event) {

    }

    @FXML
    void btnEditClient_Clicked(ActionEvent event) {

    }

    @FXML
    void btnShowContact1_Clicked(ActionEvent event) throws IOException {
        try {
            showClient(editableClient.getEmergencyContact1());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void btnShowContact2_Clicked(ActionEvent event) {

    }

    @FXML
    void btnShowEsv_Clicked(ActionEvent event) {

    }


    private void showClient(Person client) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("../view/ClientSummary.fxml"));
        BorderPane root = fxml.load();
        Scene scene = new Scene(root);
        this.getPrimStage().setScene(scene);
        Screen screen = Screen.getPrimary();

        //Maximized
        Rectangle2D bounds = screen.getVisualBounds();
        this.getPrimStage().setX(bounds.getMinX());
        this.getPrimStage().setY(bounds.getMinY());
        this.getPrimStage().setWidth(bounds.getWidth());
        this.getPrimStage().setHeight(bounds.getHeight());
        this.getPrimStage().show();



        ClientSummary_Controller showController = fxml.getController();
        //Pass whatever data you want. You can have multiple method calls here
        showController.setEditableClient((Client) client);


        SceneLoader loader = showController;
        loader.setPrimaryStage(this.getPrimStage());

    }

}
