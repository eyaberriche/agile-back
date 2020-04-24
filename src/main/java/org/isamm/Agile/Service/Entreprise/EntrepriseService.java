package org.isamm.Agile.Service.Entreprise;

import org.isamm.Agile.model.Entreprise;

import java.util.List;

public interface EntrepriseService {
    public Entreprise saveEntreprise(Entreprise entreprise);
    public List<Entreprise> getAllEntreprises();
}
