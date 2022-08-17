package com.example.lab11.service;

import com.example.lab11.dto.Town;
import com.example.lab11.repo.TownRepo;

import java.util.ArrayList;

public class TownService implements CRUDService<Town>{
    private final TownRepo townRepo;

    public TownService(){
        this.townRepo = new TownRepo();
    }

    public TownService(TownRepo townRepo, Town town) {
        this.townRepo = townRepo;
    }

    @Override
    public void create(Town town) {
        this.townRepo.create(town);
    }

    @Override
    public ArrayList<Town> findAll() {
        return this.townRepo.findAll();
    }

    @Override
    public Town findById(long id) {
        return this.townRepo.findById(id);
    }

    @Override
    public void update(Town town, long id) {
        this.townRepo.update(town, id);
    }

    @Override
    public void delete(long id) {
        this.townRepo.delete(id);
    }
}
