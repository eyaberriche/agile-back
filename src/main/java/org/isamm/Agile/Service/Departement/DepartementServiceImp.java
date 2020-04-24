package org.isamm.Agile.Service.Departement;


import org.isamm.Agile.Repository.DepartementDao;

import org.isamm.Agile.model.Departement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartementServiceImp implements DepartementService {
    @Autowired
    private DepartementDao departementdao;

    @Override
    public List<Departement> getAllDepartements() {
        return departementdao.findAll();
    }
}
