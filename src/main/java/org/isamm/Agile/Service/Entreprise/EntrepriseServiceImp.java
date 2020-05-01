package org.isamm.Agile.Service.Entreprise;


import org.isamm.Agile.Repository.EntrepriseDao;
import org.isamm.Agile.model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EntrepriseServiceImp implements EntrepriseService{
    @Autowired
    private EntrepriseDao entreprisedao;
    @Override
    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entreprisedao.save(entreprise);
    }

    @Override
    public List<Entreprise> getAllEntreprises() {
        return entreprisedao.findAll();
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entreprise) {
        return null;
    }

    @Override
    public boolean checkIfnameExists(String name) {
        return entreprisedao.existsByName(name);
    }

    @Override
    public boolean checkIfemailExists(String email) {
        return entreprisedao.existsByEmail(email);
    }

    @Override
    public void deleteEntreprise(Long id) {

    }
}
