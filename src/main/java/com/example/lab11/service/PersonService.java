package com.example.lab11.service;

import com.example.lab11.dto.Person;
import com.example.lab11.repo.PersonRepo;

import java.util.ArrayList;

public class PersonService implements CRUDService<Person>{
    private final PersonRepo personRepo;

    public PersonService(){
        this.personRepo = new PersonRepo();
    }

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public void create(Person person) {
        this.personRepo.create(person);
    }

    @Override
    public ArrayList<Person> findAll() {
        return this.personRepo.findAll();
    }

    @Override
    public Person findById(long id) {
        return this.personRepo.findById(id);
    }

    @Override
    public void update(Person person, long id) {
        this.personRepo.update(person, id);
    }

    @Override
    public void delete(long id) {
        this.personRepo.delete(id);
    }
}
