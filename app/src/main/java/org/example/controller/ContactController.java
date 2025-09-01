package org.example.controller;

import org.example.dao.ContactDAO;
import org.example.model.Contact;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ContactController {
    private ContactDAO dao = new ContactDAO();
    
    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, Integer> idColumn;
    @FXML
    private TableColumn<Contact, String> nameColumn;
    @FXML
    private TableColumn<Contact, String> phoneColumn;
    @FXML
    private TableColumn<Contact, String> emailColumn;


    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        updateTable();
    }

    @FXML
    private void addContact(){
        FormController.showForm(null, dao, this::updateTable);
    }

    @FXML
    private void editContact() {
        Contact selected = contactTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            FormController.showForm(selected, dao, this::updateTable);
        }
    }

    @FXML
    private void deleteContact() {
        Contact selected = contactTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            dao.delete(selected.getId());
            updateTable();
        }
    }


    public void updateTable(){
        contactTable.setItems(FXCollections.observableArrayList(dao.getAll()));
    }
}
