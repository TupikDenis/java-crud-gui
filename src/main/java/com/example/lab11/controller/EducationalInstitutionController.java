package com.example.lab11.controller;

import com.example.lab11.dto.EducationalInstitution;
import com.example.lab11.dto.Person;
import com.example.lab11.dto.Town;
import com.example.lab11.dto.Type;
import com.example.lab11.service.EducationalInstitutionService;
import com.example.lab11.service.PersonService;
import com.example.lab11.service.TownService;
import com.example.lab11.service.TypeService;
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

public class EducationalInstitutionController {
    @FXML
    public TextField checkYear;
    private EducationalInstitution educationalInstitution;

    private ArrayList<Person> persons;
    private ArrayList<Type> types;
    private ArrayList<Town> towns;

    private EducationalInstitutionService educationalInstitutionService;
    private PersonService personService;
    private TypeService typeService;
    private TownService townService;

    private ObservableList<EducationalInstitution> educationalInstitutionData;
    private ObservableList<Person> personData;
    private ObservableList<Type> typeData;
    private ObservableList<Town> townData;

    @FXML
    private Button add;

    @FXML
    private ComboBox<Person> comboAddPerson;

    @FXML
    private TableColumn<EducationalInstitution, String> EducationalInstitutionColumn;

    @FXML
    private TableColumn<EducationalInstitution, Integer> FoundedColumn;

    @FXML
    private TextField textUpdateEducationalInstitution;

    @FXML
    private Button update;

    @FXML
    private TableColumn<EducationalInstitution, Long> TownColumn;

    @FXML
    private TextField textUpdateFounded;

    @FXML
    private ComboBox<Person> comboUpdatePerson;

    @FXML
    private Button delete;

    @FXML
    private TextField textAddEducationalInstitution;

    @FXML
    private ComboBox<Town> comboUpdateTown;

    @FXML
    private TableColumn<EducationalInstitution, Long> PersonColumn;

    @FXML
    private ComboBox<Type> comboAddType;

    @FXML
    private ComboBox<Type> comboUpdateType;

    @FXML
    private ComboBox<Town> comboAddTown;

    @FXML
    private TextField textAddFounded;

    @FXML
    private TableView<EducationalInstitution> table;

    @FXML
    private TableColumn<EducationalInstitution, Long> TypeColumn;

    @FXML
    private TableColumn<EducationalInstitution, Long> idColumn;

    @FXML
    private void initialize() {
        this.educationalInstitution = new EducationalInstitution();
        this.persons = new ArrayList<Person>();
        this.towns = new ArrayList<Town>();
        this.types = new ArrayList<Type>();

        this.educationalInstitutionService = new EducationalInstitutionService();
        this.personService = new PersonService();
        this.typeService = new TypeService();
        this.townService = new TownService();

        this.educationalInstitutionData = FXCollections.observableArrayList();
        this.personData = FXCollections.observableArrayList();
        this.typeData = FXCollections.observableArrayList();
        this.townData = FXCollections.observableArrayList();

        this.textAddEducationalInstitution.setText("");
        this.textAddFounded.setText("");

        this.textUpdateEducationalInstitution.setText("");
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

        //show town
        this.townData.addAll(this.townService.findAll());

        String str2 = this.townData.toString().substring(1, this.townData.toString().length() - 1);
        String[] towns = str2.split(", ");
        String[] town = new String[2];

        for (int i = 0; i < towns.length; i++) {
            town = towns[i].split(" ");
            this.towns.add(new Town(Long.parseLong(town[0]), town[1]));
        }

        this.townData = FXCollections.observableArrayList(this.towns);

        this.comboAddTown.setItems(this.townData);
        this.comboUpdateTown.setItems(this.townData);

        //show type
        this.typeData.addAll(this.typeService.findAll());

        String str3 = this.typeData.toString().substring(1, this.typeData.toString().length() - 1);
        String[] types = str3.split(", ");
        String[] type = new String[2];

        for (int i = 0; i < types.length; i++) {
            type = types[i].split(" ");
            this.types.add(new Type(Long.parseLong(type[0]), type[1]));
        }

        this.typeData = FXCollections.observableArrayList(this.types);

        this.comboAddType.setItems(this.typeData);
        this.comboUpdateType.setItems(this.typeData);

        //show table
        this.idColumn.setCellValueFactory(new PropertyValueFactory<EducationalInstitution, Long>("id"));
        this.EducationalInstitutionColumn.setCellValueFactory(new PropertyValueFactory<EducationalInstitution, String>("name"));
        this.FoundedColumn.setCellValueFactory(new PropertyValueFactory<EducationalInstitution, Integer>("founded"));
        this.TownColumn.setCellValueFactory(new PropertyValueFactory<EducationalInstitution, Long>("nameTown"));
        this.TypeColumn.setCellValueFactory(new PropertyValueFactory<EducationalInstitution, Long>("nameType"));
        this.PersonColumn.setCellValueFactory(new PropertyValueFactory<EducationalInstitution, Long>("namePerson"));

        this.educationalInstitutionData.addAll(this.educationalInstitutionService.findAll());

        for(int i=0; i < educationalInstitutionData.size(); i++) {
            this.educationalInstitutionData.get(i).setNameTown(this.townService.findById(this.educationalInstitutionData.get(i).getIdTown()).getTownName());
            this.educationalInstitutionData.get(i).setNameType(this.typeService.findById(this.educationalInstitutionData.get(i).getIdType()).getType());
            this.educationalInstitutionData.get(i).setNamePerson(this.personService.findById(this.educationalInstitutionData.get(i).getIdPerson()).getLastName());
        }

        this.table.setItems(this.educationalInstitutionData);
    }

    @FXML
    void addEducationalInstitution(ActionEvent event) {
        String tmp = "";
        this.educationalInstitution.setName(this.textAddEducationalInstitution.getText());
        this.educationalInstitution.setFounded(Integer.parseInt(this.textAddFounded.getText()));

        tmp = String.valueOf(this.comboAddPerson.getValue());
        String[] resultPerson = tmp.split(" ");
        this.educationalInstitution.setIdPerson(Long.parseLong(resultPerson[0]));

        tmp = String.valueOf(this.comboAddTown.getValue());
        String[] resultTown = tmp.split(" ");
        this.educationalInstitution.setIdTown(Long.parseLong(resultTown[0]));

        tmp = String.valueOf(this.comboAddType.getValue());
        String[] resultType = tmp.split(" ");
        this.educationalInstitution.setIdType(Long.parseLong(resultType[0]));

        this.educationalInstitutionService.create(this.educationalInstitution);

        initialize();
    }

    @FXML
    void updateEducationalInstitution(ActionEvent event) {
        String tmp = "";
        this.educationalInstitution.setName(this.textUpdateEducationalInstitution.getText());
        this.educationalInstitution.setFounded(Integer.parseInt(this.textUpdateFounded.getText()));

        tmp = String.valueOf(this.comboUpdatePerson.getValue());
        String[] resultPerson = tmp.split(" ");
        this.educationalInstitution.setIdPerson(Long.parseLong(resultPerson[0]));

        tmp = String.valueOf(this.comboUpdateTown.getValue());
        String[] resultTown = tmp.split(" ");
        this.educationalInstitution.setIdTown(Long.parseLong(resultTown[0]));

        tmp = String.valueOf(this.comboUpdateType.getValue());
        String[] resultType = tmp.split(" ");
        this.educationalInstitution.setIdType(Long.parseLong(resultType[0]));

        this.educationalInstitutionService.update(this.educationalInstitution, this.educationalInstitution.getId());

        initialize();
    }

    @FXML
    void deleteEducationalInstitution(ActionEvent event) {
        this.educationalInstitutionService.delete(this.educationalInstitution.getId());

        initialize();
    }

    @FXML
    void selectEducationalInstitution(MouseEvent event) {
        TableView.TableViewSelectionModel<EducationalInstitution> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<EducationalInstitution>() {
            @Override
            public void changed(ObservableValue<? extends EducationalInstitution> observableValue, EducationalInstitution oldValue, EducationalInstitution newValue) {
                if (newValue != null) {
                    textUpdateEducationalInstitution.setText(newValue.getName());
                    textUpdateFounded.setText(String.valueOf(newValue.getFounded()));
                    comboUpdateTown.setValue(townService.findById(newValue.getIdTown()));
                    comboUpdateType.setValue(typeService.findById(newValue.getIdType()));
                    comboUpdatePerson.setValue(personService.findById(newValue.getIdPerson()));

                    educationalInstitution.setId(newValue.getId());
                }
            }
        });
    }
}