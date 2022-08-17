package com.example.lab11.service;

import com.example.lab11.dto.EducationalInstitution;
import com.example.lab11.repo.EducationalInstitutionRepo;

import java.util.ArrayList;

public class EducationalInstitutionService implements CRUDService<EducationalInstitution> {
    private final EducationalInstitutionRepo educationalInstitutionRepo;

    public EducationalInstitutionService(){
        this.educationalInstitutionRepo = new EducationalInstitutionRepo();
    }

    public EducationalInstitutionService(EducationalInstitutionRepo educationalInstitutionRepo) {
        this.educationalInstitutionRepo = educationalInstitutionRepo;
    }

    @Override
    public void create(EducationalInstitution educationalInstitution) {
        this.educationalInstitutionRepo.create(educationalInstitution);
    }

    @Override
    public ArrayList<EducationalInstitution> findAll() {
        return this.educationalInstitutionRepo.findAll();
    }

    @Override
    public EducationalInstitution findById(long id) {
        return this.educationalInstitutionRepo.findById(id);
    }

    @Override
    public void update(EducationalInstitution educationalInstitution, long id) {
        this.educationalInstitutionRepo.update(educationalInstitution, id);
    }

    @Override
    public void delete(long id) {
        this.educationalInstitutionRepo.delete(id);
    }
}
