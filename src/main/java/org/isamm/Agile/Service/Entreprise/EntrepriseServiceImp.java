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
}
