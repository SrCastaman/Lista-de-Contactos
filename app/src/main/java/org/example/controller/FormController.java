package org.example.controller;

import org.example.dao.ContactDAO;
import org.example.model.Contact;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormController {
    @FXML
    private TextField nameField, phoneField, emailField;
    
    private ContactDAO dao;
    private Contact contact;
    private Runnable onSaveCallback;


    public void initData(Contact contact, ContactDAO dao, Runnable onSaveCallback){
        this.dao = dao;
        this.contact = contact;
        this.onSaveCallback = onSaveCallback;

        if (contact != null) {
            nameField.setText(contact.getName());
            phoneField.setText(contact.getPhone());
            emailField.setText(contact.getEmail());
        }
    }


    @FXML
    private void saveContact(){
        if(contact == null){
            dao.insert(new Contact(nameField.getText(), phoneField.getText(), emailField.getText()));
        } else {
            contact.setName(nameField.getText());
            contact.setPhone(phoneField.getText());
            contact.setEmail(emailField.getText());
            dao.update(contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail());
        }
        onSaveCallback.run();
        closeWindow();
    }

    private void closeWindow(){
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }


    public static void showForm(Contact contact, ContactDAO dao, Runnable onSaveCallback) {
        try {
            FXMLLoader loader = new FXMLLoader(FormController.class.getResource("/org/example/view/contact_form.fxml"));
            Parent root = loader.load();
            
            FormController controller = loader.getController();
            controller.initData(contact, dao, onSaveCallback);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
