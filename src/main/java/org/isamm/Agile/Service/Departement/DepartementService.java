package org.isamm.Agile.Service.Departement;

import org.isamm.Agile.model.Departement;
import org.isamm.Agile.model.Entreprise;
import org.isamm.Agile.model.Project;

import java.util.List;

public interface DepartementService {

    public List<Departement> getAllDepartements();
    public Departement saveDepartement(Departement departement);
}
