package com.example.lab11.controller;

import com.example.lab11.dto.Person;
import com.example.lab11.service.PersonService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PersonControlller {
    private Person person;
    private PersonService personService;
    private ObservableList<Person> personData;

    @FXML
    private Button add;

    @FXML
    private TableColumn<Person, Integer> yearColumn;

    @FXML
    private TextField textAddSecondName;

    @FXML
    private TableColumn<Person, String> secondColumn;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private TableColumn<Person, String> firstColumn;

    @FXML
    private TextField textUpdateBirthdayYear;

    @FXML
    private TextField textUpdateFirstName;

    @FXML
    private TextField textAddFirstName;

    @FXML
    private TextField textAddBirthdayYear;

    @FXML
    private TextField textUpdateSecondName;

    @FXML
    private TableView<Person> table;

    @FXML
    private TableColumn<Person, Long> idColumn;

    @FXML
    private void initialize(){
        this.person = new Person();
        this.personService = new PersonService();
        this.personData = FXCollections.observableArrayList();

        this.textAddFirstName.setText("");
        this.textAddSecondName.setText("");
        this.textAddBirthdayYear.setText("");

        this.textUpdateFirstName.setText("");
        this.textUpdateSecondName.setText("");
        this.textUpdateBirthdayYear.setText("");

        this.idColumn.setCellValueFactory(new PropertyValueFactory<Person, Long>("id"));
        this.firstColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        this.secondColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        this.yearColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("birthdayYear"));

        this.personData.addAll(this.personService.findAll());
        this.table.setItems(this.personData);
    }

    @FXML
    void addPerson(ActionEvent event) {
        this.person.setFirstName(this.textAddSecondName.getText());
        this.person.setLastName(this.textAddFirstName.getText());
        this.person.setBirthdayYear(Integer.parseInt(this.textAddBirthdayYear.getText()));

        if(!this.person.getFirstName().isEmpty()){
            personService.create(person);
        }

        initialize();
    }

    @FXML
    void updatePerson(ActionEvent event) {
        this.person.setFirstName(this.textUpdateSecondName.getText());
        this.person.setLastName(this.textUpdateFirstName.getText());
        this.person.setBirthdayYear(Integer.parseInt(this.textUpdateBirthdayYear.getText()));

        if(!this.person.getFirstName().isEmpty()){
            personService.update(this.person, this.person.getId());
        }

        initialize();
    }

    @FXML
    void deletePerson(ActionEvent event) {
        this.personService.delete(this.person.getId());
        initialize();
    }

    @FXML
    void selectPerson(MouseEvent event) {
        TableView.TableViewSelectionModel<Person> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observableValue, Person oldValue, Person newValue) {
                if(newValue != null){
                    textUpdateFirstName.setText(newValue.getFirstName());
                    textUpdateSecondName.setText(newValue.getLastName());
                    textUpdateBirthdayYear.setText(String.valueOf(newValue.getBirthdayYear()));

                    person.setId(newValue.getId());
                }
            }
        });
    }
}
