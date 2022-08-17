package com.example.lab11.controller;

import com.example.lab11.dto.EducationalInstitution;
import com.example.lab11.dto.Facultet;
import com.example.lab11.dto.Person;
import com.example.lab11.service.EducationalInstitutionService;
import com.example.lab11.service.FacultetService;
import com.example.lab11.service.PersonService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class FacultetController {
    private Facultet facultet;

    private ArrayList<EducationalInstitution> educationalInstitutions;
    private ArrayList<Person> persons;

    private FacultetService facultetService;
    private EducationalInstitutionService educationalInstitutionService;
    private PersonService personService;

    private ObservableList<Facultet> facultetData;
    private ObservableList<EducationalInstitution> educationalInstitutionData;
    private ObservableList<Person> personData;

    @FXML
    private Button add;

    @FXML
    private ComboBox<Person> comboAddPerson;

    @FXML
    private Button update;

    @FXML
    private ComboBox<EducationalInstitution> comboAddEdu;

    @FXML
    private TextField textUpdateFacultet;

    @FXML
    private ComboBox<Person> comboUpdatePerson;

    @FXML
    private Button delete;

    @FXML
    private TextField textUpdateFounded;

    @FXML
    private TextField textAddFounded;

    @FXML
    private TableView<Facultet> table;

    @FXML
    private TextField textAddFacultet;

    @FXML
    private ComboBox<EducationalInstitution> comboUpdateEdu;

    @FXML
    private TableColumn<Facultet, Long> idColumn;

    @FXML
    private TableColumn<Facultet, String> personColumn;

    @FXML
    private TableColumn<Facultet, Integer> yearColumn;

    @FXML
    private TableColumn<Facultet, String> facultetColumn;

    @FXML
    private TableColumn<Facultet, String> eduColumn;

    @FXML
    private void initialize() {
        this.facultet = new Facultet();

        this.educationalInstitutions = new ArrayList<EducationalInstitution>();
        this.persons = new ArrayList<Person>();

        this.facultetService = new FacultetService();
        this.educationalInstitutionService = new EducationalInstitutionService();
        this.personService = new PersonService();

        this.facultetData = FXCollections.observableArrayList();
        this.educationalInstitutionData = FXCollections.observableArrayList();
        this.personData = FXCollections.observableArrayList();

        this.textAddFacultet.setText("");
        this.textAddFounded.setText("");

        this.textUpdateFacultet.setText("");
        this.textUpdateFounded.setText("");

        //show person
        this.personData.addAll(this.personService.findAll());

        String str = this.personData.toString().substring(1, this.personData.toString().length() - 1);
        String[] persons = str.split(", ");
        String[] person = new String[2];

        for (int i = 0; i < persons.length; i++) {
            person = persons[i].split(" ");
            this.persons.add(new Person(Long.parseLong(person[0]), person[1]));
        }

        this.personData = FXCollections.observableArrayList(this.persons);

        this.comboAddPerson.setItems(this.personData);
        this.comboUpdatePerson.setItems(this.personData);

        //show edu
        this.educationalInstitutionData.addAll(this.educationalInstitutionService.findAll());

        String str2 = this.educationalInstitutionData.
                toString().
                substring(1, this.educationalInstitutionData.toString().length() - 1);
        String[] edus = str2.split(", ");
        String[] edu = new String[2];

        for (int i = 0; i < persons.length; i++) {
            edu = edus[i].split(" ");
            this.educationalInstitutions.add(new EducationalInstitution(Long.parseLong(edu[0]), edu[1]));
        }

        this.educationalInstitutionData = FXCollections.observableArrayList(this.educationalInstitutions);

        this.comboAddEdu.setItems(this.educationalInstitutionData);
        this.comboUpdateEdu.setItems(this.educationalInstitutionData);

        this.idColumn.setCellValueFactory(new PropertyValueFactory<Facultet, Long>("id"));
        this.facultetColumn.setCellValueFactory(new PropertyValueFactory<Facultet, String>("name"));
        this.yearColumn.setCellValueFactory(new PropertyValueFactory<Facultet, Integer>("founded"));
        this.eduColumn.setCellValueFactory(new PropertyValueFactory<Facultet, String>("nameEducationalInstitution"));
        this.personColumn.setCellValueFactory(new PropertyValueFactory<Facultet, String>("namePerson"));

        this.facultetData.addAll(this.facultetService.findAll());

        for(int i=0; i < facultetData.size(); i++) {
            this.facultetData.get(i).setNameEducationalInstitution(this.educationalInstitutionService.findById(this.facultetData.get(i).getIdEducationalInstitution()).getName());
            this.facultetData.get(i).setNamePerson(this.personService.findById(this.facultetData.get(i).getIdPerson()).getLastName());
        }
        this.table.setItems(this.facultetData);
    }

    @FXML
    void addTown(ActionEvent event) {
        String tmp = "";
        this.facultet.setName(this.textAddFacultet.getText());
        this.facultet.setFounded(Integer.parseInt(this.textAddFounded.getText()));

        tmp = String.valueOf(this.comboAddEdu.getValue());
        String[] resultEdu = tmp.split(" ");
        this.facultet.setIdEducationalInstitution(Long.parseLong(resultEdu[0]));

        tmp = String.valueOf(this.comboAddPerson.getValue());
        String[] resultPerson = tmp.split(" ");
        this.facultet.setIdPerson(Long.parseLong(resultPerson[0]));

        this.facultetService.create(this.facultet);

        initialize();
    }

    @FXML
    void updateTown(ActionEvent event) {
        String tmp = "";
        this.facultet.setName(this.textUpdateFacultet.getText());
        this.facultet.setFounded(Integer.parseInt(this.textUpdateFounded.getText()));

        tmp = String.valueOf(this.comboUpdateEdu.getValue());
        String[] resultEdu = tmp.split(" ");
        this.facultet.setIdEducationalInstitution(Long.parseLong(resultEdu[0]));

        tmp = String.valueOf(this.comboUpdatePerson.getValue());
        String[] resultPerson = tmp.split(" ");
        this.facultet.setIdPerson(Long.parseLong(resultPerson[0]));

        this.facultetService.update(this.facultet, this.facultet.getId());

        initialize();
    }

    @FXML
    void deleteTown(ActionEvent event) {
        this.facultetService.delete(this.facultet.getId());
        initialize();
    }

    @FXML
    void selectTown(MouseEvent event) {
        TableView.TableViewSelectionModel<Facultet> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Facultet>() {
            @Override
            public void changed(ObservableValue<? extends Facultet> observableValue, Facultet oldValue, Facultet newValue) {
                if (newValue != null) {
                    textUpdateFacultet.setText(newValue.getName());
                    textUpdateFounded.setText(String.valueOf(newValue.getFounded()));
                    comboUpdateEdu.setValue(educationalInstitutionService.findById(newValue.getIdEducationalInstitution()));
                    comboUpdatePerson.setValue(personService.findById(newValue.getIdPerson()));

                    facultet.setId(newValue.getId());
                }
            }
        });
    }
}

