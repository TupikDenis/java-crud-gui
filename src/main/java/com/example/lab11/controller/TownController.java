package com.example.lab11.controller;

import com.example.lab11.dto.Town;
import com.example.lab11.service.TownService;
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

public class TownController {
    private Town town;
    private TownService townService;
    private ObservableList<Town> townData;

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private TextField textUpdateTown;

    @FXML
    private TextField textAddTown;

    @FXML
    private TableView<Town> table;

    @FXML
    private TableColumn<Town, Long> idColumn;

    @FXML
    private TableColumn<Town, String> townColumn;

    @FXML
    private void initialize() {
        this.town = new Town();
        this.townService= new TownService();
        this.townData = FXCollections.observableArrayList();

        this.textAddTown.setText("");
        this.textUpdateTown.setText("");

        this.idColumn.setCellValueFactory(new PropertyValueFactory<Town, Long>("id"));
        this.townColumn.setCellValueFactory(new PropertyValueFactory<Town, String>("townName"));

        this.townData.addAll(this.townService.findAll());
        this.table.setItems(this.townData);
    }

    @FXML
    void addTown(ActionEvent event) {
        this.town.setTownName(this.textAddTown.getText());

        if(!this.town.getTownName().isEmpty()){
            townService.create(town);
        }

        initialize();
    }

    @FXML
    void updateTown(ActionEvent event) {
        this.town.setTownName(this.textUpdateTown.getText());

        if(!this.town.getTownName().isEmpty()){
            townService.update(this.town, this.town.getId());
        }

        initialize();
    }

    @FXML
    void deleteTown(ActionEvent event) {
        townService.delete(town.getId());
        initialize();
    }

    @FXML
    void selectTown(MouseEvent event) {
        TableView.TableViewSelectionModel<Town> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Town>() {
            @Override
            public void changed(ObservableValue<? extends Town> observableValue, Town oldValue, Town newValue) {
                if(newValue != null){
                    textUpdateTown.setText(newValue.getTownName());
                    town.setId(newValue.getId());
                }
            }
        });
    }
}