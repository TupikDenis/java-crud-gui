package com.example.lab11.service;

import com.example.lab11.dto.Type;
import com.example.lab11.repo.TypeRepo;

import java.util.ArrayList;

public class TypeService implements CRUDService<Type> {
    private final TypeRepo typeRepo;

    public TypeService(){
        this.typeRepo = new TypeRepo();
    }

    public TypeService(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }

    @Override
    public void create(Type type) {
        this.typeRepo.create(type);
    }

    @Override
    public ArrayList<Type> findAll() {
        return this.typeRepo.findAll();
    }

    @Override
    public Type findById(long id) {
        return this.typeRepo.findById(id);
    }

    @Override
    public void update(Type type, long id) {
        this.typeRepo.update(type, id);
    }

    @Override
    public void delete(long id) {
        this.typeRepo.delete(id);
    }
}
