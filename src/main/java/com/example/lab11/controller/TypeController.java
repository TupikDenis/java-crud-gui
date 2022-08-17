package com.example.lab11.controller;

import com.example.lab11.dto.Type;
import com.example.lab11.service.TypeService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class TypeController {
    @FXML
    public Button updateForm;

    private Type type;
    private TypeService typeService;
    private ObservableList<Type> typeData;

    @FXML
    private Button add;

    @FXML
    private Button addForm;

    @FXML
    private TextField textUpdateType;

    @FXML
    private TextField textAddType;

    @FXML
    private Button update;

    @FXML
    private TableColumn<Type, Long> idColumn;

    @FXML
    private TableColumn<Type, String> typeColumn;

    @FXML
    private Button delete;

    @FXML
    private TableView<Type> table;

    @FXML
    private void initialize() {
        this.type = new Type();
        this.typeService= new TypeService();
        this.typeData = FXCollections.observableArrayList();

        this.textAddType.setText("");
        this.textUpdateType.setText("");

        this.idColumn.setCellValueFactory(new PropertyValueFactory<Type, Long>("id"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory<Type, String>("type"));

        this.typeData.addAll(this.typeService.findAll());
        this.table.setItems(this.typeData);
    }

    @FXML
    void addType(ActionEvent event) {
        this.type.setType(this.textAddType.getText());

        if(!this.type.getType().isEmpty()){
            typeService.create(type);
        }

        initialize();
    }

    @FXML
    void updateType(ActionEvent event) {
        this.type.setType(this.textUpdateType.getText());

        if(!this.type.getType().isEmpty()){
            typeService.update(type, type.getId());
        }

        initialize();
    }

    @FXML
    void deleteType(ActionEvent event) {
        typeService.delete(type.getId());
        initialize();
    }

    @FXML
    void selectType(MouseEvent event) {
        TableView.TableViewSelectionModel<Type> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Type>() {
            @Override
            public void changed(ObservableValue<? extends Type> observableValue, Type oldValue, Type newValue) {
                if(newValue != null){
                    textUpdateType.setText(newValue.getType());
                    type.setId(newValue.getId());
                }
            }
        });
    }
}
